package cn.edu.nju.madpill.domain;

import java.time.LocalDate;
import javax.annotation.Generated;

public class Drug {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private LocalDate producedDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private LocalDate expireDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer qualityPeriod;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String qualityPeriodUnit;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String description;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long userId;

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
    public LocalDate getProducedDate() {
        return producedDate;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setProducedDate(LocalDate producedDate) {
        this.producedDate = producedDate;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public LocalDate getExpireDate() {
        return expireDate;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getQualityPeriod() {
        return qualityPeriod;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setQualityPeriod(Integer qualityPeriod) {
        this.qualityPeriod = qualityPeriod;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getQualityPeriodUnit() {
        return qualityPeriodUnit;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setQualityPeriodUnit(String qualityPeriodUnit) {
        this.qualityPeriodUnit = qualityPeriodUnit == null ? null : qualityPeriodUnit.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getDescription() {
        return description;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getUserId() {
        return userId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUserId(Long userId) {
        this.userId = userId;
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