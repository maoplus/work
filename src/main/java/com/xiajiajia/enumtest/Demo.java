package com.xiajiajia.enumtest;

import java.io.IOException;

enum NewsRSSFeedEnum {
    // 雅虎头条新闻 RSS 种子
    YAHOO_TOP_STORIES("http://rss.news.yahoo.com/rss/topstories"),
    
    // CBS 头条新闻 RSS 种子
    CBS_TOP_STORIES("http://feeds.cbsnews.com/CBSNewsMain?format=xml"),
    
    // 洛杉矶时报头条新闻 RSS 种子
    LATIMES_TOP_STORIES("http://feeds.latimes.com/latimes/news?format=xml");
    
    // 枚举对象的 RSS 地址的属性
    private String rss_url;
    
    // 枚举对象构造函数
    private NewsRSSFeedEnum(String rss) {
        this.rss_url = rss;
    }
    
    // 枚举对象获取 RSS 地址的方法
    public String getRssURL() {
        return this.rss_url;
    }
}

public class Demo {
    protected String getHello(String value) {
        return "上海 " + value;
    }
    
    public static void main(String[] args) throws IOException {
        System.out.println(NewsRSSFeedEnum.CBS_TOP_STORIES.getRssURL());
        System.out.println(NewsRSSFeedEnum.YAHOO_TOP_STORIES.getRssURL());
        System.out.println(NewsRSSFeedEnum.LATIMES_TOP_STORIES.getRssURL());
    }
}
