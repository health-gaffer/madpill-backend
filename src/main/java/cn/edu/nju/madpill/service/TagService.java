package cn.edu.nju.madpill.service;

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

import java.util.List;
import java.util.stream.Collectors;

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
    private final ModelMapper modelMapper;

    public TagService(DrugTagMapper drugTagMapper, TagMapper tagMapper, ModelMapper modelMapper) {
        this.drugTagMapper = drugTagMapper;
        this.tagMapper = tagMapper;
        this.modelMapper = modelMapper;
    }

    public List<TagDTO> getTagsOfUser(Long userId) {
        SelectStatementProvider tagSelectStatement = select(tag.id, tag.name)
                .from(tag)
                .where(tag.userId, isEqualTo(userId))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<Tag> tags = tagMapper.selectMany(tagSelectStatement);
        List<TagDTO> tagDTOS = tags.stream().map(tag -> TagDTO.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build())
                .collect(Collectors.toList());
        return tagDTOS;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteTag(Long tagId) {
        DeleteStatementProvider deleteStatement = deleteFrom(drugTag)
                .where(drugTag.tagId, isEqualTo(tagId)).build()
                .render(RenderingStrategies.MYBATIS3);
        drugTagMapper.delete(deleteStatement);
        int row = tagMapper.deleteByPrimaryKey(tagId);
        if (0 == row) {
            throw ExceptionSuppliers.TAG_NOT_FOUND.get();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void addNewTag(TagDTO dto) {
        Tag tag = new Tag();
        modelMapper.map(dto, tag);
        tagMapper.insert(tag);
    }

//    public int updateTagsOfDrug(Long drugId, Long[] tagIds) {
//        DeleteStatementProvider deleteStatement = deleteFrom(drugTag)
//                .where(drugTag.drugId, isEqualTo(drugId)).build()
//                .render(RenderingStrategy.MYBATIS3);
//        drugTagMapper.delete(deleteStatement);
//        List<DrugTag> drugTags = new ArrayList<>();
//        for (Long tadId :tagIds){
//            DrugTag drugTag = new DrugTag();
//            drugTag.setDrugId(drugId);
//            drugTag.setTagId(tadId);
//            drugTags.add(drugTag);
//        }
//        return drugTagMapper.insertMultiple(drugTags);
//    }
}
