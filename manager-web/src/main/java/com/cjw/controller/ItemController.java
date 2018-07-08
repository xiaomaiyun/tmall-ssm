package com.cjw.controller;

import com.cjw.common.EasyUIDataGridResult;
import com.cjw.common.utils.ItemResult;
import com.cjw.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cjw.pojo.TbItem;

/**
 * @author codeAC
 * @Date: 2018/7/1
 * @Time: 18:24
 */
@Controller
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public String getItemById(@PathVariable Long itemId) {
        TbItem tbItem = itemService.getItemById(itemId);
        return tbItem.toString();
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page,Integer rows){
        return itemService.getItemList(page, rows);
    }

    /**
     * 插入商品
     */
    @RequestMapping("/item/save")
    @ResponseBody
    public ItemResult addItem(TbItem item, String desc) {
        return itemService.addItem(item, desc);
    }

    @RequestMapping("/rest/item/delete")
    @ResponseBody
    public ItemResult deleteItem(Long[] ids) {
        return itemService.deleteItem(ids);
    }

    @RequestMapping("/rest/item/instock")
    @ResponseBody
    public ItemResult instockItem(Long[] ids) {
        return itemService.instock(ids);
    }

    @RequestMapping("/rest/item/reshelf")
    @ResponseBody
    public ItemResult reshelfItem(Long[] ids) {
        return itemService.reshelf(ids);
    }

    @RequestMapping("/rest/item/query/item/desc/{id}")
    @ResponseBody
    public ItemResult queryItem(@PathVariable()long id) {
        return itemService.queryItem(id);
    }

    @RequestMapping("/rest/item/param/item/query/{id}")
    @ResponseBody
    public ItemResult queryItemDesc(@PathVariable()long id) {
        return itemService.queryItemDesc(id);
    }

    @RequestMapping("/rest/page/item-edit")
    public String itemEdit(){
        return "item-edit";
    }

    @RequestMapping("/rest/item/update")
    @ResponseBody
    public ItemResult updateItem(TbItem item, String desc) {
        return itemService.updateItem(item,desc);
    }
}
