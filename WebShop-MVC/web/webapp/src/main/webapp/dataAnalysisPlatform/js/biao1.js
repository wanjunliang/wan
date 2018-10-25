$(document).ready(function(){
    initCreateBiao();
});
function getContextPath(){           //得到项目的路径
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}

function initCreateBiao(){
    // 基于准备好的dom，初始化echarts实例
    var myChart1 = echarts.init(document.getElementById('chart_1'),'dark');
    // var chart = echarts.init(document.getElementById('main'), 'dark');
    // 指定图表的配置项和数据
    var option1 = {
        title : {
            text: '总拍卖和成交套数量',
            subtext: '根据成都房管局数据进行统计'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['拍卖套数','成交套数']
        },
        // toolbox: {
        //     show : true,
        //     feature : {
        //         dataView : {show: true, readOnly: false},
        //         magicType : {show: true, type: ['line', 'bar']},
        //         restore : {show: true},
        //         saveAsImage : {show: true}
        //     }
        // },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                data : ['1月','2月','3月','4月','5月','6月','7月','8月']
            }
        ],
        yAxis : [
            {
                type : 'value'

            }
        ],
        series : [
            {
                name:'拍卖套数',
                type:'bar',
                data:[266, 209, 212, 289, 311, 295, 399, 530],
                markPoint : {
                    data : [
                        {type : 'max', name: '最大值'},
                        {type : 'min', name: '最小值'}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name: '平均值'}
                    ]
                }
            },
            {
                name:'成交套数',
                type:'bar',
                data:[161, 116, 103, 153,175, 108, 117, 215],
                markPoint : {
                    data : [
                        {name : '年最高', value : 182.2, xAxis: 7, yAxis: 183},
                        {name : '年最低', value : 2.3, xAxis: 11, yAxis: 3}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name : '平均值'}
                    ]
                }
            }
        ]
    };
    myChart1.setOption(option1);
}