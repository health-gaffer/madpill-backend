package cn.edu.nju.madpill.custommapper;

import cn.edu.nju.madpill.domain.Drug;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;

import static cn.edu.nju.madpill.mapper.DrugDynamicSqlSupport.*;

/**
 * <p>
 * <p>
 *
 * @author Charles
 * @date 2020/2/16
 */
public final class DrugAssistantDynamicSqlSupport {

    private DrugAssistantDynamicSqlSupport() {
    }

    public static InsertStatementProvider<Drug> buildInsert(Drug record) {
        return SqlBuilder.insert(record)
                .into(drug)
                .map(id).toProperty("id")
                .map(name).toProperty("name")
                .map(producedDate).toProperty("producedDate")
                .map(expireDate).toProperty("expireDate")
                .map(description).toProperty("description")
                .map(userId).toProperty("userId")
                .map(indication).toProperty("indication")
                .map(contraindication).toProperty("contraindication")
                .build()
                .render(RenderingStrategies.MYBATIS3);
    }
}
