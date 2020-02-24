package cn.edu.nju.madpill.custommapper;

import cn.edu.nju.madpill.domain.Group;
import cn.edu.nju.madpill.domain.Tag;
import cn.edu.nju.madpill.dto.GroupBriefDTO;
import org.apache.ibatis.annotations.*;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
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

    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "record.id")
    Long insert(InsertStatementProvider<Group> insertStatement);
}
