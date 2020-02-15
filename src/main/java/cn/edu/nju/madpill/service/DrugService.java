package cn.edu.nju.madpill.service;

import cn.edu.nju.madpill.custommapper.AssistantMapper;
import cn.edu.nju.madpill.domain.Drug;
import cn.edu.nju.madpill.domain.Tag;
import cn.edu.nju.madpill.dto.DrugBriefDTO;
import cn.edu.nju.madpill.dto.DrugDTO;
import cn.edu.nju.madpill.dto.TagDTO;
import cn.edu.nju.madpill.exception.ExceptionSuppliers;
import cn.edu.nju.madpill.mapper.DrugMapper;
import cn.edu.nju.madpill.mapper.TagMapper;
import org.modelmapper.ModelMapper;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static cn.edu.nju.madpill.mapper.DrugDynamicSqlSupport.drug;
import static cn.edu.nju.madpill.mapper.DrugTagDynamicSqlSupport.drugTag;
import static cn.edu.nju.madpill.mapper.TagDynamicSqlSupport.tag;
import static cn.edu.nju.madpill.mapper.UserDynamicSqlSupport.user;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * <p>
 * <p>
 *
 * @author Shenmiu
 * @date 2020/2/6
 */
@Service
public class DrugService {

    private final DrugMapper drugMapper;
    private final TagMapper tagMapper;
    private final AssistantMapper assistantMapper;
    private final ModelMapper modelMapper;

    public DrugService(DrugMapper drugMapper, TagMapper tagMapper, AssistantMapper assistantMapper, ModelMapper modelMapper) {
        this.drugMapper = drugMapper;
        this.tagMapper = tagMapper;
        this.assistantMapper = assistantMapper;
        this.modelMapper = modelMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    public void createNewDrug(DrugDTO dto) {
        Drug newDrug = new Drug();
        modelMapper.map(dto, newDrug);

        // TODO user_id tags
        newDrug.setUserId(1L);
        drugMapper.insert(newDrug);
    }

    public DrugDTO getDrugDetail(Long drugId) {
        Drug drugInfo = drugMapper.selectByPrimaryKey(drugId).orElseThrow(ExceptionSuppliers.DRUG_NOT_FOUND);
        DrugDTO drugDTO = new DrugDTO();
        modelMapper.map(drugInfo, drugDTO);

        SelectStatementProvider selectStatement = select(tag.id, tag.name)
                .from(drugTag)
                .join(tag).on(drugTag.tagId, equalTo(tag.id))
                .where(drugTag.drugId, isEqualTo(drugId))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<Tag> tags = tagMapper.selectMany(selectStatement);

        List<TagDTO> tagDTOS = tags.stream().map(tag -> {
            TagDTO cur = new TagDTO();
            modelMapper.map(tag, cur);
            return cur;
        }).collect(Collectors.toList());

        drugDTO.setTags(tagDTOS);
        return drugDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    public void modifyDrug(DrugDTO dto) {
        Drug modifiedDrug = new Drug();
        modelMapper.map(dto, modifiedDrug);

        // TODO user_id tags
        modifiedDrug.setUserId(1L);
        drugMapper.updateByPrimaryKey(modifiedDrug);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteDrug(Long drugId) {
        int row = drugMapper.deleteByPrimaryKey(drugId);
        if (0 == row) {
            throw ExceptionSuppliers.DRUG_NOT_FOUND.get();
        }
    }

    public List<DrugBriefDTO> getUserDrugs(Long userId) {
        SelectStatementProvider drugsSelectStatement = select(drug.id.as("drug_id"), drug.name.as("drug_name"), drug.expireDate.as("expireDate"),
                tag.id.as("tag_id"), tag.name.as("tag_name"))
                .from(user)
                .join(drug, on(user.id, equalTo(drug.userId)))
                .join(drugTag, on(drug.id, equalTo(drugTag.drugId)))
                .join(tag, on(drugTag.tagId, equalTo(tag.id)))
                .where(user.id, isEqualTo(userId))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        return assistantMapper.selectDrugs(drugsSelectStatement);
    }
}
