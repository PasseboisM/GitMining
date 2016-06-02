// require.config({
//     paths: {
//         echarts: 'http://echarts.baidu.com/build/dist'
//     }
// });
// 基于准备好的dom，初始化echarts图表
var myChart = echarts.init(document.getElementById('qwe'));

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
        indicator: [{
            name: 'famous',
            max: 10
        }, {
            name: 'size',
            max: 10
        }, {
            name: 'hot',
            max: 10
        }, {
            name: 'popular',
            max: 10
        }, {
            name: 'mature',
            max: 10
        }, {
            name: 'contributor',
            max: 10
        }]
    },
    series: [{
        name: '项目评分',
        type: 'radar',
        // areaStyle: {normal: {}},
        data: [{
                value: [4, 7, 8, 3, 5, 9],
                name: 'score'
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
