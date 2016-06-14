function GetQueryString(name) { 
var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i"); 
var r = window.location.search.substr(1).match(reg); 
if (r!=null) return (r[2]); return null; 
}

var login=GetQueryString("login"); 
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
            // contentType:"application/x-www-form-urlencoded; charset=utf8", 
            data : {type:'user',param:login},
            success : function(data) {
                // Set up the chart
                var vertexes = JSON.parse(data).vertexes;
                console.log(vertexes);
                var catagories = [];
                var showdatas = [];
                var radar_indicator = [];
                for (var i = 0; i < vertexes.length; i++) {
                    var range = vertexes[i];
                    // catagories[i] = range.header;
                    showdatas[i] = range.mark.toFixed(2);;
                    radar_indicator[i] = {text:range.header,max:1}
                }
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
                        text: '用户数据评分雷达图'
                    },
                    tooltip : {
                        trigger: 'axis'
                    },
                    legend: {
                        orient : 'vertical',
                        x : 'right',
                        y : 'bottom',
                        data:['用户评分']
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
                     indicator : radar_indicator
                 }
                 ],
                 calculable : true,
                 series : [
                 {
                    name: '用户评分',
                    type: 'radar',
                    data : [
                    {
                        value : showdatas,
                        name : '用户评分'
                    }
                    ]
                }
                ]
            };


                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
            );

               
            }
        });

        

                

    });

});