package cn.edu.nju.madpill.mapper;

import static cn.edu.nju.madpill.mapper.DrugDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import cn.edu.nju.madpill.domain.Drug;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface DrugMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, name, producedDate, expireDate, qualityPeriod, qualityPeriodUnit, description, userId, indication, contraindication);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Drug> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Drug> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("DrugResult")
    Optional<Drug> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="DrugResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="produced_date", property="producedDate", jdbcType=JdbcType.DATE),
        @Result(column="expire_date", property="expireDate", jdbcType=JdbcType.DATE),
        @Result(column="quality_period", property="qualityPeriod", jdbcType=JdbcType.INTEGER),
        @Result(column="quality_period_unit", property="qualityPeriodUnit", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="indication", property="indication", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="contraindication", property="contraindication", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Drug> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, drug, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, drug, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Drug record) {
        return MyBatis3Utils.insert(this::insert, record, drug, c ->
            c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(producedDate).toProperty("producedDate")
            .map(expireDate).toProperty("expireDate")
            .map(qualityPeriod).toProperty("qualityPeriod")
            .map(qualityPeriodUnit).toProperty("qualityPeriodUnit")
            .map(description).toProperty("description")
            .map(userId).toProperty("userId")
            .map(indication).toProperty("indication")
            .map(contraindication).toProperty("contraindication")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<Drug> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, drug, c ->
            c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(producedDate).toProperty("producedDate")
            .map(expireDate).toProperty("expireDate")
            .map(qualityPeriod).toProperty("qualityPeriod")
            .map(qualityPeriodUnit).toProperty("qualityPeriodUnit")
            .map(description).toProperty("description")
            .map(userId).toProperty("userId")
            .map(indication).toProperty("indication")
            .map(contraindication).toProperty("contraindication")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Drug record) {
        return MyBatis3Utils.insert(this::insert, record, drug, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(producedDate).toPropertyWhenPresent("producedDate", record::getProducedDate)
            .map(expireDate).toPropertyWhenPresent("expireDate", record::getExpireDate)
            .map(qualityPeriod).toPropertyWhenPresent("qualityPeriod", record::getQualityPeriod)
            .map(qualityPeriodUnit).toPropertyWhenPresent("qualityPeriodUnit", record::getQualityPeriodUnit)
            .map(description).toPropertyWhenPresent("description", record::getDescription)
            .map(userId).toPropertyWhenPresent("userId", record::getUserId)
            .map(indication).toPropertyWhenPresent("indication", record::getIndication)
            .map(contraindication).toPropertyWhenPresent("contraindication", record::getContraindication)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<Drug> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, drug, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Drug> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, drug, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Drug> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, drug, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<Drug> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, drug, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(Drug record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(name).equalTo(record::getName)
                .set(producedDate).equalTo(record::getProducedDate)
                .set(expireDate).equalTo(record::getExpireDate)
                .set(qualityPeriod).equalTo(record::getQualityPeriod)
                .set(qualityPeriodUnit).equalTo(record::getQualityPeriodUnit)
                .set(description).equalTo(record::getDescription)
                .set(userId).equalTo(record::getUserId)
                .set(indication).equalTo(record::getIndication)
                .set(contraindication).equalTo(record::getContraindication);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Drug record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(name).equalToWhenPresent(record::getName)
                .set(producedDate).equalToWhenPresent(record::getProducedDate)
                .set(expireDate).equalToWhenPresent(record::getExpireDate)
                .set(qualityPeriod).equalToWhenPresent(record::getQualityPeriod)
                .set(qualityPeriodUnit).equalToWhenPresent(record::getQualityPeriodUnit)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(indication).equalToWhenPresent(record::getIndication)
                .set(contraindication).equalToWhenPresent(record::getContraindication);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Drug record) {
        return update(c ->
            c.set(name).equalTo(record::getName)
            .set(producedDate).equalTo(record::getProducedDate)
            .set(expireDate).equalTo(record::getExpireDate)
            .set(qualityPeriod).equalTo(record::getQualityPeriod)
            .set(qualityPeriodUnit).equalTo(record::getQualityPeriodUnit)
            .set(description).equalTo(record::getDescription)
            .set(userId).equalTo(record::getUserId)
            .set(indication).equalTo(record::getIndication)
            .set(contraindication).equalTo(record::getContraindication)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Drug record) {
        return update(c ->
            c.set(name).equalToWhenPresent(record::getName)
            .set(producedDate).equalToWhenPresent(record::getProducedDate)
            .set(expireDate).equalToWhenPresent(record::getExpireDate)
            .set(qualityPeriod).equalToWhenPresent(record::getQualityPeriod)
            .set(qualityPeriodUnit).equalToWhenPresent(record::getQualityPeriodUnit)
            .set(description).equalToWhenPresent(record::getDescription)
            .set(userId).equalToWhenPresent(record::getUserId)
            .set(indication).equalToWhenPresent(record::getIndication)
            .set(contraindication).equalToWhenPresent(record::getContraindication)
            .where(id, isEqualTo(record::getId))
        );
    }
}