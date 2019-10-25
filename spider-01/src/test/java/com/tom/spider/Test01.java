package com.tom.spider;

import org.apache.commons.io.FileUtils;
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
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

/**
 * ClassName: Test01
 * Description:
 *
 * @date 2019/10/22 19:59
 * @author: Mi_dad
 */
public class Test01 {
    @Test
    public void urlTest() throws IOException {
        long start = System.currentTimeMillis();
//        Document doc = Jsoup.parse(new URL("https://pvp.qq.com/web201605/herolist.shtml"), 10000);
        Document doc = Jsoup.parse(new URL("http://www.henu.edu.cn/"), 10000);

        long end = System.currentTimeMillis();

        System.out.println("用时"+ (end-start));
        String title = doc.getElementsByTag("title").first().text();
        System.out.println(title);
    }
    @Test
    public void stringTest() throws IOException {
        String html = FileUtils.readFileToString(new File("C:\\Users\\Mi_Dad\\Desktop\\河南大学官网.html"), "utf8");
        Document doc = Jsoup.parse(html);
        Elements title = doc.getElementsByTag("title");
        System.out.println(title.html());
    }

    @Test
    public void fileTest() throws IOException {
        Document doc = Jsoup.parse(new File("C:\\Users\\Mi_Dad\\Desktop\\河南大学官网.html"), "utf8");
        Elements title = doc.getElementsByTag("title");
        System.out.println(title.html());

    }
    @Test
    public void docTest() throws IOException {
        Document doc = Jsoup.parse(new File("C:\\Users\\Mi_Dad\\Desktop\\河南大学官网.html"), "utf8");
        //1、通过id获取
        Element elementById = doc.getElementById("vsb_content_2");
        Elements p = elementById.getElementsByTag("p");
        for (Element ele:p){
            System.out.println(ele.text());
        }
        System.out.println("---------------------");
        //2、通过class获取元素
        Elements elements = doc.getElementsByClass("head_li");
        for (Element ele:elements){
            Elements a = ele.getElementsByTag("a");
            for(Element e:a){
                System.out.println(e.text());
                System.out.println(e.attr("href"));
            }
            System.out.println("==============================");
        }
        //3、通过标签获取元素

        //4、通过属性获取元素
    }

    @Test
    public void fileDownTest(){
        HttpGet httpGet = new HttpGet("https://list.jd.com/list.html?cat=670,671,672&page=2&sort=sort_totalsales15_desc&trans=1&JL=6_0_0#J_main");
//        RequestConfig config = RequestConfig.custom().setSocketTimeout(10 * 1000)
//                .setConnectionRequestTimeout(500)
//                .setConnectTimeout(1000).build();
//        httpGet.setConfig(config);

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        CloseableHttpClient client = HttpClients.custom().setConnectionManager(cm).build();

        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpGet);
            if (response.getEntity()!=null){
                HttpEntity entity = response.getEntity();
                String html = EntityUtils.toString(entity, "gbk");
                Document doc = Jsoup.parse(html);

                //商品列表
                Elements list = doc.select("div#plist > ul >li");
                for (Element li:list){
                    //获取brand_id
//                    String brand_id = li.select("[brand_id]").attr("brand_id");
//                    System.out.println(brand_id);

                    //获取desc
//                    Element desc = li.getElementsByClass("p-name").first();
//                    Element em = desc.select("em").first();
//                    System.out.println(em.text());
//                    System.out.println("-----------------------------------------------");

                    //price    取不到
//                    System.out.println(li.getElementsByClass("p-price").html());
                    //获取sku等
                    //获取图片外面的a标签
                    Elements imgLi = li.select("div.p-img a");
                    for (Element img:imgLi){
                        //title
                        System.out.println(img.attr("title"));
                        //sku
//                        String sku = img.select("[data-sku]").attr("data-sku");
//                        System.out.println(sku);
                        //src
                        String src = img.select("[src]").attr("src");
                        System.out.println(src);
                    }
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
    }

}
