package cn.edu.nju.madpill.mapper;

import static cn.edu.nju.madpill.mapper.SearchingDrugDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import cn.edu.nju.madpill.domain.SearchingDrug;
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
public interface SearchingDrugMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, name, manufactor, indication, contraindication);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<SearchingDrug> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<SearchingDrug> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SearchingDrugResult")
    Optional<SearchingDrug> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SearchingDrugResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="manufactor", property="manufactor", jdbcType=JdbcType.VARCHAR),
        @Result(column="indication", property="indication", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="contraindication", property="contraindication", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<SearchingDrug> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, searchingDrug, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, searchingDrug, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(SearchingDrug record) {
        return MyBatis3Utils.insert(this::insert, record, searchingDrug, c ->
            c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(manufactor).toProperty("manufactor")
            .map(indication).toProperty("indication")
            .map(contraindication).toProperty("contraindication")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<SearchingDrug> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, searchingDrug, c ->
            c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(manufactor).toProperty("manufactor")
            .map(indication).toProperty("indication")
            .map(contraindication).toProperty("contraindication")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(SearchingDrug record) {
        return MyBatis3Utils.insert(this::insert, record, searchingDrug, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(manufactor).toPropertyWhenPresent("manufactor", record::getManufactor)
            .map(indication).toPropertyWhenPresent("indication", record::getIndication)
            .map(contraindication).toPropertyWhenPresent("contraindication", record::getContraindication)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<SearchingDrug> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, searchingDrug, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<SearchingDrug> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, searchingDrug, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<SearchingDrug> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, searchingDrug, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<SearchingDrug> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, searchingDrug, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(SearchingDrug record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(name).equalTo(record::getName)
                .set(manufactor).equalTo(record::getManufactor)
                .set(indication).equalTo(record::getIndication)
                .set(contraindication).equalTo(record::getContraindication);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(SearchingDrug record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(name).equalToWhenPresent(record::getName)
                .set(manufactor).equalToWhenPresent(record::getManufactor)
                .set(indication).equalToWhenPresent(record::getIndication)
                .set(contraindication).equalToWhenPresent(record::getContraindication);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(SearchingDrug record) {
        return update(c ->
            c.set(name).equalTo(record::getName)
            .set(manufactor).equalTo(record::getManufactor)
            .set(indication).equalTo(record::getIndication)
            .set(contraindication).equalTo(record::getContraindication)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(SearchingDrug record) {
        return update(c ->
            c.set(name).equalToWhenPresent(record::getName)
            .set(manufactor).equalToWhenPresent(record::getManufactor)
            .set(indication).equalToWhenPresent(record::getIndication)
            .set(contraindication).equalToWhenPresent(record::getContraindication)
            .where(id, isEqualTo(record::getId))
        );
    }
}