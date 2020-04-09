package cn.edu.nju.madpill.custommapper;

import cn.edu.nju.madpill.dto.DrugBriefDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;

import java.util.List;

/**
 * <p>
 * <p>
 *
 * @author Shenmiu
 * @date 2020/2/7
 */
@Mapper
public interface DrugAssistantMapper {

    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("DrugBrief")
    List<DrugBriefDTO> selectDrugs(SelectStatementProvider selectStatement);
}
