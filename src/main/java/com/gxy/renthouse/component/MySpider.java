package com.gxy.renthouse.component;/*
 * Created by GXY on 2019/2/15
 */

import com.gxy.renthouse.entity.Links;
import com.gxy.renthouse.entity.Page;
import com.gxy.renthouse.service.RequestAndResponseService;
import com.gxy.renthouse.utils.PageParserTool;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * @program:renthouse
 * @description:
 * @author:gaoxiangyu
 * @since:2019-02-15 14:24
 **/
@Component
public class MySpider implements CommandLineRunner {

    @Autowired
    private MongoTemplate mongoTemplate;

    private void initCrawlerWithSeeds ( String[] seeds ) {
        for ( String seed : seeds ) {
            Links.addEnvisitedUrl ( seed );
        }
    }


    public void crawling ( String[] seeds ) {
        initCrawlerWithSeeds ( seeds );
        while ( ! Links.envisitedUrlIsEmpty ( ) && Links.getVisitedNum ( ) <= 10) {
            String visitUrl = ( String ) Links.removeHeadOfEnvistedUrl ( );
            if ( visitUrl == null ) {
                continue;
            }
            Page page = RequestAndResponseService.sendRequestAndGetResponse ( visitUrl );
            Elements a = PageParserTool.select ( page , "a" );
            if ( ! a.isEmpty ( ) ) {
                System.out.println ( "【" + visitUrl + "的子页面打印中..】" );
                System.out.println ( a );
                Links.addEnvisitedUrl ( a.toString ( ) );
            }
            mongoTemplate.insert ( page );
            Links.addVisitedNum ( visitUrl );
        }
    }


    @Override
    public void run ( String... strings ) throws Exception {
        crawling ( new String[] { "https://www.baidu.com/" } );
    }
}
