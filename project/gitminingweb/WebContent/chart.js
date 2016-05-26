$(function() {
	var chart;

	$(document).ready(function() {
		// get company
		var url = "/GitMiningServer/stat?type=general&param=UserDistOverFollower"
		$.ajax(url, {
			type : 'GET',
			// async : false,
			// contentType : 'application/json',
			// dataType : 'json',
			success : function(data) {
				// Set up the chart
				window.alert(data);
				var companychart = new Highcharts.Chart({
					chart : {
						renderTo : 'company1',
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
						text : 'Numbers of Users in each Company'
					},
					plotOptions : {
						column : {
							depth : 25
						}
					},
					xAxis : {
						categories : data.companyName
					},
					yAxis : {
						title : {
							text : 'Numbers of Users'
						},
					},
					tooltip : {
						valueSuffix : 'people'
					},
					series : [ {
						name : 'User',
						data : data.companyCount
					} ]
				});
			}
		});
	});

});