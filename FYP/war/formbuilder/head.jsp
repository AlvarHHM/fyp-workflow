
<div id="topContainer">
	<div id="banner">
		<img src="/Client/img/logo40.png">
	</div>
	<div id="userMenu">
			<div id="userAction">
			<a href="#"><img src="/formbuilder/img/logout.png" style="width: 20; height: 20;"/></a>
        </div>
		<div id="userHi">
		<labe>Welcome, ${sessionScope.BUILDEREMP.nickName} ${sessionScope.BUILDEREMP.engSurname}</label>
		<%=session.getAttribute("BUILDEREMP") %>
		</div>
    </div>
 </div>