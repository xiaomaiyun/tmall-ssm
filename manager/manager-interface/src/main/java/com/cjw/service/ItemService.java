package com.cjw.service;

import com.cjw.common.EasyUIDataGridResult;
import com.cjw.common.utils.ItemResult;
import com.cjw.pojo.TbItem;

/**
 * @author codeAC
 * @Date: 2018/7/1
 * @Time: 18:05
 */
public interface ItemService {
    TbItem getItemById(long id);

    EasyUIDataGridResult getItemList(int page, int rows);

    ItemResult addItem(TbItem item, String desc);

    ItemResult updateItem(TbItem item, String desc);

    ItemResult deleteItem(Long[] ids);

    ItemResult instock(Long[] ids);

    ItemResult reshelf(Long[] ids);

    ItemResult queryItemDesc(long id);

    ItemResult queryItem(long id);
}
