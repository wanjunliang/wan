package com.neusoft.smis.common.entity.mysql.news;

import lombok.Data;

/**
 * Created by Administrator on 2017/7/27.
 */
//新闻内容
@Data
public class NewsContent {
    private int news_content_id;//新闻内容表的ID
    private String news_content_text;//新闻内容
}
