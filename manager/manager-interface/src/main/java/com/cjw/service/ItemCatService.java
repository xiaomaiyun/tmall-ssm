package com.cjw.service;

import com.cjw.common.EasyUITreeNode;

import java.util.List;

/**
 * @author codeAC
 * @Date: 2018/7/5
 * @Time: 8:41
 */
public interface ItemCatService {
    List<EasyUITreeNode> getItemCatList(long parentId);
}
