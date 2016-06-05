// 路径配置
require.config({
    paths: {
        echarts: 'http://echarts.baidu.com/build/dist'
    }
});
$(function() {
    // var chart;

    $(document).ready(function() {
        // get company
        var url = "/GitMiningServer/stat"
        /*$.ajax(url, {
            type : 'GET',
            // async : false,
            // contentType : 'application/json',
            // dataType : 'json',
            data : {type:'general',param:'UserDistOverFollower'},
            success : function(data) {
                // Set up the chart
                

               
            }
        });*/

        /*var ranges = JSON.parse(data).ranges;
                var catagories = []
                var showdatas = []
                for (var i = 0; i < ranges.length; i++) {
                    var range = ranges[i];
                    catagories[i] = range.lowerRange+"~"+range.higherRange;
                    showdatas[i] = range.numOfUsers;
                }*/

                // 使用
                require(
                    [
                    'echarts',
                    'echarts/chart/radar'
                    ],
                function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('radar')); 
               
                var option = {
                    title : {
                        text: '预算 vs 开销（Budget vs spending）',
                        subtext: '纯属虚构'
                    },
                    tooltip : {
                        trigger: 'axis'
                    },
                    legend: {
                        orient : 'vertical',
                        x : 'right',
                        y : 'bottom',
                        data:['预算分配（Allocated Budget）']
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    polar : [
                    {
                     indicator : [
                     { text: '销售（sales）', max: 6000},
                     { text: '管理（Administration）', max: 16000},
                     { text: '信息技术（Information Techology）', max: 30000},
                     { text: '客服（Customer Support）', max: 38000},
                     { text: '研发（Development）', max: 52000},
                     { text: '市场（Marketing）', max: 25000}
                     ]
                 }
                 ],
                 calculable : true,
                 series : [
                 {
                    name: '预算 vs 开销（Budget vs spending）',
                    type: 'radar',
                    data : [
                    {
                        value : [4300, 10000, 28000, 35000, 50000, 19000],
                        name : '预算分配（Allocated Budget）'
                    }
                    ]
                }
                ]
            };


                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
            );

    });

});