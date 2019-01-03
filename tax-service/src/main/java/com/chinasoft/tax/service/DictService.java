package com.chinasoft.tax.service;

import com.chinasoft.tax.vo.DictVo;
import com.chinasoft.tax.vo.MyPageInfo;
import com.chinasoft.tax.vo.PageVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DictService {

    /**
     * 分页查询字典信息
     * @param pageVo
     * @return
     */
    MyPageInfo<DictVo> findCondition(PageVo pageVo, DictVo dictVo);

    /**
     * 获取该type下的所有字典
     * @param typeId 0:国家，1：币种    2：税种  3：纳税期限表
     * @return
     */
    List<DictVo> getByTypeId(Integer typeId);

    /**
     * 添加字典信息
     * @param dictVo
     */
    void add(DictVo dictVo);

    /**
     * 修改字典
     * @param dictVo
     */
    void edit(DictVo dictVo);


    /**
     * 删除字典
     * @param id
     */
    void del(String id);
}
