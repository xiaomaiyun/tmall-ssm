package com.cjw.service.impl;

import com.cjw.mapper.TbItemMapper;
import com.cjw.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cjw.pojo.TbItem;

/**
 * @author codeAC
 * @Date: 2018/7/1
 * @Time: 18:09
 */
@Service
public class ItemServiceImpl implements ItemService {
    private final TbItemMapper itemMapper;

    @Autowired
    public ItemServiceImpl(TbItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    public TbItem getItemById(long id) {
        return itemMapper.selectByPrimaryKey(id);
    }
}
