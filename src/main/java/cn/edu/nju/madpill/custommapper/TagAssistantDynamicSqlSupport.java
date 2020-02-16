package cn.edu.nju.madpill.custommapper;

import cn.edu.nju.madpill.domain.Tag;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;

import static cn.edu.nju.madpill.mapper.TagDynamicSqlSupport.*;

/**
 * <p>
 * <p>
 *
 * @author Charles
 * @date 2020/2/16
 */
public final class TagAssistantDynamicSqlSupport {

    public static InsertStatementProvider<Tag> buildInsert(Tag record) {
        return SqlBuilder.insert(record)
                .into(tag)
                .map(id).toProperty("id")
                .map(name).toProperty("name")
                .map(userId).toProperty("userId")
                .build()
                .render(RenderingStrategies.MYBATIS3);
    }
}
