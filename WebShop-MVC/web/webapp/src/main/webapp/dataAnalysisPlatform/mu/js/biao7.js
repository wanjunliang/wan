// 前5天上拍和成交
// $(document).ready(function(){
//     initCreateBiao7();
// });
// function getContextPath(){           //得到项目的路径
//     var pathName = document.location.pathname;
//     var index = pathName.substr(1).indexOf("/");
//     var result = pathName.substr(0,index+1);
//     return result;
// }

function initCreateBiao7(div_id){
    // 基于准备好的dom，初始化echarts实例
    var chart_7 = document.getElementById(div_id);
    //请求后台数据
    ajaxRequestData7(chart_7);
}
//ajax步请求数据
function ajaxRequestData7(chart_7) {
    $.ajax({
        url:getContextPath()+"/dataAnalysisController/selectDataToBiao7",
        async: true,
        type:"post",
        dataType:"json",
        data:{

        },
        success:function(res){
            //填充数据绘制图形
            createBiao7(chart_7,res);
        },
        error:function () {
            alert("服务器异常,请求数据失败");
        }
    });
}
//填充数据绘制图形
function createBiao7(chart_7,data) {
    var date_list=data.date_list;
    var html="<table>"+
            "<thead>"+
            "<tr>"+
            "<th>日期</th>"+
            "<th>上拍套数</th>"+
            "<th>成交套数</th>"+
            "<th>上拍总金额</th>"+
            "<th>成交总金额</th>"+
            "</tr>"+
            "</thead>"+
            "<tbody>";
    if(chart_7.offsetHeight<300){
        for(var i=0;i<5;i++){
            var tr=" <tr>"+
                "<td>"+date_list[i]['date']+"</td>"+
                "<td>"+date_list[i]['shangpaitaoshu']+"</td>"+
                "<td>"+date_list[i]['chengjiaotaoshu']+"</td>"+
                "<td>"+number_format(date_list[i]['shangpaijine'],0)+"</td>"+
                "<td>"+number_format(date_list[i]['chengjiaojine'],0)+"</td>"+
                "</tr>";
            html+=tr;
        }
    }
    else{
        for(var i in date_list){
            var tr=" <tr>"+
                "<td>"+date_list[i]['date']+"</td>"+
                "<td>"+date_list[i]['shangpaitaoshu']+"</td>"+
                "<td>"+date_list[i]['chengjiaotaoshu']+"</td>"+
                "<td>"+number_format(date_list[i]['shangpaijine'],0)+"</td>"+
                "<td>"+number_format(date_list[i]['chengjiaojine'],0)+"</td>"+
                "</tr>";
            html+=tr;
        }
    }

    html+="</tbody>"+
        "</table>";
    chart_7.innerHTML=html;
}

function number_format(number, decimals, dec_point, thousands_sep) {
    /*
     　　 * 参数说明：
     　　 * number：要格式化的数字
     　　 * decimals：保留几位小数
     　　 * dec_point：小数点符号
     　　 * thousands_sep：千分位符号
     　　 * */
    number = (number + '').replace(/[^0-9+-Ee.]/g, '');
    var n = !isFinite(+number) ? 0 : +number,
        prec = !isFinite(+decimals) ? 2 : Math.abs(decimals),
        sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
        dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
        s = '',
        toFixedFix = function(n, prec) {
            var k = Math.pow(10, prec);
            return '' + Math.ceil(n * k) / k;
        };

    s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
    var re = /(-?\d+)(\d{3})/;
    while(re.test(s[0])) {
        s[0] = s[0].replace(re, "$1" + sep + "$2");
    }

    if((s[1] || '').length < prec) {
        s[1] = s[1] || '';
        s[1] += new Array(prec - s[1].length + 1).join('0');
    }
    return s.join(dec);
}