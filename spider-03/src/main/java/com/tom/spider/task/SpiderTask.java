package com.tom.spider.task;

import com.tom.spider.pojo.Mobile;
import com.tom.spider.utils.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * ClassName: SpiderTask
 * Description:
 *
 * @date 2019/10/23 0:43
 * @author: Mi_dad
 */
@Component
public class SpiderTask {

    @Autowired
    private HttpUtils httpUtils;

    @Scheduled(fixedDelay = 1000*60*10)
    public void jdTask(){
        //声明需要解析的初始地址
        String url1 = "https://list.jd.com/list.html?cat=9987,653,655&page=";
        String url2 = "&sort=sort_rank_asc&trans=1&JL=6_0_0#J_main";
        for (int i=1; i<10; i++){
            String html = httpUtils.doGetHtml(url1 + i + url2);

            this.parse(html);



        }
        System.out.println();
        System.out.println("------数据抓取完成！------");
        System.out.println();
    }


    //处理html
    public void parse(String html){
        Document doc = Jsoup.parse(html);
        //商品列表
        Elements list = doc.select("div#plist > ul >li");
        for (Element li:list){
            //获取brand_id
            String brand_id = li.select("[brand_id]").attr("brand_id");
            //获取sku

            //价格        取不到

            //获取title

            //获取图片
            //详情地址

            //获取描述
            Element desc = li.getElementsByClass("p-name").first();
            Element em = desc.select("em").first();

            //创建时间
            //更新时间

        }

    }
}
