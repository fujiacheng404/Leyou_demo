package com.leyou.search.client;

import com.leyou.item.api.BrandApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 21:47 2018/11/17
 * @Modified:
 * @annotation:
 */

@FeignClient("item-service")
public interface BrandClient extends BrandApi {
}
