package com.cjw.service.impl;

import com.cjw.common.EasyUIDataGridResult;
import com.cjw.common.utils.IDUtils;
import com.cjw.common.utils.ItemResult;
import com.cjw.mapper.TbItemDescMapper;
import com.cjw.mapper.TbItemMapper;
import com.cjw.pojo.TbItemDesc;
import com.cjw.pojo.TbItemExample;
import com.cjw.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cjw.pojo.TbItem;

import java.util.Date;
import java.util.List;

/**
 * @author codeAC
 * @Date: 2018/7/1
 * @Time: 18:09
 */
@Service
public class ItemServiceImpl implements ItemService {
    private final TbItemMapper itemMapper;
    private final TbItemDescMapper itemDescMapper;
    @Autowired
    public ItemServiceImpl(TbItemMapper itemMapper, TbItemDescMapper itemDescMapper) {
        this.itemMapper = itemMapper;
        this.itemDescMapper = itemDescMapper;
    }



    public TbItem getItemById(long id) {
        return itemMapper.selectByPrimaryKey(id);
    }


    public EasyUIDataGridResult getItemList(int page, int rows) {
        //设置分页信息
        PageHelper.startPage(page, rows);
        //执行查询
        TbItemExample itemExample = new TbItemExample();
        List<TbItem> itemList  = itemMapper.selectByExample(itemExample);
        //创建一个返回值对象
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(itemList);
        //取分页结果
        PageInfo<TbItem> itemPageInfo = new PageInfo<TbItem>(itemList);
        long total = itemPageInfo.getTotal();
        result.setTotal(total);
        return result;
    }

    public ItemResult addItem(TbItem item, String desc) {
        long itemId = IDUtils.genItemId();
        item.setId(itemId);
        //1-正常，2-下架，3-删除
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        itemMapper.insert(item);
        //插入商品描述
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDescMapper.insert(itemDesc);
        return ItemResult.ok();
    }


    //删除商品
    public ItemResult deleteItem(Long[] ids) {
        for (int i = 0; i < ids.length; i++) {
            itemMapper.deleteByPrimaryKey(ids[i]);
            itemDescMapper.deleteByPrimaryKey(ids[i]);
        }
        return ItemResult.ok();
    }

    //下架商品
    public ItemResult instock(Long[] ids) {
        for (int i = 0; i < ids.length; i++) {
            TbItem item = itemMapper.selectByPrimaryKey(ids[i]);
            item.setStatus((byte) 2);
            itemMapper.updateByPrimaryKey(item);
        }
        return ItemResult.ok();
    }

    //上架商品
    public ItemResult reshelf(Long[] ids) {
        for (int i = 0; i < ids.length; i++) {
            TbItem item = itemMapper.selectByPrimaryKey(ids[i]);
            item.setStatus((byte) 1);
            itemMapper.updateByPrimaryKey(item);
        }
        return ItemResult.ok();
    }

    //修改商品信息
    public ItemResult queryItemDesc(long id) {
        return ItemResult.ok(itemDescMapper.selectByPrimaryKey(id));
    }

    public ItemResult queryItem(long id) {
        return ItemResult.ok(itemMapper.selectByPrimaryKey(id));
    }

    public ItemResult updateItem(TbItem item, String desc) {
        item.setUpdated(new Date());
        itemMapper.updateByPrimaryKey(item);
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemDesc(desc);
        itemDesc.setUpdated(new Date());
        itemDescMapper.updateByPrimaryKey(itemDesc);
        return ItemResult.ok();
    }

}
