// require.config({
//     paths: {
//         echarts: 'http://echarts.baidu.com/build/dist'
//     }
// });
// 基于准备好的dom，初始化echarts图表





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
        indicator: [{
            name: '',
            max: 0
        }, {
            name: '',
            max: 0
        }, {
            name: '',
            max: 0
        }, {
            name: '',
            max: 0
        }, {
            name: '',
            max: 0
        }, {
            name: '',
            max: 0
        }]
    },
    series: [{
        name: '项目评分',
        type: 'radar',
        // areaStyle: {normal: {}},
        data: [{
                value: [],
                name: 'score'
            },

        ]
    }]
};
var dataFake = [{
    "name": "famous",
    "data": 1
}, {
    "name": "hot",
    "data": 5
}, {
    "name": "size",
    "data": 1
}, {
    "name": "popular",
    "data": 5
}, {
    "name": "mature",
    "data": 1
}, {
    "name": "contributor",
    "data": 5
}];

var radarData = [];
var radarInfo = [];
for (var i = 0; i < dataFake.length; i++) {
    radarData.push(dataFake[i].data);
    radarInfo.push(dataFake[i].name);
}
// 为echarts对象加载数据
myChart.setOption(option);

// $.get('dataFake.json').done(function(data) {
// 填入数据
myChart.setOption({
    radar: {
        indicator: [{
          name: radarInfo[1],
          max: 10
        },
        {
          name: radarInfo[2],
          max: 10
        },
        {
          name: radarInfo[3],
          max: 10
        },
        {
          name: radarInfo[4],
          max: 10
        },
        {
          name: radarInfo[5],
          max: 10
        },
        {
          name: radarInfo[0],
          max: 10
        }


        ]
    },
    series: [{
        // 根据名字对应到相应的系列
        name: '项目评分',
        type: 'radar',
        data: [{
                value: radarData,
                name: 'score'
            },

        ]
    }]
});
// });
