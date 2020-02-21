package cn.edu.nju.madpill.service;

import cn.edu.nju.madpill.custommapper.DrugAssistantMapper;
import cn.edu.nju.madpill.domain.Drug;
import cn.edu.nju.madpill.domain.Tag;
import cn.edu.nju.madpill.domain.User;
import cn.edu.nju.madpill.dto.DrugBriefDTO;
import cn.edu.nju.madpill.dto.DrugDTO;
import cn.edu.nju.madpill.dto.DrugsListDTO;
import cn.edu.nju.madpill.dto.TagDTO;
import cn.edu.nju.madpill.exception.ExceptionSuppliers;
import cn.edu.nju.madpill.mapper.DrugMapper;
import cn.edu.nju.madpill.mapper.TagMapper;
import org.modelmapper.ModelMapper;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static cn.edu.nju.madpill.custommapper.DrugAssistantDynamicSqlSupport.buildInsert;
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

    /**
     * 默认需要提醒的即将过期的时间
     */
    private static final int EXPIRING_DAY = 15;

    private final TagService tagService;
    private final DrugMapper drugMapper;
    private final TagMapper tagMapper;
    private final DrugAssistantMapper drugAssistantMapper;
    private final ModelMapper modelMapper;

    public DrugService(TagService tagService, DrugMapper drugMapper, TagMapper tagMapper, DrugAssistantMapper drugAssistantMapper, ModelMapper modelMapper) {
        this.tagService = tagService;
        this.drugMapper = drugMapper;
        this.tagMapper = tagMapper;
        this.drugAssistantMapper = drugAssistantMapper;
        this.modelMapper = modelMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    public void createNewDrug(DrugDTO dto, User curUser) {
        Drug newDrug = new Drug();
        modelMapper.map(dto, newDrug);

        // TODO group_id
        newDrug.setGroupId(1L);
        newDrug.setUserId(curUser.getId());
        drugAssistantMapper.insert(buildInsert(newDrug));
        tagService.updateTagsOfDrug(newDrug.getId(), getTagIdsOfDrug(dto));
    }

    public DrugDTO getDrugDetail(Long drugId, User curUser) {
        Drug drugInfo = drugMapper.selectByPrimaryKey(drugId).orElseThrow(ExceptionSuppliers.DRUG_NOT_FOUND);

        if (drugInfo.getUserId().equals(curUser.getId())) {
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
        } else {
            throw ExceptionSuppliers.PERMISSION_DENIED.get();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void modifyDrug(DrugDTO dto, User curUser) {
        Optional<Drug> toModify = drugMapper.selectByPrimaryKey(dto.getId());
        if (toModify.isPresent() && toModify.get().getUserId().equals(curUser.getId())) {
            Drug modifiedDrug = new Drug();
            modelMapper.map(dto, modifiedDrug);

            // TODO group_id
            modifiedDrug.setGroupId(1L);
            modifiedDrug.setUserId(curUser.getId());
            drugMapper.updateByPrimaryKey(modifiedDrug);
            tagService.updateTagsOfDrug(modifiedDrug.getId(), getTagIdsOfDrug(dto));
        } else {
            throw ExceptionSuppliers.PERMISSION_DENIED.get();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteDrug(Long drugId, User curUser) {
        Drug toDelete = drugMapper.selectByPrimaryKey(drugId).orElseThrow(ExceptionSuppliers.DRUG_NOT_FOUND);
        if (toDelete.getUserId().equals(curUser.getId())) {
            drugMapper.deleteByPrimaryKey(drugId);
        } else {
            throw ExceptionSuppliers.PERMISSION_DENIED.get();
        }
    }

    public DrugsListDTO getUserDrugs(Long userId) {
        SelectStatementProvider drugsSelectStatement = select(drug.id.as("drug_id"), drug.name.as("drug_name"), drug.expireDate.as("expireDate"),
                tag.id.as("tag_id"), tag.name.as("tag_name"))
                .from(user)
                .join(drug, on(user.id, equalTo(drug.userId)))
                .leftJoin(drugTag, on(drug.id, equalTo(drugTag.drugId)))
                .leftJoin(tag, on(drugTag.tagId, equalTo(tag.id)))
                .where(user.id, isEqualTo(userId))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        List<DrugBriefDTO> dtos = drugAssistantMapper.selectDrugs(drugsSelectStatement);

        final LocalDate today = LocalDate.now();
        dtos.forEach(dto -> {
            if (today.isAfter(dto.getExpireDate())) {
                System.out.println("expired");
                dto.setDay(dto.getExpireDate().until(today, ChronoUnit.DAYS));
            } else if (today.plusDays(EXPIRING_DAY).isAfter(dto.getExpireDate())) {
                System.out.println("expiring");
                dto.setDay(today.until(dto.getExpireDate(), ChronoUnit.DAYS) + 1);
            } else {
                System.out.println("notExpired");
            }
        });

        Map<String, List<DrugBriefDTO>> dtoMap = dtos.stream()
                .collect(Collectors.groupingBy(dto -> {
                    if (today.isAfter(dto.getExpireDate())) {
                        return "expired";
                    } else if (today.plusDays(EXPIRING_DAY).isAfter(dto.getExpireDate())) {
                        return "expiring";
                    } else {
                        return "notExpired";
                    }
                }));
        return DrugsListDTO.builder()
                .expired(dtoMap.getOrDefault("expired", new ArrayList<>()))
                .expiring(dtoMap.getOrDefault("expiring", new ArrayList<>()))
                .notExpired(dtoMap.getOrDefault("notExpired", new ArrayList<>()))
                .build();
    }

    private Long[] getTagIdsOfDrug(DrugDTO dto) {
        if (null == dto.getTags()) {
            return new Long[0];
        }
        Long[] tagIds = new Long[dto.getTags().size()];
        List<TagDTO> dtoTags = dto.getTags();
        for (int i = 0; i < dtoTags.size(); i++) {
            tagIds[i] = dtoTags.get(i).getId();
        }
        return tagIds;
    }
}
