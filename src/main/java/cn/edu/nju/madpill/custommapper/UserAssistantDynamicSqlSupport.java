package cn.edu.nju.madpill.custommapper;

import cn.edu.nju.madpill.domain.User;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;

import static cn.edu.nju.madpill.mapper.UserDynamicSqlSupport.*;

/**
 * <p>
 * <p>
 *
 * @author Charles
 * @date 2020/2/24
 */
public final class UserAssistantDynamicSqlSupport {

    private UserAssistantDynamicSqlSupport() {
    }

    public static InsertStatementProvider<User> buildInsert(User record) {
        return SqlBuilder.insert(record)
                .into(user)
                .map(id).toProperty("id")
                .map(openId).toProperty("openId")
                .map(createdAt).toProperty("createdAt")
                .build()
                .render(RenderingStrategies.MYBATIS3);
    }
}
