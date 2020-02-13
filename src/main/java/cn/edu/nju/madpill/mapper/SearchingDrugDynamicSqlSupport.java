package cn.edu.nju.madpill.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SearchingDrugDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SearchingDrug searchingDrug = new SearchingDrug();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = searchingDrug.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = searchingDrug.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> manufactor = searchingDrug.manufactor;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> indication = searchingDrug.indication;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> contraindication = searchingDrug.contraindication;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class SearchingDrug extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> manufactor = column("manufactor", JDBCType.VARCHAR);

        public final SqlColumn<String> indication = column("indication", JDBCType.LONGVARCHAR);

        public final SqlColumn<String> contraindication = column("contraindication", JDBCType.LONGVARCHAR);

        public SearchingDrug() {
            super("warehouse");
        }
    }
}