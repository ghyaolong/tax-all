package com.chinasoft.tax.po;

import javax.persistence.*;

@Table(name = "t_sys_config")
public class TSysConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 键
     */
    private String property;

    /**
     * 键的名称
     */
    @Column(name = "property_name")
    private String propertyName;

    /**
     * 值
     */
    @Column(name = "property_value")
    private String propertyValue;

    /**
     * 单位
     */
    private String unit;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取键
     *
     * @return property - 键
     */
    public String getProperty() {
        return property;
    }

    /**
     * 设置键
     *
     * @param property 键
     */
    public void setProperty(String property) {
        this.property = property;
    }

    /**
     * 获取键的名称
     *
     * @return property_name - 键的名称
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * 设置键的名称
     *
     * @param propertyName 键的名称
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    /**
     * 获取值
     *
     * @return property_value - 值
     */
    public String getPropertyValue() {
        return propertyValue;
    }

    /**
     * 设置值
     *
     * @param propertyValue 值
     */
    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    /**
     * 获取单位
     *
     * @return unit - 单位
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置单位
     *
     * @param unit 单位
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }
}