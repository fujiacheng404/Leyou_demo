package com.leyou.search.pojo;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 20:49 2018/11/18
 * @Modified:
 * @annotation:
 */

public class SearchRequest {
    private String key; //搜索字段
    private Integer page; //当前页
    private static final int DEFAULT_SIZE = 20; //每页大小 默认值
    private static final int DEFAULT_PAGE = 1; //默认页 默认值
    
    public String getKey() {
        return key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    
    public Integer getPage() {
        if (page == null) {
            return getDefaultPage();
        }
        // 获取页码时做的验证
        return Math.max(DEFAULT_PAGE, page);
    }
    
    public void setPage(Integer page) {
        this.page = page;
    }
    
    public static int getDefaultSize() {
        return DEFAULT_SIZE;
    }
    
    public static int getDefaultPage() {
        return DEFAULT_PAGE;
    }
}
