package com.spider.core;

import org.springframework.stereotype.Service;

/**
 * Created by michael on 2017/10/26.
 */

@Service
public interface ISpider {
    String getHtml() throws Exception;
}
