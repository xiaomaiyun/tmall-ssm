package com.cjw.content.service.impl;

import com.cjw.common.EasyUIDataGridResult;
import com.cjw.common.jedis.JedisClient;
import com.cjw.common.utils.JsonUtils;
import com.cjw.common.utils.OperationResult;
import com.cjw.content.service.ContentService;
import com.cjw.mapper.TbContentMapper;
import com.cjw.pojo.TbContent;
import com.cjw.pojo.TbContentExample;
import com.cjw.pojo.TbItem;
import com.cjw.pojo.TbItemExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
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
    private final JedisClient jedisClient;
    @Autowired
    public ContentServiceImpl(TbContentMapper contentMapper, JedisClient jedisClient) {
        this.contentMapper = contentMapper;
        this.jedisClient = jedisClient;
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
        //查询缓存
        try {
            String json = jedisClient.hget("CONTENT_KEY", cid + "");
            //判断json是否为空
            if (StringUtils.isNotBlank(json)) {
                //把json转换成list
                List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbContentExample contentExample = new TbContentExample();
        contentExample.createCriteria().andCategoryIdEqualTo(cid);
        List<TbContent> contentList = contentMapper.selectByExample(contentExample);
        try {
            jedisClient.hset("CONTENT_KEY", cid + "", JsonUtils.objectToJson(contentList));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentList;
    }

    public OperationResult addContent(TbContent content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insert(content);
        return OperationResult.ok();
    }

    public OperationResult updateContent(TbContent content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.updateByPrimaryKey(content);
        return OperationResult.ok();
    }

    public OperationResult deleteContents(long[] ids) {
        for (long id : ids) {
            contentMapper.deleteByPrimaryKey(id);
        }
        return OperationResult.ok();
    }
}
