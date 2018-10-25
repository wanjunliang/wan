package com.neusoft.smis.dao.mysql.news;

import com.neusoft.smis.common.entity.mysql.news.NewsContent;
import com.neusoft.smis.common.entity.mysql.news.NewsTitle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */
public interface NewsMapper {
    //新增新闻内容
    int insertNewsContent(NewsContent newsContent);
    //新增新闻标题
    int insertNewsTitle(NewsTitle newsTitle);
    //查询新闻标题和内容通过新闻标题的ID
    NewsTitle selectNewsTitleContentByNewsTitleId(NewsTitle newsTitle);
    //查询新闻标题list
    List<NewsTitle> selectNewsTitleList(NewsTitle newsTitle);
    //更新新闻内容通过新闻内容ID
    int updateNewsContentTextByNewsContentId(NewsContent newsContent);
    //更新新闻标题通过新闻标题ID
    int updateNewsTitle(NewsTitle newsTitle);
    //查询新闻标题 list 传入限制 数量
    List<NewsTitle> selectNewsTitleListLimitNumber(int number);
}
