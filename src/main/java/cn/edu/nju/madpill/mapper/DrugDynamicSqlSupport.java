package cn.edu.nju.madpill.mapper;

import java.sql.JDBCType;
import java.time.LocalDate;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class DrugDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Drug drug = new Drug();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = drug.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = drug.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<LocalDate> producedDate = drug.producedDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<LocalDate> expireDate = drug.expireDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> qualityPeriod = drug.qualityPeriod;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> qualityPeriodUnit = drug.qualityPeriodUnit;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> description = drug.description;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> userId = drug.userId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> indication = drug.indication;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> contraindication = drug.contraindication;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Drug extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<LocalDate> producedDate = column("produced_date", JDBCType.DATE);

        public final SqlColumn<LocalDate> expireDate = column("expire_date", JDBCType.DATE);

        public final SqlColumn<Integer> qualityPeriod = column("quality_period", JDBCType.INTEGER);

        public final SqlColumn<String> qualityPeriodUnit = column("quality_period_unit", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<Long> userId = column("user_id", JDBCType.BIGINT);

        public final SqlColumn<String> indication = column("indication", JDBCType.LONGVARCHAR);

        public final SqlColumn<String> contraindication = column("contraindication", JDBCType.LONGVARCHAR);

        public Drug() {
            super("drug");
        }
    }
}