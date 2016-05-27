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
                    'echarts/chart/bar',
                    'echarts/chart/line' 
                    ],
                function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('follower')); 
               
                var option = {
                    title : {
                        text: '用户关注统计图表',
                        // subtext: '纯属虚构'
                    },
                    tooltip : {
                        trigger: 'axis'
                    },
                    //legend: {
                        // data:['蒸发量']
                    //},
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            magicType : {show: true, type: ['line', 'bar']},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    calculable : true,
                    xAxis : [
                    {
                        type : 'category',
                        data : catagories,
                        axisLabel :{  
                            interval:0 ,
                            rotate: -40
                        }
                    }
                    ],
                    yAxis : [
                    {
                        type : 'value'
                        formatter: '{value} 人'
                    }
                    ],
                    series : [
                    {
                        name:'用户数',
                        type:'bar',
                        data:showdatas,
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

                    ],
                    color: [ 
                            '#1e90ff', '#87cefa', '#da70d6', '#32cd32', '#6495ed', 
                            '#ff69b4', '#ba55d3', '#cd5c5c', '#ffa500', '#40e0d0', 
                            '#ff7f50', '#ff6347', '#7b68ee', '#00fa9a', '#ffd700', 
                            '#6b8e23', '#ff00ff', '#3cb371', '#b8860b', '#30e0e0' 
                        ]
                };

                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
            );

               
            }
        });

        $.ajax(url, {
            type : 'GET',
            // async : false,
            // contentType : 'application/json',
            // dataType : 'json',
            data : {type:'general',param:'RepoDistOverFork'},
            success : function(data) {
                // Set up the chart
                var ranges = JSON.parse(data).repositoryRanges;
                var catagories = []
                var showdatas = []
                for (var i = 0; i < ranges.length; i++) {
                    var range = ranges[i];
                    catagories[i] = range.lowerBound+"~"+range.higherBound;
                    showdatas[i] = range.numOfRepos;
                }
                // 使用
                require(
                    [
                    'echarts',
                    'echarts/chart/bar',
                    'echarts/chart/line' 
                    ],
                function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('fork')); 
               
                var option = {
                    title : {
                        text: '项目复刻统计图表',
                        // subtext: '纯属虚构'
                    },
                    tooltip : {
                        trigger: 'axis'
                    },
                    //legend: {
                        // data:['蒸发量']
                    //},
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            magicType : {show: true, type: ['line', 'bar']},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    calculable : true,
                    xAxis : [
                    {
                        type : 'category',
                        data : catagories
                    }
                    ],
                    yAxis : [
                    {
                        type : 'value'，
                        formatter: '{value} 个'
                    }
                    ],
                    series : [
                    {
                        name:'用户数',
                        type:'bar',
                        data:showdatas,
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

                    ],
                    color: [ 
                            '#1e90ff', '#87cefa', '#da70d6', '#32cd32', '#6495ed', 
                            '#ff69b4', '#ba55d3', '#cd5c5c', '#ffa500', '#40e0d0', 
                            '#ff7f50', '#ff6347', '#7b68ee', '#00fa9a', '#ffd700', 
                            '#6b8e23', '#ff00ff', '#3cb371', '#b8860b', '#30e0e0' 
                        ]
                };

                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
            );
            }
        });

        $.ajax(url, {
            type : 'GET',
            // async : false,
            // contentType : 'application/json',
            // dataType : 'json',
            data : {type:'general',param:'RepoDistOverLanguage'},
            success : function(data) {
                // Set up the chart
                var ranges = JSON.parse(data).languageCounts;
                var catagories = []
                var showdatas = []
                for (var i = 0; i < ranges.length; i++) {
                    var range = ranges[i];
                    catagories[i] = range.language;
                    showdatas[i] = range.repositoryCount;
                }

                // 使用
                require(
                    [
                    'echarts',
                    'echarts/chart/bar',
                    'echarts/chart/line' 
                    ],
                function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('language')); 
               
                var option = {
                    title : {
                        text: '项目语言统计图表',
                        // subtext: '纯属虚构'
                    },
                    tooltip : {
                        trigger: 'axis'
                    },
                    //legend: {
                        // data:['蒸发量']
                    //},
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            magicType : {show: true, type: ['line', 'bar']},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    calculable : true,
                    xAxis : [
                    {
                        type : 'category',
                        data : catagories,
                        axisLabel :{  
                            interval:0 ,
                            rotate: -40
                        }
                    }
                    ],
                    yAxis : [
                    {
                        type : 'value'，
                        formatter: '{value} 个'
                    }
                    ],
                    series : [
                    {
                        name:'用户数',
                        type:'bar',
                        data:showdatas,
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

                    ],
                    color: [ 
                            '#1e90ff', '#87cefa', '#da70d6', '#32cd32', '#6495ed', 
                            '#ff69b4', '#ba55d3', '#cd5c5c', '#ffa500', '#40e0d0', 
                            '#ff7f50', '#ff6347', '#7b68ee', '#00fa9a', '#ffd700', 
                            '#6b8e23', '#ff00ff', '#3cb371', '#b8860b', '#30e0e0' 
                        ]
                };

                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
            );
                
            }
        });

        $.ajax(url, {
            type : 'GET',
            // async : false,
            // contentType : 'application/json',
            // dataType : 'json',
            data : {type:'general',param:'RepoDistOverCreateTime'},
            success : function(data) {
                // Set up the chart
                var ranges = JSON.parse(data).counts;
                var catagories = []
                var showdatas = []
                for (var i = 0; i < ranges.length; i++) {
                    var range = ranges[i];
                    catagories[i] = range.timeLo+"to"+range.timeHi;
                    showdatas[i] = range.count;
                }

                // 使用
                require(
                    [
                    'echarts',
                    'echarts/chart/bar',
                    'echarts/chart/line' 
                    ],
                function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('repo_time')); 
               
                var option = {
                    title : {
                        text: '项目创建时间统计图表',
                        // subtext: '纯属虚构'
                    },
                    tooltip : {
                        trigger: 'axis'
                    },
                    //legend: {
                        // data:['蒸发量']
                    //},
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            magicType : {show: true, type: ['line', 'bar']},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    calculable : true,
                    xAxis : [
                    {
                        type : 'category',
                        data : catagories,
                        axisLabel :{  
                            interval:0 ,
                            rotate: -40
                        }
                    }
                    ],
                    yAxis : [
                    {
                        type : 'value'，
                        formatter: '{value} 个'
                    }
                    ],
                    series : [
                    {
                        name:'项目个数',
                        type:'bar',
                        data:showdatas,
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

                    ],
                    color: [ 
                            '#1e90ff', '#87cefa', '#da70d6', '#32cd32', '#6495ed', 
                            '#ff69b4', '#ba55d3', '#cd5c5c', '#ffa500', '#40e0d0', 
                            '#ff7f50', '#ff6347', '#7b68ee', '#00fa9a', '#ffd700', 
                            '#6b8e23', '#ff00ff', '#3cb371', '#b8860b', '#30e0e0' 
                        ]
                };

                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
            );
                
            }
        });

        $.ajax(url, {
            type : 'GET',
            // async : false,
            // contentType : 'application/json',
            // dataType : 'json',
            data : {type:'general',param:'UserDistOverCreateTime'},
            success : function(data) {
                // Set up the chart
                var ranges = JSON.parse(data).counts;
                var catagories = []
                var showdatas = []
                for (var i = 0; i < ranges.length; i++) {
                    var range = ranges[i];
                    catagories[i] = range.timeLo+"~"+range.timeHi;
                    showdatas[i] = range.count;
                }

                // 使用
                require(
                    [
                    'echarts',
                    'echarts/chart/bar',
                    'echarts/chart/line' 
                    ],
                function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('user_time')); 
               
                var option = {
                    title : {
                        text: '用户创建时间统计图表',
                        // subtext: '纯属虚构'
                    },
                    tooltip : {
                        trigger: 'axis'
                    },
                    //legend: {
                        // data:['蒸发量']
                    //},
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            magicType : {show: true, type: ['line', 'bar']},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    calculable : true,
                    xAxis : [
                    {
                        type : 'category',
                        data : catagories,
                        axisLabel :{  
                            interval:0 ,
                            rotate: -40
                        }
                    }
                    ],
                    yAxis : [
                    {
                        type : 'value'，
                        formatter: '{value} 人'
                    }
                    ],
                    series : [
                    {
                        name:'用户数',
                        type:'bar',
                        data:showdatas,
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

                    ],
                    color: [ 
                            '#1e90ff', '#87cefa', '#da70d6', '#32cd32', '#6495ed', 
                            '#ff69b4', '#ba55d3', '#cd5c5c', '#ffa500', '#40e0d0', 
                            '#ff7f50', '#ff6347', '#7b68ee', '#00fa9a', '#ffd700', 
                            '#6b8e23', '#ff00ff', '#3cb371', '#b8860b', '#30e0e0' 
                        ]
                };

                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
            );
                
            }
        });

        $.ajax(url, {
            type : 'GET',
            // async : false,
            // contentType : 'application/json',
            // dataType : 'json',
            data : {type:'general',param:'RepoDistOverStar'},
            success : function(data) {
                // Set up the chart
                var ranges = JSON.parse(data).counts;
                var catagories = []
                var showdatas = []
                for (var i = 0; i < ranges.length; i++) {
                    var range = ranges[i];
                    catagories[i] = range.lowerStar+"~"+range.higherStar;
                    showdatas[i] = range.numOfRepos;
                }

                // 使用
                require(
                    [
                    'echarts',
                    'echarts/chart/bar',
                    'echarts/chart/line' 
                    ],
                function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('star')); 
               
                var option = {
                    title : {
                        text: '项目关注统计图表',
                        // subtext: '纯属虚构'
                    },
                    tooltip : {
                        trigger: 'axis'
                    },
                    //legend: {
                        // data:['蒸发量']
                    //},
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            magicType : {show: true, type: ['line', 'bar']},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    calculable : true,
                    xAxis : [
                    {
                        type : 'category',
                        data : catagories,
                        axisLabel :{  
                            interval:0 ,
                            rotate: -40
                        }
                    }
                    ],
                    yAxis : [
                    {
                        type : 'value'，
                        formatter: '{value} 个'
                    }
                    ],
                    series : [
                    {
                        name:'项目数',
                        type:'bar',
                        data:showdatas,
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

                    ],
                    color: [ 
                            '#1e90ff', '#87cefa', '#da70d6', '#32cd32', '#6495ed', 
                            '#ff69b4', '#ba55d3', '#cd5c5c', '#ffa500', '#40e0d0', 
                            '#ff7f50', '#ff6347', '#7b68ee', '#00fa9a', '#ffd700', 
                            '#6b8e23', '#ff00ff', '#3cb371', '#b8860b', '#30e0e0' 
                        ]
                };

                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
            );
                
            }
        });

        $.ajax(url, {
            type : 'GET',
            // async : false,
            // contentType : 'application/json',
            // dataType : 'json',
            data : {type:'general',param:'UserDistOverType'},
            success : function(data) {
                // Set up the chart
                var ranges = JSON.parse(data).counts;
                var catagories = []
                var showdatas = []
                for (var i = 0; i < ranges.length; i++) {
                    var range = ranges[i];
                    catagories[i] = range.type;
                    showdatas[i] = range.count;
                }

                // 使用
                require(
                    [
                    'echarts',
                    'echarts/chart/bar',
                    'echarts/chart/line' 
                    ],
                function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('type')); 
               
                var option = {
                    title : {
                        text: '项目语言统计图表',
                        // subtext: '纯属虚构'
                    },
                    tooltip : {
                        trigger: 'axis'
                    },
                    //legend: {
                        // data:['蒸发量']
                    //},
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            magicType : {show: true, type: ['line', 'bar']},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    calculable : true,
                    xAxis : [
                    {
                        type : 'category',
                        data : catagories,
                        axisLabel :{  
                            interval:0 ,
                            rotate: -40
                        }
                    }
                    ],
                    yAxis : [
                    {
                        type : 'value'，
                        formatter: '{value} 个'
                    }
                    ],
                    series : [
                    {
                        name:'用户数',
                        type:'bar',
                        data:showdatas,
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

                    ],
                    color: [ 
                            '#1e90ff', '#87cefa', '#da70d6', '#32cd32', '#6495ed', 
                            '#ff69b4', '#ba55d3', '#cd5c5c', '#ffa500', '#40e0d0', 
                            '#ff7f50', '#ff6347', '#7b68ee', '#00fa9a', '#ffd700', 
                            '#6b8e23', '#ff00ff', '#3cb371', '#b8860b', '#30e0e0' 
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



