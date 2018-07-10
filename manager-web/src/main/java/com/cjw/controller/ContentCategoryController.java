package com.cjw.controller;

import com.cjw.common.EasyUITreeNode;
import com.cjw.common.utils.OperationResult;
import com.cjw.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author codeAC
 * @Date: 2018/7/9
 * @Time: 16:14
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
    private final ContentCategoryService contentCategoryService;

    @Autowired
    public ContentCategoryController(ContentCategoryService contentCategoryService) {
        this.contentCategoryService = contentCategoryService;
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCategory(@RequestParam(value = "id", defaultValue = "0") long parentId) {
        return contentCategoryService.getCOntentList(parentId);
    }

    @RequestMapping("/create")
    @ResponseBody
    public OperationResult createCategory(long parentId, String name) {
        return contentCategoryService.addContentCategory(parentId, name);
    }

    @RequestMapping("/update")
    @ResponseBody
    public OperationResult updateContentCategory(long id, String name) {
        return contentCategoryService.updateContentCategory(id, name);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public OperationResult deleteContentCategory(long id) {
        return contentCategoryService.deleteContentCategory(id);
    }

}
