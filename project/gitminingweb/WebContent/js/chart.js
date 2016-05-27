$(function() {
	var chart;

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
				var companychart = new Highcharts.Chart({
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
				});
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
				var companychart = new Highcharts.Chart({
					chart : {
						renderTo : 'fork',
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
						text : '项目复刻数'
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
							text : '项目个数'
						},
					},
					tooltip : {
						valueSuffix : '个'
					},
					series : [ {
						name : '项目',
						data : showdatas
					} ]
				});
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
				var companychart = new Highcharts.Chart({
					chart : {
						renderTo : 'language',
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
						text : '项目语言统计图'
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
							text : '语言个数'
						},
					},
					tooltip : {
						valueSuffix : '个'
					},
					series : [ {
						name : '项目',
						data : showdatas
					} ]
				});
			}
		});


	});

});