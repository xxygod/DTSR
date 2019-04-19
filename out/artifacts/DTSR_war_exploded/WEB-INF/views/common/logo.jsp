
<style type="text/css">
.logo {
	position: inherit;
	width: 100%;
	height: 30%;
	top: -10%;
}
</style>
<div class="logo">
	<a
		href="${pageContext.request.contextPath}/host/getTemperature?hostGuid=${Host.hostGuid}&hostName=${Host.hostName}">
		<img alt="" height="25%" width="15%"
		src="${pageContext.request.contextPath}/images/button1.png">
	</a> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <a
		href="${pageContext.request.contextPath}/host/getCabinetDiagram?hostGuid=${Host.hostGuid}">
		<img alt="" height="25%" width="15%"
		src="${pageContext.request.contextPath}/images/button2.png">
	</a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <a
		href="${pageContext.request.contextPath}/host/getHostTemperatureList?hostGuid=${Host.hostGuid}">
		<img alt="" height="25%" width="15%"
		src="${pageContext.request.contextPath}/images/button3.png">
	</a> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <a
		href="${pageContext.request.contextPath}/host/getHostHistory?hostGuid=${Host.hostGuid}">
		<img alt="" height="25%" width="15%"
		src="${pageContext.request.contextPath}/images/button4.png">
	</a>
</div>