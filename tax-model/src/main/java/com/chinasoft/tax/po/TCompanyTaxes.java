package com.chinasoft.tax.po;

import javax.persistence.*;

@Table(name = "t_company_taxes")
public class TCompanyTaxes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "company_id")
    private String companyId;

    @Column(name = "company_name")
    private String companyName;

    /**
     * 税种id---字典表到id
     */
    @Column(name = "dict_id")
    private String dictId;

    @Column(name = "dict_name")
    private String dictName;

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
     * @return company_id
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * @param companyId
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    /**
     * @return company_name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 获取税种id---字典表到id
     *
     * @return dict_id - 税种id---字典表到id
     */
    public String getDictId() {
        return dictId;
    }

    /**
     * 设置税种id---字典表到id
     *
     * @param dictId 税种id---字典表到id
     */
    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    /**
     * @return dict_name
     */
    public String getDictName() {
        return dictName;
    }

    /**
     * @param dictName
     */
    public void setDictName(String dictName) {
        this.dictName = dictName;
    }
}