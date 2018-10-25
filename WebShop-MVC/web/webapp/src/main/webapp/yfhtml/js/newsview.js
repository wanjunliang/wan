/**
 * 万峻良创建
 * Created by Administrator on 2017/7/31.
 */
// 得到新闻标题的数据,并且内嵌入html标签 ,只要前六个最后修改的新闻,多余的不要
jQuery(document).ready(function() {
    selectNewsTitleContentByNewsTitleId();
});
function getContextPath(){           //得到项目的路径
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}
//    获取url地址传递的参数
function GetQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}
//    获取新闻数据
function selectNewsTitleContentByNewsTitleId() {
    $.ajax({
        url:getContextPath()+"/news/selectNewsTitleContentByNewsTitleId",
        async: true,
        type:"post",
        dataType:"json",
        data:{
            news_title_id:GetQueryString("news_title_id"),
        },
        success:function(data){
            createData(data);
        },
        error:function (data) {
            alert("返回新闻数据失败");
        }
    });
}
function createData(data) {
    $("#newsTitle").html(data['newsTitleContent']['news_title_text']);
    $("#newsUpdateByAndDate").html("发布人:"+data['newsTitleContent']['update_name']+"  发布时间:"+data['newsTitleContent']['news_update_date']);
    $("#newsContent").html(data['newsTitleContent']['newsContent']['news_content_text']);

}