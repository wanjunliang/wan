//住宅类成交率走势图
// $(document).ready(function(){
//     initCreateBiao4();
// });
// function getContextPath(){           //得到项目的路径
//     var pathName = document.location.pathname;
//     var index = pathName.substr(1).indexOf("/");
//     var result = pathName.substr(0,index+1);
//     return result;
// }

function initCreateBiao4(div_id){
    // 基于准备好的dom，初始化echarts实例
    var myChart1 = echarts.init(document.getElementById(div_id),'dark');
    //请求后台数据
    ajaxRequestData4(myChart1);
}
//ajax步请求数据
function ajaxRequestData4(myChart) {
    $.ajax({
        url:getContextPath()+"/dataAnalysisController/selectDataToBiao4",
        async: true,
        type:"post",
        dataType:"json",
        data:{

        },
        success:function(res){
            //填充数据绘制图形
            createEchartsByData4(myChart,res);
        },
        error:function () {
            alert("服务器异常,请求数据失败");
        }
    });
}
//填充数据绘制图形
function createEchartsByData4(myChart,data) {
    // 指定图表的配置项和数据
    var option = {
        title : {
            text: '住宅成交率走势图',
            subtext: ''
        },
        xAxis: [
            {
                type: 'category',
                data: []
            }
        ],
        yAxis: {
            type: 'value',
            axisLabel: {
                show: true,
                interval: 'auto',
                formatter: '{value} %'
            },
            show: true
        },
        series: [{
            data: [],
            type: 'line',
            itemStyle : {
                normal: {
                    label : {
                        show: true
                    }
                }
            }
        }]
    };
    option.xAxis[0].data=data.yuefeng_array;
    option.series[0].data=data.chengjiaolv_array;
    myChart.setOption(option);
}