package com.tom.spider;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * ClassName: Sprider
 * Description:
 *
 * @date 2019/10/22 21:17
 * @author: Mi_dad
 */
public class Sprider {
    public static void main(String[] args) {
//        HttpGet httpGet = new HttpGet("https://pvp.qq.com/web201605/herolist.shtml");
        HttpGet httpGet = new HttpGet("http://www.nxkg.org.cn/index.php?m=content&c=index&a=lists&catid=29");
        RequestConfig config = RequestConfig.custom().setSocketTimeout(10 * 1000)
                .setConnectionRequestTimeout(500)
                .setConnectTimeout(1000).build();
        httpGet.setConfig(config);

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        CloseableHttpClient client = HttpClients.custom().setConnectionManager(cm).build();

        CloseableHttpResponse response = null;
        try {
            response= client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String html = EntityUtils.toString(entity,"gbk");

            Document doc = Jsoup.parse(html);

//            Elements heros = doc.getElementsByClass("herolist clearfix");
            Elements heros = doc.getElementsByClass("listww");
            for (Element hero:heros){
                Elements a = hero.getElementsByTag("a");
                for (Element h:a){
                    System.out.println(h.text());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
