package com.cjw.content.service;

import com.cjw.common.EasyUITreeNode;
import com.cjw.common.utils.OperationResult;

import java.util.List;

/**
 * @author codeAC
 * @Date: 2018/7/9
 * @Time: 11:19
 */
public interface ContentCategoryService {
    List<EasyUITreeNode> getCOntentList(long parentId);

    OperationResult addContentCategory(long parentId, String name);

    OperationResult updateContentCategory(long id, String name);

    OperationResult deleteContentCategory(long id);
}
