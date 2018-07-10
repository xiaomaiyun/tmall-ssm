package com.cjw.controller;

import com.cjw.common.EasyUIDataGridResult;
import com.cjw.common.utils.OperationResult;
import com.cjw.content.service.ContentService;
import com.cjw.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author codeAC
 * @Date: 2018/7/9
 * @Time: 22:01
 */
@RestController
public class ContentController {
    private final ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @RequestMapping("/content/query/list")
    public EasyUIDataGridResult getContentList(long categoryId, int page, int rows) {
        return contentService.getContentList(categoryId, page, rows);
    }
    @RequestMapping("/content/save")
    public OperationResult addContent(TbContent content) {
        return contentService.addCategory(content);
    }
}
