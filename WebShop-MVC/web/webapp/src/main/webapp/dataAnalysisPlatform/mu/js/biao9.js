function initCreateBiao9(div_id){
    // 基于准备好的dom，初始化echarts实例
    var myChart1 = echarts.init(document.getElementById(div_id),'dark');
    //请求后台数据
    ajaxRequestData9(myChart1);
}
//ajax步请求数据
function ajaxRequestData9(myChart) {
    $.ajax({
        url:getContextPath()+"/dataAnalysisController/selectDataToBiao9",
        async: true,
        type:"post",
        dataType:"json",
        data:{

        },
        success:function(res){
            //填充数据绘制图形
            createEchartsByData9(myChart,res);
        },
        error:function () {
            alert("服务器异常,请求数据失败");
        }
    });
}
//填充数据绘制图形
function createEchartsByData9(myChart,data) {
    // 指定图表的配置项和数据
    var option = {
        title : {
            text: '二拍拍卖和成交套数',
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
    option.xAxis[0].data=data.yuefeng_array;
    option.series[0].data=data.shangpai_array;
    option.series[1].data=data.chengjiao_array;
    myChart.setOption(option);
}