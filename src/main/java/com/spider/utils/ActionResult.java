package com.spider.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by michael on 2017/11/4.
 */
@Component
public class ActionResult<T>{
    private List<T> resultList;
    private int totalPages;
    private int currentPage;
    private int count;
    @Value("${lemicp.pageSize}")
    public int pageSize;
    public int getPageSize() {
        return this.pageSize;
    }
    public ActionResult setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }
    public ActionResult setResultList(List<T> resultList) {
        this.resultList = resultList;
        this.count = resultList.size();
        this.totalPages = this.count / this.pageSize + 1;
        return this;
    }

    @Override
    public String toString() {
        JSONObject resultJson = new JSONObject(12);
        resultJson.put("data", this.resultList);
        resultJson.put("pageSize", this.pageSize);
        resultJson.put("totalPages", this.totalPages);
        return resultJson.toJSONString();
    }
}
