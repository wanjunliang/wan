$(document).ready(function(){
    initCreateBiao2();
});
function getContextPath(){           //得到项目的路径
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}

function initCreateBiao2(){
    // 基于准备好的dom，初始化echarts实例
    var myChart2 = echarts.init(document.getElementById('biao2'),'dark');
    // var chart = echarts.init(document.getElementById('main'), 'dark');
    // 指定图表的配置项和数据
    var option2 = {
        title: {
            text: 'ECharts 入门示例'
        },
        tooltip: {},
        legend: {
            data:['销量']
        },
        xAxis: {
            data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
        },
        yAxis: {},
        series: [{
            name: '销量',
            type: 'bar',
            data: [5, 20, 36, 10, 10, 20]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart2.setOption(option2);
}