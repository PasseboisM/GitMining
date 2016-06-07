function GetQueryString(name) { 
var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i"); 
var r = window.location.search.substr(1).match(reg); 
if (r!=null) return (r[2]); return null; 
}

var full_name=GetQueryString("fn"); 
// 路径配置
/*require.config({
    paths: {
        echarts: 'http://echarts.baidu.com/build/dist'
    }
});*/
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
            data : {type:'repo',param:full_name},
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
                var myChart = echarts.init(document.getElementById('radar'));

                var option = {
                    title: {
                        text: '项目评分'
                    },
                    tooltip: {},
                    legend: {
                        data: ['score']
                    },
                    radar: {
                        // shape: 'circle',
                        indicator: radar_indicator
                    },
                    series: [{
                        name: '项目评分',
                        type: 'radar',
                        // areaStyle: {normal: {}},
                        data: [{value:showdatas,name:"score"}]
                    }]
                };

                // 为echarts对象加载数据
                myChart.setOption(option);

               
            }
        });
    });

});





// });
