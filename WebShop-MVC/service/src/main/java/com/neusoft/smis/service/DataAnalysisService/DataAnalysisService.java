package com.neusoft.smis.service.DataAnalysisService;

/**
 * Created by Administrator on 2018/9/7.
 */
public interface DataAnalysisService {
    //第一张表查询每个月的总上拍套数和总成交套数
    String selectDataToBiao1();
    //第二张表查询总上拍套数和总成交套数
    String selectDataToBiao2();
    //第三张表查询住宅类上拍套数和成交套数
    String selectDataToBiao3();
    //第四张查询住宅类 每个月的成交率
    String selectDataToBiao4();
    //第7张图标,前5天每日上拍和成交
    String selectDataToBiao7();

    //第5张 各区拍卖和成交套数
    String selectDataToBiao5();
    //第八张 第一次拍卖上拍和成交套数
    String selectDataToBiao8();
    //第九张 第一次拍卖上拍和成交套数
    String selectDataToBiao9();
    //第十张 第一次拍卖上拍和成交套数
    String selectDataToBiao10();
   // 第十一张 第一次拍卖上拍和成交套数
    String selectDataToBiao11();
    //第十二张 第一次拍卖上拍和成交套数
    String selectDataToBiao12();
    //第十三张 锦江区上拍套数和成交套数
    String selectDataToBiao13();
    //第十四张 青羊区上拍套数和成交套数
    String selectDataToBiao14();
    //第十五张 金牛区上拍套数和成交套数
    String selectDataToBiao15();
    //第十六张 武侯区上拍套数和成交套数
    String selectDataToBiao16();
    //第十七张 成华区上拍套数和成交套数
    String selectDataToBiao17();
    //第十八张 双流区上拍套数和成交套数
    String selectDataToBiao18();
    //第十九张 高新区上拍套数和成交套数
    String selectDataToBiao19();
    //第二十张 天府新区上拍套数和成交套数
    String selectDataToBiao20();
    //第二十一张 龙泉驿区上拍套数和成交套数
    String selectDataToBiao21();
    //第二十二张 温江区上拍套数和成交套数
    String selectDataToBiao22();
    //第二十三张 郫县区上拍套数和成交套数
    String selectDataToBiao23();
}
