//各区拍卖与成交
// $(document).ready(function(){
//     initCreateBiao5();
// });
// function getContextPath(){           //得到项目的路径
//     var pathName = document.location.pathname;
//     var index = pathName.substr(1).indexOf("/");
//     var result = pathName.substr(0,index+1);
//     return result;
// }

function initCreateBiao5(div_id){
    // 基于准备好的dom，初始化echarts实例
    var myChart1 = echarts.init(document.getElementById(div_id),'dark');
    //请求后台数据
    ajaxRequestData5(myChart1);
}
//ajax步请求数据
function ajaxRequestData5(myChart) {
    $.ajax({
        url:getContextPath()+"/dataAnalysisController/selectDataToBiao5",
        async: true,
        type:"post",
        dataType:"json",
        data:{

        },
        success:function(res){
            //填充数据绘制图形
            createEchartsByData5(myChart,res);
        },
        error:function () {
            alert("服务器异常,请求数据失败");
        }
    });
}
//填充数据绘制图形
function createEchartsByData5(myChart,data) {
    // 指定图表的配置项和数据
    var option = {
        title : {
            text: '各区拍卖和成交套数',
            subtext: ''
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:[]
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
                data : []
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
                data:[],
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
                data:[],
                markPoint : {
                    data : [
                        {type : 'max', name: '最大值'},
                        {type : 'min', name: '最小值'}
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
    option.xAxis[0].data=data.region_array;
    option.series[0].data=data.shangpai_array;
    option.series[1].data=data.chengjiao_array;
    myChart.setOption(option);
}