package com.spider.utils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.DeflateDecompressingEntity;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

/**
 * Created by michael on 2017/10/26.
 */
public abstract class AbstractHttpUtil {
    /**
     * 发送 GET 请求（HTTP），K-V形式
     * @param url
     * @param params
     * @return
     */
     public String executeHttpGet(String url, Map<String, Object> params, String cookie) {
        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0)
                param.append("?");
            else
                param.append("&");
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl += param;
        StringBuilder result = new StringBuilder();
        HttpClient httpclient = new DefaultHttpClient();
        try {
            HttpGet httpGet = new HttpGet(apiUrl);
            buildHeader(httpGet, cookie);
            HttpResponse response = httpclient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            if (statusCode == 200) {
                if (entity.getContentEncoding() != null) {
                    if ("gzip".equalsIgnoreCase(entity.getContentEncoding().getValue())) {//乱码处理重点
                        entity = new GzipDecompressingEntity(entity);
                    } else if ("deflate".equalsIgnoreCase(entity.getContentEncoding().getValue())) {//乱码处理重点
                        entity = new DeflateDecompressingEntity(entity);
                    }
                }
            }
            if (entity == null) {
                return result.toString();
            }
            return EntityUtils.toString(entity,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
            return result.toString();
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
    }

    /**
     * 发送 GET 请求（HTTP），K-V形式
     * @param url
     * @param params
     * @return
     */
    public HttpResponse executeHttpGetGetResponse(String url, Map<String, Object> params, String cookie) {
        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0)
                param.append("?");
            else
                param.append("&");
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl += param;
        HttpClient httpclient = new DefaultHttpClient();
        try {
            HttpGet httpGet = new HttpGet(apiUrl);
            buildHeader(httpGet, cookie);
            HttpResponse response = httpclient.execute(httpGet);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
    }

    public String executeHttpGetCookie(String url, Map<String, Object> params, String cookie){
        HttpResponse response = executeHttpGetGetResponse(url, params, cookie);
        int statusCode = response.getStatusLine().getStatusCode();
        String key = "Set-Cookie";
        if (response.containsHeader(key)){
            Header[] headers = response.getHeaders(key);
            for (Header header : headers) {
                return header.getValue();//	Set-Cookie:KLBRSID=bedea8ce0f983584f316a2516a052acb|1508907783|1508907618;Path=/
            }
        }
        return new String();
    }

   protected abstract void buildHeader(HttpRequestBase httpRequestBase, String cookie);
}
