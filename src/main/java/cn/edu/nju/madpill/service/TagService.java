package cn.edu.nju.madpill.service;

import cn.edu.nju.madpill.custommapper.AssistantMapper;
import cn.edu.nju.madpill.domain.DrugTag;
import cn.edu.nju.madpill.domain.Tag;
import cn.edu.nju.madpill.dto.DrugBriefDTO;
import cn.edu.nju.madpill.dto.DrugDTO;
import cn.edu.nju.madpill.dto.TagDTO;
import cn.edu.nju.madpill.exception.BaseException;
import cn.edu.nju.madpill.mapper.DrugTagMapper;
import cn.edu.nju.madpill.mapper.TagMapper;
import com.mysql.cj.xdevapi.UpdateStatement;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private final AssistantMapper assistantMapper;

    public TagService(DrugTagMapper drugTagMapper, TagMapper tagMapper, AssistantMapper assistantMapper) {
        this.drugTagMapper = drugTagMapper;
        this.tagMapper = tagMapper;
        this.assistantMapper = assistantMapper;
    }

    public DrugDTO getDrugDetail(Long drugId) throws BaseException {
        return null;
    }

    public List<DrugBriefDTO> getUserDrugs(Long userId) throws BaseException {
        return null;
    }

    public List<TagDTO> getTagsOfUser(Long userId) {
        SelectStatementProvider tagSelectStatement = select(tag.id,tag.name)
                .from(tag)
                .where(tag.userId, isEqualTo(userId))
                .build()
                .render(RenderingStrategy.MYBATIS3);
        List<Tag> tags = tagMapper.selectMany(tagSelectStatement);
        List<TagDTO> tagDTOS = tags.stream().map(tag -> TagDTO.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build())
                .collect(Collectors.toList());
        return tagDTOS;
    }

    public int deleteTag(Long userId, Long tagId) {
        DeleteStatementProvider deleteStatement = deleteFrom(drugTag)
                .where(drugTag.tagId, isEqualTo(tagId)).build()
                .render(RenderingStrategy.MYBATIS3);
        int result = drugTagMapper.delete(deleteStatement);
        tagMapper.deleteByPrimaryKey(tagId);
        return result;
    }

    public int addNewTag(Long userId, String name) {
        Tag tag = new Tag();
        tag.setName(name);
        tag.setUserId(userId);
        return tagMapper.insert(tag);
    }

    public int updateTagsOfDrug(Long drugId, Long[] tagIds) {
        DeleteStatementProvider deleteStatement = deleteFrom(drugTag)
                .where(drugTag.drugId, isEqualTo(drugId)).build()
                .render(RenderingStrategy.MYBATIS3);
        drugTagMapper.delete(deleteStatement);
        List<DrugTag> drugTags = new ArrayList<>();
        for (Long tadId :tagIds){
            DrugTag drugTag = new DrugTag();
            drugTag.setDrugId(drugId);
            drugTag.setTagId(tadId);
            drugTags.add(drugTag);
        }
        return drugTagMapper.insertMultiple(drugTags);
    }
}
