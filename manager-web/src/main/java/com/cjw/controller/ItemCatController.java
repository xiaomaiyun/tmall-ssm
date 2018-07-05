package com.cjw.controller;

import com.cjw.common.EasyUITreeNode;
import com.cjw.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author codeAC
 * @Date: 2018/7/5
 * @Time: 9:11
 */
@Controller
public class ItemCatController {
    private final ItemCatService itemCatService;

    @Autowired
    public ItemCatController(ItemCatService itemCatService) {
        this.itemCatService = itemCatService;
    }

    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<EasyUITreeNode> getItemCatList(@RequestParam(value = "id",defaultValue = "0") Long parentId){
        return itemCatService.getItemCatList(parentId);
    }
}
