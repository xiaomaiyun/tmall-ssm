package com.cjw.vo;

import com.cjw.pojo.TbItem;

/**
 * @author codeAC
 * @Date: 2018/7/8
 * @Time: 18:56
 */
public class QueryVo {
    private TbItem item;
    private Integer[] ids;

    public TbItem getItem() {
        return item;
    }

    public void setItem(TbItem item) {
        this.item = item;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
}
