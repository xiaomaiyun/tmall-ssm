package com.cjw.common;



import java.io.Serializable;
import java.util.List;

/**
 * @author codeAC
 * @Date: 2018/7/4
 * @Time: 10:02
 */
public class EasyUIDataGridResult implements Serializable {
    private long total;
    private List rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
