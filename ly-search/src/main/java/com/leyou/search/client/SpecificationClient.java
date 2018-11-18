package com.leyou.search.client;

import com.leyou.item.api.SpecificationApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 21:46 2018/11/17
 * @Modified:
 * @annotation:
 */

@FeignClient("item-service")
public interface SpecificationClient extends SpecificationApi {
}
