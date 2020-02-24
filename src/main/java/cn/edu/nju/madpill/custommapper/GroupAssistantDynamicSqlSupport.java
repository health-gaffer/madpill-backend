package cn.edu.nju.madpill.custommapper;

import cn.edu.nju.madpill.domain.Group;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;

import static cn.edu.nju.madpill.mapper.GroupDynamicSqlSupport.*;

/**
 * <p>
 * <p>
 *
 * @author gyyyy
 * @date 2020/2/24
 */
public final class GroupAssistantDynamicSqlSupport {

    private GroupAssistantDynamicSqlSupport() {
    }

    public static InsertStatementProvider<Group> buildInsert(Group record) {
        return SqlBuilder.insert(record)
                .into(group)
                .map(id).toProperty("id")
                .map(name).toProperty("name")
                .map(createAt).toProperty("createAt")
                .map(createBy).toProperty("createBy")
                .map(canDelete).toProperty("canDelete")
                .build()
                .render(RenderingStrategies.MYBATIS3);
    }
}
