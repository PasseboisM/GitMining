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
        $.ajax(url, {
            type : 'GET',
            // async : false,
            // contentType : 'application/json',
            // dataType : 'json',
            data : {type:'general',param:'UserDistOverFollower'},
            success : function(data) {
                // Set up the chart
                var ranges = JSON.parse(data).ranges;
                var catagories = []
                var showdatas = []
                for (var i = 0; i < ranges.length; i++) {
                    var range = ranges[i];
                    catagories[i] = range.lowerRange+"~"+range.higherRange;
                    showdatas[i] = range.numOfUsers;
                }

                // 使用
                require(
                    [
                    'echarts',
                    'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
                    ],
                function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('follower')); 
               
                var option = {
                    tooltip: {
                        show: true
                    },
                    legend: {
                        data:['销量']
                    },
                    xAxis : [
                    {
                        type : 'category',
                        data : catagories
                    }
                    ],
                    yAxis : [
                    {
                        type : 'value'
                    }
                    ],
                    series : [
                    {
                        "name":"销量",
                        "type":"bar",
                        "data":showdatas
                    }
                    ]
                };

                // 为echarts对象加载数据 
                myChart.setOption(option); 
                }
            );

                /*var companychart = new Highcharts.Chart({
                    chart : {
                        renderTo : 'follower',
                        type : 'column',
                        margin : 100,
                        options3d : {
                            enabled : true,
                            alpha : 5,
                            beta : 15,
                            depth : 50,
                            viewDistance : 25
                        }
                    },
                    title : {
                        text : '用户关注数'
                    },
                    plotOptions : {
                        column : {
                            depth : 25
                        }
                    },
                    xAxis : {
                        categories : catagories
                    },
                    yAxis : {
                        title : {
                            text : '用户个数'
                        },
                    },
                    tooltip : {
                        valueSuffix : '人'
                    },
                    series : [ {
                        name : '用户',
                        data : showdatas
                    } ]
                });*/
            }
        });

    });

});



