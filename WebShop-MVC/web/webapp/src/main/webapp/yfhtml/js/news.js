/**
 * 万峻良创建
 * Created by Administrator on 2017/7/31.
 */
// 得到新闻标题的数据,并且内嵌入html标签 ,只要前六个最后修改的新闻,多余的不要
jQuery(document).ready(function() {
    selectNewsTitleList();
});
function getContextPath(){           //得到项目的路径
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}
//    获取新闻数据
function selectNewsTitleList() {
    $.ajax({
        url:getContextPath()+"/news/selectNewsTitleListLimitNumber",
        async: true,
        type:"post",
        dataType:"json",
        data:{
            //传入要的最新的数量
            number:10,
        },
        success:function(data){
            createData(data);
        },
        error:function (data) {
            // alert("请求新闻数据失败");
        }
    });
}
function createData(data) {
    var tempList="";
    $.each(data['newsTitleList'],function(i,newTitle){
        var temp='<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">'+
            '<a target="_blank" href="newsView.html?news_title_id='+newTitle['news_title_id']+'" style="margin-right: auto;font-size: 18px;font-weight: 600;color: #B50909;display: block;text-align: center;" class="hvr-float-shadow">'+newTitle['news_title_text']+
            '<p><small>发布人<strong>'+newTitle['update_name']+'</strong> <i class="fa fa-clock-o"></i>'+newTitle['news_update_date']+'</small></p>'+
            '</a>'+
            '</div>';
        tempList+=temp;
    });
    $("#div_news").html(tempList);
}