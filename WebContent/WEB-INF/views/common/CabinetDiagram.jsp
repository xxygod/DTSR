<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="cn.model.Position"%>
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
	List<Position> Pl = (List<Position>) request.getAttribute("positionListA");
	List<Position> P2 = (List<Position>) request.getAttribute("positionListB");
	List<Position> P3 = (List<Position>) request.getAttribute("positionListC");
%>
<script type="text/javascript">
	$(function() {
		postChart();
	});

	function postChart() {
		var myChart = echarts.init(document.getElementById('main'));
		var dataDWBJ = [ 
		                <%for (Position p : Pl) {%>
		                 [ <%=p.getX()%>, <%=p.getY()%>, "<%=p.getTime()%>", "<%=p.getPosition()%>m", "<%=p.getValue()%>℃" ],
		                 <%}%>
		                 
		                 ];
		var dataSWBJ = [ 
		                <%for (Position p : P2) {%>
		                 [ <%=p.getX()%>, <%=p.getY()%>, "<%=p.getTime()%>", "<%=p.getPosition()%>m", "<%=p.getValue()%>℃" ],
		                 <%}%>
		                 
		                 ];
		var dataGZ = [
		              
		                <%for (Position p : P3) {%>
		                 [ <%=p.getX()%>, <%=p.getY()%>, "<%=p.getTime()%>", "<%=p.getPosition()%>m", "<%=p.getValue()%>℃" ],
		                 <%}%>
		              
		              ];
		var schema = [ {
			name : 'x',
			index : 0,
			text : 'x坐标'
		}, {
			name : 'y',
			index : 1,
			text : 'y坐标'
		}, {
			name : 'time',
			index : 2,
			text : '时间'
		}, {
			name : 'position',
			index : 3,
			text : '位置'
		}, {
			name : 'value',
			index : 4,
			text : '值'
		}, {
			name : 'type',
			index : 5,
			text : '类型'
		} ];
		var itemStyle = {
			normal : {
				opacity : 0.8,
				shadowBlur : 10,
				shadowOffsetX : 0,
				shadowOffsetY : 0,
				shadowColor : 'rgba(0, 0, 0, 0.5)'
			}
		};

		var img = new Image();
		var canvas = document.createElement('canvas');
		var ctx = canvas.getContext('2d');

		canvas.width = myChart.getWidth() * window.devicePixelRatio;
		canvas.height = myChart.getHeight() * window.devicePixelRatio;

		var fullImage = new Image();
		img.onload = function() {
			ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
			fullImage.src = canvas.toDataURL();
			setTimeout(function() {
				myChart.resize();
			}, 100)
		}

		img.src = '${pageContext.request.contextPath}/${cabinetDiagram.cdSrc}'

		var option = {
			backgroundColor : {
				type : "pattern",
				repeat : "repeat",
				image : fullImage
			},
			color : [ '#FF0000', '#FF6347', '#FFD700' ],
			legend : {
				y : 'top',
				x : 'lift',
				data : [ '定温火警', '升温火警', '故障' ],
				textStyle : {
					color : '#ff9900',
					fontSize : 16
				}
			},
			grid : {
				x : '10%',
				x2 : 150,
				y : '18%',
				y2 : '10%'
			},
			tooltip : {
				padding : 10,
				backgroundColor : 'rgba(0, 0, 0,0.6)',
				borderColor : '#777',
				borderWidth : 1,
				textStyle:{
					align:'left'
				},
				formatter : function(obj) {
					var value = obj.value;
					return '<div style="border-bottom: 1px solid rgba(255,255,255,.3); font-size: 18px;padding-bottom: 7px;margin-bottom: 7px">'
							+ obj.seriesName
							+ '</div>'
							+ schema[2].text
							+ '：'
							+ value[2]
							+ '<br>'
							+ schema[3].text
							+ '：'
							+ value[3]
							+ '<br>'
							+ schema[4].text
							+ '：'
							+ value[4] + '<br>';
				}
			},
			xAxis : {
				show : false,
				type : 'value',
				max : 100,
				splitLine : {
					show : false
				}
			},
			yAxis : {
				show : false,
				type : 'value',
				max : 100,
				splitLine : {
					show : false
				}
			},
			series : [ {
				name : '定温火警',
				type : 'scatter',
				itemStyle : itemStyle,
				data : dataDWBJ
			},{
				name : '升温火警',
				type : 'scatter',
				itemStyle : itemStyle,
				data : dataSWBJ 
			}, {
				name : '故障',
				type : 'scatter',
				itemStyle : itemStyle,
				data : dataGZ
			} ]
		};
		myChart.setOption(option);
	}
</script>
<div id="main" style="width: 87.663rem; height: 52rem;"></div>