package cn.edu.nju.madpill.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class DrugTagDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final DrugTag drugTag = new DrugTag();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> drugId = drugTag.drugId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> tagId = drugTag.tagId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class DrugTag extends SqlTable {
        public final SqlColumn<Long> drugId = column("drug_id", JDBCType.BIGINT);

        public final SqlColumn<Long> tagId = column("tag_id", JDBCType.BIGINT);

        public DrugTag() {
            super("drug_tag");
        }
    }
}