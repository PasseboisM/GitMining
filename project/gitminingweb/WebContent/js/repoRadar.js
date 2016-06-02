
// require.config({
//     paths: {
//         echarts: 'http://echarts.baidu.com/build/dist'
//     }
// });
// 基于准备好的dom，初始化echarts图表
var myChart = echarts.init(document.getElementById('qwe'));

var option = {
    title: {
        text: '基础雷达图'
    },
    tooltip: {},
    legend: {
        data: ['预算分配（Allocated Budget）']
    },
    radar: {
        // shape: 'circle',
        indicator: [
           { name: '销售（sales）', max: 6500},
           { name: '管理（Administration）', max: 16000},
           { name: '信息技术（Information Techology）', max: 30000},
           { name: '客服（Customer Support）', max: 38000},
           { name: '研发（Development）', max: 52000},
           { name: '市场（Marketing）', max: 25000}
        ]
    },
    series: [{
        name: '预算 vs 开销（Budget vs spending）',
        type: 'radar',
        // areaStyle: {normal: {}},
        data : [
            {
                value : [4300, 10000, 28000, 35000, 50000, 19000],
                name : '预算分配（Allocated Budget）'
            },

        ]
    }]
};

// 为echarts对象加载数据
myChart.setOption(option);




// $(function() {
//     // var chart;
//
//     $(document).ready(function() {
//
//           $.ajax(url, {
//             //TODO
//
//
//
//
//             });
//
//     });
//
// });
