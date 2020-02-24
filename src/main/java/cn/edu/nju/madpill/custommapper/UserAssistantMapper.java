package cn.edu.nju.madpill.custommapper;

import cn.edu.nju.madpill.domain.User;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;

/**
 * <p>
 * <p>
 *
 * @author Charles
 * @date 2020/2/24
 */
@Mapper
public interface UserAssistantMapper {

    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "record.id")
    Long insert(InsertStatementProvider<User> insertStatement);
}
