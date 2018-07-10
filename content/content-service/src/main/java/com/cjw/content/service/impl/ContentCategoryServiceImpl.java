package com.cjw.content.service.impl;

import com.cjw.common.EasyUITreeNode;
import com.cjw.common.utils.OperationResult;
import com.cjw.content.service.ContentCategoryService;
import com.cjw.mapper.TbContentCategoryMapper;
import com.cjw.pojo.TbContentCategory;
import com.cjw.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author codeAC
 * @Date: 2018/7/9
 * @Time: 15:54
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    private final TbContentCategoryMapper contentCategoryMapper;

    @Autowired
    public ContentCategoryServiceImpl(TbContentCategoryMapper contentCategoryMapper) {
        this.contentCategoryMapper = contentCategoryMapper;
    }

    //展示内容分类
    public List<EasyUITreeNode> getCOntentList(long parentId) {
        // 根据parentid查询子节点列表
        TbContentCategoryExample example = new TbContentCategoryExample();
        //设置查询条件
        example.createCriteria().andParentIdEqualTo(parentId);
        //执行查询
        List<TbContentCategory> catList = contentCategoryMapper.selectByExample(example);
        //转换成EasyUITreeNode的列表
        List<EasyUITreeNode> nodeList = new ArrayList<EasyUITreeNode>();
        for (TbContentCategory tbContentCategory : catList) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbContentCategory.getId());
            node.setText(tbContentCategory.getName());
            node.setState(tbContentCategory.getIsParent()?"closed":"open");
            //添加到列表
            nodeList.add(node);
        }
        return nodeList;
    }
    //新增内容分类节点
    public OperationResult addContentCategory(long parentId, String name) {
        //创建一个tb_content_category表对应的pojo对象
        TbContentCategory contentCategory = new TbContentCategory();
        //设置pojo的属性
        contentCategory.setParentId(parentId);
        contentCategory.setName(name);
        //1(正常),2(删除)
        contentCategory.setStatus(1);
        //默认排序就是1
        contentCategory.setSortOrder(1);
        //新添加的节点一定是叶子节点
        contentCategory.setIsParent(false);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        //插入到数据库
        contentCategoryMapper.insert(contentCategory);
        //判断父节点的isparent属性。如果不是true改为true
        //根据parentid查询父节点
        TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
        if (!parent.getIsParent()) {
            parent.setIsParent(true);
            //更新到数数据库
            contentCategoryMapper.updateByPrimaryKey(parent);
        }
        //返回结果，返回E3Result，包含pojo
        return OperationResult.ok(contentCategory);
    }

    //内容分类重命名
    public OperationResult updateContentCategory(long id, String name) {
        TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        contentCategory.setName(name);
        contentCategoryMapper.updateByPrimaryKey(contentCategory);
        return OperationResult.ok();
    }

    public OperationResult deleteContentCategory(long id) {
        TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        if(contentCategory.getIsParent()){
            return OperationResult.build(404,"Refuse");
        }
        else {
            contentCategoryMapper.deleteByPrimaryKey(id);
            return OperationResult.ok();
        }

    }

}
