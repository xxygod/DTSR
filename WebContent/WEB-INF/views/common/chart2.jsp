<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="cn.model.Temperature"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap/css/default.css">
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/css/carousel.css"
	rel="stylesheet">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.js"></script>
<script src="${pageContext.request.contextPath}/js/functions.js"></script>
<script src="${pageContext.request.contextPath}/js/cart.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script
	src="${pageContext.request.contextPath}/css/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/echarts.js"></script>
<%
	List<Temperature> tl = (List<Temperature>) request.getAttribute("TempertureList");
%>

<script type="text/javascript">
	$(function() {
		postChart();
	});
	//学员职务统计图
	function postChart() {
		//这个echarts对象应该是在echarts-all.js文件里面初始化好的，所以直接拿来用，
		var myChart = echarts.init(document.getElementById('main'));
		var option = {
			backgroundColor : '#404a59',
			title : {
				text : '实时温度',
				show : false,
				textStyle : {
					fontWeight : 'normal',
					fontSize : 16,
					color : '#F1F1F3'
				},
				left : '6%'
			},
			tooltip : {
				trigger : 'axis',
				axisPointer : {
					lineStyle : {
						color : '#57617B'
					}
				}
			},
			legend : {
				icon : 'rect',
				itemWidth : 14,
				itemHeight : 5,
				itemGap : 13,
				data : [
				        <%int size = 0;
			for (Temperature t : tl) {
				++size;
				String str;
				if (size != tl.size())
					str = "'" + t.getChannelName() + "',";
				else
					str = "'" + t.getChannelName() + "'";%>
					<%=str%>
				        <%}%>
				         ],
				right : '4%',
				textStyle : {
					fontSize : 12,
					color : '#F1F1F3'
				}
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			xAxis : [ {
				type : 'category',
				boundaryGap : false,
				axisLine : {
					lineStyle : {
						color : '#57617B'
					}
				},
				data : [
				<%for (int i = 0; i < tl.get(0).getTemOriginalLength(); i += 10) {
				String str;
				if ((i+10) >= tl.get(0).getTemOriginalLength())
					str = "'" + i + "m'";
				else
					str = "'" + i + "m',";%>
				<%=str%>
				<%}%>
				]
			} ],
			yAxis : [ {
				type : 'value',
				name : '',
				axisTick : {
					show : false
				},
				axisLine : {
					lineStyle : {
						color : '#57617B'
					}
				},
				axisLabel : {
					margin : 10,
					textStyle : {
						fontSize : 14
					}
				},
				splitLine : {
					lineStyle : {
						color : '#57617B'
					}
				}
			} ],
			series : [
			       
					{
						name : '通道2',
						type : 'line',
						smooth : true,
						symbol : 'circle',
						symbolSize : 5,
						showSymbol : false,
						lineStyle : {
							normal : {
								width : 1
							}
						},
						areaStyle : {
							normal : {
								color : new echarts.graphic.LinearGradient(0,
										0, 0, 1, [ {
											offset : 0,
											color : 'rgba(0, 136, 212, 0.3)'
										}, {
											offset : 0.8,
											color : 'rgba(0, 136, 212, 0)'
										} ], false),
								shadowColor : 'rgba(0, 0, 0, 0.1)',
								shadowBlur : 10
							}
						},
						itemStyle : {
							normal : {
								color : 'rgb(0,136,212)',
								borderColor : 'rgba(0,136,212,0.2)',
								borderWidth : 12

							}
						},
						data : [
								<%for (int i = 0, n = 0; i < 10000; i += 10) {
				String name;
				Random random = new Random();
				n = random.nextInt(50) + 1;
				if (i != 9990)
					name = n + 1 + ",";
				else
					name = n + 2 + " ";%>
										<%=name%>
										<%}%>
								]
					},

	
					   <%for (Temperature t : tl) {%>
				          
						{
							name : '<%=t.getChannelName()%>',
							type : 'line',
							smooth : true,
							symbol : 'circle',
							symbolSize : 5,
							showSymbol : false,
							lineStyle : {
								normal : {
									width : 1
								}
							},
							areaStyle : {
								normal : {
									color : new echarts.graphic.LinearGradient(0,
											0, 0, 1, [ {
												offset : 0,
												color : 'rgba(219, 50, 51, 0.3)'
											}, {
												offset : 0.8,
												color : 'rgba(219, 50, 51, 0)'
											} ], false),
									shadowColor : 'rgba(0, 0, 0, 0.1)',
									shadowBlur : 10
								}
							},
							itemStyle : {
								normal : {

									color : 'rgb(219,50,51)',
									borderColor : 'rgba(219,50,51,0.2)',
									borderWidth : 12
								}
							},
							data : [ 
							<%size=0;
							for(String str:t.getTemperatureStr()) {
								++size;
								String date;
								if (size!=t.getTemperatureStr().length)
									date = t.getTemperatureStr() + ",";
								else
									date = t.getTemperatureStr() + " ";%>
									<%=date%>
									<%}%>
									]
						},

						<%}%>
					
					
					
					
					]
		};
		myChart.setOption(option);
	}
</script>
<div class="page-header">
	<h1>
		主机1 <small>实时温度数据</small>
	</h1>
</div>
<div align="center">
	<div id="main" style="width: 110rem; height: 40rem;"></div>
</div>