package com.neusoft.smis.service.company_web_service;

import com.neusoft.smis.common.entity.mysql.news.NewsContent;
import com.neusoft.smis.common.entity.mysql.news.NewsTitle;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */
public interface NewsService {
//    新增新闻
    String insertNews(NewsTitle newsTitle, NewsContent newsContent);
    //查询新闻标题和内容通过新闻标题的ID
    NewsTitle selectNewsTitleContentByNewsTitleId(NewsTitle newsTitle);
    //查询新闻标题list
    List<NewsTitle> selectNewsTitleList(NewsTitle newsTitle);
    //修改新闻
    String updateNews(NewsTitle newsTitle,NewsContent newsContent);
    //删除新闻
    String deleteNews(NewsTitle newsTitle);
    //查询新闻标题,有限制,最新的几条
    List<NewsTitle> selectNewsTitleListLimitNumber(String number);
}
