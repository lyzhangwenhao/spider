package com.tom.spider.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * ClassName: HttpClient
 * Description:
 *
 * @date 2019/10/22 23:07
 * @author: Mi_dad
 */
@Component
public class HttpUtils {
    private PoolingHttpClientConnectionManager cm;

    public HttpUtils() {
        this.cm = new PoolingHttpClientConnectionManager();

        //设置最大连接数
        cm.setMaxTotal(100);
        //设置每个主机最大连接数
        cm.setDefaultMaxPerRoute(10);
    }

    /**
     * 根据请求地址下载页面数据
     * @param url
     * @return
     */
    public String doGetHtml(String url){
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = null;
        try {
             response = httpClient.execute(httpGet);

             //解析响应，返回结果
             if(response.getStatusLine().getStatusCode() == 200) {
                 //判断相应体Entity是否为空，如果不为空才能使用EntityUtils
                 if (response.getEntity() != null){
                     String html = EntityUtils.toString(response.getEntity(), "utf8");
                     return html;
                 }
             }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    /**
     * 获取RequestConfig
     * @return
     */
    public RequestConfig getConfig(){
        RequestConfig config = RequestConfig.custom().setConnectTimeout(1000)   //创建链接的最长时间
                .setConnectionRequestTimeout(3000)          //获得连接的最长时间
                .setSocketTimeout(10000)        //数据传输的最长时间
                .build();
        return config;
    }

    /**
     * 下载图片
     * @param url
     * @return 返回图片名称
     */
    public String doGetImage(String url){
        CloseableHttpClient httpClient =
                HttpClients.custom().setConnectionManager(cm).build();
        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);

            //解析响应，返回结果
            if(response.getStatusLine().getStatusCode() == 200) {
                //判断相应体Entity是否为空，如果不为空才能使用EntityUtils
                if (response.getEntity() != null){
                    //获取图片的后缀
                    String lastName = url.substring(url.lastIndexOf("."));
                    //生成图片名称
                    String picName = UUID.randomUUID().toString() + lastName;

                    OutputStream os = new FileOutputStream(new File("D:\\z_test\\JD\\"+picName));
                    response.getEntity().writeTo(os);
                    return picName;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }
}
