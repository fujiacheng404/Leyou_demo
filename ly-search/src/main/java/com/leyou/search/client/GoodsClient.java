package com.leyou.search.client;

import com.leyou.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 23:58 2018/11/16
 * @Modified:
 * @annotation:
 */

@FeignClient("item-service")
public interface GoodsClient extends GoodsApi {
}
