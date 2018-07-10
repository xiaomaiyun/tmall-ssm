package com.cjw.content.service.impl;

import com.cjw.common.EasyUIDataGridResult;
import com.cjw.common.utils.OperationResult;
import com.cjw.content.service.ContentService;
import com.cjw.mapper.TbContentMapper;
import com.cjw.pojo.TbContent;
import com.cjw.pojo.TbContentExample;
import com.cjw.pojo.TbItem;
import com.cjw.pojo.TbItemExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author codeAC
 * @Date: 2018/7/9
 * @Time: 21:49
 */
@Service
public class ContentServiceImpl implements ContentService {
    private final TbContentMapper contentMapper;

    @Autowired
    public ContentServiceImpl(TbContentMapper contentMapper) {
        this.contentMapper = contentMapper;
    }

    public EasyUIDataGridResult getContentList(long categoryId,int page,int rows) {
        //设置分页信息
        PageHelper.startPage(page, rows);
        //执行查询
        TbContentExample contentExample = new TbContentExample();
        contentExample.createCriteria().andCategoryIdEqualTo(categoryId);
        List<TbContent> contentList  = contentMapper.selectByExample(contentExample);
        //创建一个返回值对象
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(contentList);
        //取分页结果
        PageInfo<TbContent> itemPageInfo = new PageInfo<TbContent>(contentList);
        long total = itemPageInfo.getTotal();
        result.setTotal(total);
        return result;
    }

    public List<TbContent> getContentById(long cid) {
        TbContentExample contentExample = new TbContentExample();
        contentExample.createCriteria().andCategoryIdEqualTo(cid);
        return contentMapper.selectByExample(contentExample);
    }

    public OperationResult addCategory(TbContent content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insert(content);
        return OperationResult.ok();
    }
}
