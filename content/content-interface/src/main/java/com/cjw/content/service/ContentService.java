package com.cjw.content.service;

import com.cjw.common.EasyUIDataGridResult;
import com.cjw.common.utils.OperationResult;
import com.cjw.pojo.TbContent;

import java.util.List;

/**
 * @author codeAC
 * @Date: 2018/7/9
 * @Time: 19:54
 */
public interface ContentService {
    EasyUIDataGridResult getContentList(long categoryId,int page,int rows);

    List<TbContent> getContentById(long cid);

    OperationResult addCategory(TbContent content);
}
