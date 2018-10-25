package com.neusoft.smis.webapp.dataAnalysisController;

import com.alibaba.fastjson.JSON;
import com.neusoft.smis.webapp.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Wanjunliang on 2018/9/7.
 */
@Controller
@RequestMapping("/dataAnalysisController")
public class DataAnalysisController extends BaseController{

    /***
     * 查询一年到目前每个月的所有住宅、商业、办公的，返回每个月的总上拍套数和总成交套数
     * @return
     * {
     * "chengjiao_array":[147,96,115,148,153,66,134,95],
     * "shangpai_array":[236,179,325,252,348,303,488,522],
     * "yuefeng_array":["1月","2月","3月","4月","5月","6月","7月","8月"]
     * }
     */
    @RequestMapping(value = "/selectDataToBiao1",produces = "text/html;charset=UTF-8")
    @ResponseBody
//    @RequiresPermissions("新闻:查询")
    public String selectDataToBiao1(){

        String res=dataAnalysisService.selectDataToBiao1();
        return res;
    }
    // 图表2 总成交率走势图
    @RequestMapping(value = "/selectDataToBiao2",produces = "text/html;charset=UTF-8")
    @ResponseBody
//    @RequiresPermissions("新闻:查询")
    public String selectDataToBiao2(){

        String res=dataAnalysisService.selectDataToBiao2();
        return res;
    }

    /***
     * 住宅类拍卖的每个月的上拍套数和成交的套数
     * @return
     * {
     * "chengjiao_array":[115,76,91,99,112,56,110,74],
     * "shangpai_array":[138,112,194,150,201,191,269,316],
     * "yuefeng_array":["1月","2月","3月","4月","5月","6月","7月","8月"]
     * }
     */
    @RequestMapping(value = "/selectDataToBiao3",produces = "text/html;charset=UTF-8")
    @ResponseBody
//    @RequiresPermissions("新闻:查询")
    public String selectDataToBiao3(){
        String res=dataAnalysisService.selectDataToBiao3();
        return res;
    }

    /***
     * 住宅类成交率走势图
     * @return
     * {
     * "chengjiaolv_array":[83,67,46,66,55,29,40,23],
     * "yuefeng_array":["1月","2月","3月","4月","5月","6月","7月","8月"]
     * }
     */
    @RequestMapping(value = "/selectDataToBiao4",produces = "text/html;charset=UTF-8")
    @ResponseBody
//    @RequiresPermissions("新闻:查询")
    public String selectDataToBiao4(){
        String res=dataAnalysisService.selectDataToBiao4();
        return res;
    }


    /***
     * 各区拍卖和成交
     * @return
     * {
     * "chengjiao_array":[106,90,115,118,59,64,132,42,78,109,86],
     * "region_array":["锦江","青羊","金牛","武侯","成华","双流","高新","天府新","龙泉驿","温江","郫"],
     * "shangpai_array":[263,317,407,342,167,215,482,97,167,272,210]
     * }
     */
    @RequestMapping(value = "/selectDataToBiao5",produces = "text/html;charset=UTF-8")
    @ResponseBody
//    @RequiresPermissions("新闻:查询")
    public String selectDataToBiao5(){
        String res=dataAnalysisService.selectDataToBiao5();
        return res;
    }

    /***
     * 图表7 前5天上拍和成交
     * @return
     *{
     * "date_list":
     * [
     * {"chengjiaojine":0,"chengjiaotaoshu":0,"date":"2018-09-21","shangpaijine":0,"shangpaitaoshu":0},
     * {"chengjiaojine":0,"chengjiaotaoshu":0,"date":"2018-09-20","shangpaijine":44211952,"shangpaitaoshu":13},
     * {"chengjiaojine":3416350,"chengjiaotaoshu":2,"date":"2018-09-19","shangpaijine":69331282,"shangpaitaoshu":20},
     * {"chengjiaojine":29292511,"chengjiaotaoshu":11,"date":"2018-09-18","shangpaijine":23896251,"shangpaitaoshu":21},
     * {"chengjiaojine":20684470,"chengjiaotaoshu":11,"date":"2018-09-17","shangpaijine":66687838,"shangpaitaoshu":26}
     * ]
     * }
     */
    @RequestMapping(value = "/selectDataToBiao7",produces = "text/html;charset=UTF-8")
    @ResponseBody
//    @RequiresPermissions("新闻:查询")
    public String selectDataToBiao7(){
        String res=dataAnalysisService.selectDataToBiao7();
        return res;
    }
    /***
     * 第一次拍卖上拍和成交套数
     * @return
     * {
     * "chengjiaolv_array":[83,67,46,66,55,29,40,23],
     * "yuefeng_array":["1月","2月","3月","4月","5月","6月","7月","8月"]
     * }
     */
    @RequestMapping(value = "/selectDataToBiao8",produces = "text/html;charset=UTF-8")
    @ResponseBody
//    @RequiresPermissions("新闻:查询")
    public String selectDataToBiao8(){
        String res=dataAnalysisService.selectDataToBiao8();
        return res;
    }

    /***
     * 第二次拍卖上拍和成交套数
     * @return
     * {
     * "chengjiaolv_array":[83,67,46,66,55,29,40,23],
     * "yuefeng_array":["1月","2月","3月","4月","5月","6月","7月","8月"]
     * }
     */
    @RequestMapping(value = "/selectDataToBiao9",produces = "text/html;charset=UTF-8")
    @ResponseBody
//    @RequiresPermissions("新闻:查询")
    public String selectDataToBiao9(){
        String res=dataAnalysisService.selectDataToBiao9();
        return res;
    }


    /***
     * 变卖拍卖上拍和成交套数
     * @return
     * {
     * "chengjiaolv_array":[83,67,46,66,55,29,40,23],
     * "yuefeng_array":["1月","2月","3月","4月","5月","6月","7月","8月"]
     * }
     */
    @RequestMapping(value = "/selectDataToBiao10",produces = "text/html;charset=UTF-8")
    @ResponseBody
//    @RequiresPermissions("新闻:查询")
    public String selectDataToBiao10(){
        String res=dataAnalysisService.selectDataToBiao10();
        return res;
    }


    /***
     * 二拍成交率
     * @return
     * {
     * "chengjiaolv_array":[83,67,46,66,55,29,40,23],
     * "yuefeng_array":["1月","2月","3月","4月","5月","6月","7月","8月"]
     * }
     */
    @RequestMapping(value = "/selectDataToBiao11",produces = "text/html;charset=UTF-8")
    @ResponseBody
//    @RequiresPermissions("新闻:查询")
    public String selectDataToBiao11(){
        String res=dataAnalysisService.selectDataToBiao11();
        return res;
    }




    /***
     * 变卖成交率
     * @return
     * {
     * "chengjiaolv_array":[83,67,46,66,55,29,40,23],
     * "yuefeng_array":["1月","2月","3月","4月","5月","6月","7月","8月"]
     * }
     */
    @RequestMapping(value = "/selectDataToBiao12",produces = "text/html;charset=UTF-8")
    @ResponseBody
//    @RequiresPermissions("新闻:查询")
    public String selectDataToBiao12(){
        String res=dataAnalysisService.selectDataToBiao12();
        return res;
    }




    /***
     * 锦江区拍卖和成交数
     * @return
     * {
     * "chengjiaolv_array":[83,67,46,66,55,29,40,23],
     * "yuefeng_array":["1月","2月","3月","4月","5月","6月","7月","8月"]
     * }
     */
    @RequestMapping(value = "/selectDataToBiao13",produces = "text/html;charset=UTF-8")
    @ResponseBody
//    @RequiresPermissions("新闻:查询")
    public String selectDataToBiao13(){
        String res=dataAnalysisService.selectDataToBiao13();
        return res;
    }




    /***
     * 青羊区拍卖和成交数
     * @return
     * {
     * "chengjiaolv_array":[83,67,46,66,55,29,40,23],
     * "yuefeng_array":["1月","2月","3月","4月","5月","6月","7月","8月"]
     * }
     */
    @RequestMapping(value = "/selectDataToBiao14",produces = "text/html;charset=UTF-8")
    @ResponseBody
//    @RequiresPermissions("新闻:查询")
    public String selectDataToBiao14(){
        String res=dataAnalysisService.selectDataToBiao14();
        return res;
    }


    /***
     * 金牛区拍卖和成交数
     * @return
     * {
     * "chengjiaolv_array":[83,67,46,66,55,29,40,23],
     * "yuefeng_array":["1月","2月","3月","4月","5月","6月","7月","8月"]
     * }
     */
    @RequestMapping(value = "/selectDataToBiao15",produces = "text/html;charset=UTF-8")
    @ResponseBody
//    @RequiresPermissions("新闻:查询")
    public String selectDataToBiao15(){
        String res=dataAnalysisService.selectDataToBiao15();
        return res;
    }



    /***
     * 武侯区拍卖和成交数
     * @return
     * {
     * "chengjiaolv_array":[83,67,46,66,55,29,40,23],
     * "yuefeng_array":["1月","2月","3月","4月","5月","6月","7月","8月"]
     * }
     */
    @RequestMapping(value = "/selectDataToBiao16",produces = "text/html;charset=UTF-8")
    @ResponseBody
//    @RequiresPermissions("新闻:查询")
    public String selectDataToBiao16(){
        String res=dataAnalysisService.selectDataToBiao16();
        return res;
    }



    /***
     * 成华区拍卖和成交数
     * @return
     * {
     * "chengjiaolv_array":[83,67,46,66,55,29,40,23],
     * "yuefeng_array":["1月","2月","3月","4月","5月","6月","7月","8月"]
     * }
     */
    @RequestMapping(value = "/selectDataToBiao17",produces = "text/html;charset=UTF-8")
    @ResponseBody
//    @RequiresPermissions("新闻:查询")
    public String selectDataToBiao17(){
        String res=dataAnalysisService.selectDataToBiao17();
        return res;
    }



    /***
     * 双流区拍卖和成交数
     * @return
     * {
     * "chengjiaolv_array":[83,67,46,66,55,29,40,23],
     * "yuefeng_array":["1月","2月","3月","4月","5月","6月","7月","8月"]
     * }
     */
    @RequestMapping(value = "/selectDataToBiao18",produces = "text/html;charset=UTF-8")
    @ResponseBody
//    @RequiresPermissions("新闻:查询")
    public String selectDataToBiao18(){
        String res=dataAnalysisService.selectDataToBiao18();
        return res;
    }

    /***
     * 高新区拍卖和成交数
     * @return
     * {
     * "chengjiaolv_array":[83,67,46,66,55,29,40,23],
     * "yuefeng_array":["1月","2月","3月","4月","5月","6月","7月","8月"]
     * }
     */
    @RequestMapping(value = "/selectDataToBiao19",produces = "text/html;charset=UTF-8")
    @ResponseBody
//    @RequiresPermissions("新闻:查询")
    public String selectDataToBiao19(){
        String res=dataAnalysisService.selectDataToBiao19();
        return res;
    }


    /***
     * 天府新区拍卖和成交数
     * @return
     * {
     * "chengjiaolv_array":[83,67,46,66,55,29,40,23],
     * "yuefeng_array":["1月","2月","3月","4月","5月","6月","7月","8月"]
     * }
     */
    @RequestMapping(value = "/selectDataToBiao20",produces = "text/html;charset=UTF-8")
    @ResponseBody
//    @RequiresPermissions("新闻:查询")
    public String selectDataToBiao20(){
        String res=dataAnalysisService.selectDataToBiao20();
        return res;
    }


    /***
     * 龙泉驿区拍卖和成交数
     * @return
     * {
     * "chengjiaolv_array":[83,67,46,66,55,29,40,23],
     * "yuefeng_array":["1月","2月","3月","4月","5月","6月","7月","8月"]
     * }
     */
    @RequestMapping(value = "/selectDataToBiao21",produces = "text/html;charset=UTF-8")
    @ResponseBody
//    @RequiresPermissions("新闻:查询")
    public String selectDataToBiao21(){
        String res=dataAnalysisService.selectDataToBiao21();
        return res;
    }


    /***
     * 天府新区拍卖和成交数
     * @return
     * {
     * "chengjiaolv_array":[83,67,46,66,55,29,40,23],
     * "yuefeng_array":["1月","2月","3月","4月","5月","6月","7月","8月"]
     * }
     */
    @RequestMapping(value = "/selectDataToBiao22",produces = "text/html;charset=UTF-8")
    @ResponseBody
//    @RequiresPermissions("新闻:查询")
    public String selectDataToBiao22(){
        String res=dataAnalysisService.selectDataToBiao22();
        return res;
    }



    /***
     * 郫县区拍卖和成交数
     * @return
     * {
     * "chengjiaolv_array":[83,67,46,66,55,29,40,23],
     * "yuefeng_array":["1月","2月","3月","4月","5月","6月","7月","8月"]
     * }
     */
    @RequestMapping(value = "/selectDataToBiao23",produces = "text/html;charset=UTF-8")
    @ResponseBody
//    @RequiresPermissions("新闻:查询")
    public String selectDataToBiao23(){
        String res=dataAnalysisService.selectDataToBiao23();
        return res;
    }
}
