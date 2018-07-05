package com.cjw.service.impl;

import com.cjw.common.EasyUITreeNode;
import com.cjw.mapper.TbItemCatMapper;
import com.cjw.pojo.TbItemCat;
import com.cjw.pojo.TbItemCatExample;
import com.cjw.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author codeAC
 * @Date: 2018/7/5
 * @Time: 8:47
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    private final TbItemCatMapper tbItemCatMapper;

    @Autowired
    public ItemCatServiceImpl(TbItemCatMapper tbItemCatMapper) {
        this.tbItemCatMapper = tbItemCatMapper;
    }

    public List<EasyUITreeNode> getItemCatList(long parentId) {
        //根据parentId查寻子节点列表
        TbItemCatExample itemCatExample = new TbItemCatExample();
        itemCatExample.createCriteria().andParentIdEqualTo(parentId);
        List<TbItemCat> itemCatList = tbItemCatMapper.selectByExample(itemCatExample);
        //创建返回结果List并把列表转换成EasyUITreeNode列表
        List<EasyUITreeNode> treeNodeList = new ArrayList<EasyUITreeNode>();
        for (TbItemCat tbItemCat : itemCatList) {
            EasyUITreeNode treeNode = new EasyUITreeNode();
            treeNode.setId(tbItemCat.getId());
            treeNode.setText(tbItemCat.getName());
            treeNode.setState(tbItemCat.getIsParent()?"closed":"open");
            treeNodeList.add(treeNode);
        }
        return treeNodeList;
    }
}
