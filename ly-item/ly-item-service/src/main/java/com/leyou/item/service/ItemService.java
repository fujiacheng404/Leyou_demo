package com.leyou.item.service;

import com.leyou.item.pojo.item;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 19:24 2018/11/2
 * @Modified:
 * @annotation:
 */

@Service
public class ItemService {
    public item saveItem(item item) {
        int id = new Random().nextInt(100); //随机生成id =新增商品
        item.setId(id);
        return item;
    }
}