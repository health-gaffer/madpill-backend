package cn.edu.nju.madpill.custommapper;

import cn.edu.nju.madpill.dto.GroupBriefDTO;
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
 * @author Charles
 * @date 2020/2/21
 */
@Mapper
public interface UserAssistantMapper {

    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("GroupBrief")
    List<GroupBriefDTO> selectGroups(SelectStatementProvider selectStatement);
}
