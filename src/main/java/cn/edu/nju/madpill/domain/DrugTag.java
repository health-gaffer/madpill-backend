package cn.edu.nju.madpill.domain;

import javax.annotation.Generated;

public class DrugTag {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long drugId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long tagId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getDrugId() {
        return drugId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setDrugId(Long drugId) {
        this.drugId = drugId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getTagId() {
        return tagId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }
}