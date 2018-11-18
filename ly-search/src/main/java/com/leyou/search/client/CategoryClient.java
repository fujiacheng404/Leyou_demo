package com.leyou.search.client;

import com.leyou.item.api.CategoryApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 23:46 2018/11/16
 * @Modified:
 * @annotation:
 */

@FeignClient("item-service")
public interface CategoryClient extends CategoryApi {
}
