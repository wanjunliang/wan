package com.neusoft.smis.common.entity.mysql.news;

import lombok.Data;

/**
 * Created by Administrator on 2017/7/27.
 */
//新闻标题
@Data
public class NewsTitle {
    private int news_title_id;//新闻标题主键
    private String news_title_text;//新闻标题
    private int news_create_by;//新闻创建者ID
    private String news_create_date;//新闻标题创建时间
    private int news_update_by;//新闻标题修改者ID
    private String news_update_date;//新闻标题 修改时间  最后一次的
    private int news_content_id;//新闻标题内容ID
    private NewsContent newsContent;
    private String del_flag;//删除标志,0:没有删除,1:已删除

//    数据库中没有的字段
    private String create_name;//创建人名字
    private String update_name;//修改人名字
}
