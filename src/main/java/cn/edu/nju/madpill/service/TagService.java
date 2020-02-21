package cn.edu.nju.madpill.service;

import cn.edu.nju.madpill.custommapper.TagAssistantMapper;
import cn.edu.nju.madpill.domain.DrugTag;
import cn.edu.nju.madpill.domain.Tag;
import cn.edu.nju.madpill.dto.TagDTO;
import cn.edu.nju.madpill.exception.ExceptionSuppliers;
import cn.edu.nju.madpill.mapper.DrugTagMapper;
import cn.edu.nju.madpill.mapper.TagMapper;
import org.modelmapper.ModelMapper;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static cn.edu.nju.madpill.custommapper.TagAssistantDynamicSqlSupport.buildInsert;
import static cn.edu.nju.madpill.mapper.DrugTagDynamicSqlSupport.drugTag;
import static cn.edu.nju.madpill.mapper.TagDynamicSqlSupport.tag;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * <p>
 * <p>
 *
 * @author gyyyy
 * @date 2020/2/11
 */
@Service
public class TagService {

    private final DrugTagMapper drugTagMapper;
    private final TagMapper tagMapper;
    private final TagAssistantMapper tagAssistantMapper;
    private final ModelMapper modelMapper;

    public TagService(DrugTagMapper drugTagMapper, TagMapper tagMapper, TagAssistantMapper tagAssistantMapper, ModelMapper modelMapper) {
        this.drugTagMapper = drugTagMapper;
        this.tagMapper = tagMapper;
        this.tagAssistantMapper = tagAssistantMapper;
        this.modelMapper = modelMapper;
    }

    public List<TagDTO> getTagsOfUser(Long userId) {
        SelectStatementProvider tagSelectStatement = select(tag.id, tag.name)
                .from(tag)
                .where(tag.userId, isEqualTo(userId))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<Tag> tags = tagMapper.selectMany(tagSelectStatement);
        return tags.stream().map(tag -> {
            TagDTO dto = new TagDTO();
            modelMapper.map(tag, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteTag(Long tagId, Long userId) {
        Tag toDelete = tagMapper.selectByPrimaryKey(tagId).orElseThrow(ExceptionSuppliers.TAG_NOT_FOUND);
        if (userId.equals(toDelete.getUserId())) {
            DeleteStatementProvider deleteStatement = deleteFrom(drugTag)
                    .where(drugTag.tagId, isEqualTo(tagId))
                    .build()
                    .render(RenderingStrategies.MYBATIS3);
            drugTagMapper.delete(deleteStatement);
            tagMapper.deleteByPrimaryKey(tagId);
        } else {
            throw ExceptionSuppliers.PERMISSION_DENIED.get();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Long addNewTag(TagDTO dto, Long userId) {
        Tag newTag = new Tag();
        modelMapper.map(dto, newTag);
        newTag.setUserId(userId);
        tagAssistantMapper.insert(buildInsert(newTag));
        return newTag.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateTagsOfDrug(Long drugId, Long[] tagIds) {
        drugTagMapper.delete(c -> c.where(drugTag.drugId, isEqualTo(drugId)));

        if (tagIds.length == 0) {
            return 0;
        } else {
            List<DrugTag> drugTags = new ArrayList<>();
            for (Long tadId : tagIds) {
                DrugTag drugTag = new DrugTag();
                drugTag.setDrugId(drugId);
                drugTag.setTagId(tadId);
                drugTags.add(drugTag);
            }
            return drugTagMapper.insertMultiple(drugTags);
        }
    }
}
