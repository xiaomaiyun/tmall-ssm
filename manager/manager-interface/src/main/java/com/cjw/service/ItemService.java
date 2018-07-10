package com.cjw.service;

import com.cjw.common.EasyUIDataGridResult;
import com.cjw.common.utils.OperationResult;
import com.cjw.pojo.TbItem;

/**
 * @author codeAC
 * @Date: 2018/7/1
 * @Time: 18:05
 */
public interface ItemService {
    TbItem getItemById(long id);

    EasyUIDataGridResult getItemList(int page, int rows);

    OperationResult addItem(TbItem item, String desc);

    OperationResult updateItem(TbItem item, String desc);

    OperationResult deleteItem(Long[] ids);

    OperationResult instock(Long[] ids);

    OperationResult reshelf(Long[] ids);

    OperationResult queryItemDesc(long id);

    OperationResult queryItem(long id);
}
