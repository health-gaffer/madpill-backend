package cn.edu.nju.madpill.domain;

import javax.annotation.Generated;

public class SearchingDrug {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String manufactor;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String indication;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String contraindication;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getId() {
        return id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getName() {
        return name;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getManufactor() {
        return manufactor;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setManufactor(String manufactor) {
        this.manufactor = manufactor == null ? null : manufactor.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getIndication() {
        return indication;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setIndication(String indication) {
        this.indication = indication == null ? null : indication.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getContraindication() {
        return contraindication;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setContraindication(String contraindication) {
        this.contraindication = contraindication == null ? null : contraindication.trim();
    }
}