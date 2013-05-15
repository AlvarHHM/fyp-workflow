<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	var formKey = "${appKey}"
	var appJson = ${appJson};
	var pathJson = ${path};
</script>
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/jquery.ui.position.js"></script>
<script type="text/javascript"
	src="/js/jquery.jsPlumb-1.3.16-all-min.js"></script>
<script type="text/javascript" src="/js/jquery.contextMenu.js"></script>
<script type="text/javascript" src="/js/jquery.lightbox_me.js"></script>
<script type="text/javascript" src="/js/jquery.md5.min.js"></script>
<script type="text/javascript" src="/js/select2.min.js"></script>
<script type="text/javascript" src="/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/prototype.js"></script>
<script type="text/javascript" src="/js/property.js"></script>
<script type="text/javascript" src="/js/pathviewer.js"></script>
<link rel="stylesheet" type="text/css" href="/css/jquery-ui.css">
<link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link rel="stylesheet" type="text/css"
	href="/css/jquery.contextMenu.css">
<link rel="stylesheet" type="text/css" href="/css/select2.css">
<link rel="stylesheet" type="text/css" href="/css/pathbuilder2.css">
<style type="text/css">
.node-instance{
	cursor :pointer;
}

</style>
</head>

<body>

	<div id="main-container">
		<button id="save-btn" style="display: none">Save</button>
		<div id="node-detail-panel">
			<table>
				<tr>
					<td>Node Type: </td>
					<td class="node-type"></td>
				</tr>
				<tr>
					<td>Node Id: </td>
					<td class="node-Id"></td>
				</tr>
				<tr>
					<td>Timestamp: </td>
					<td class="timestamp"></td>
				</tr>
				<tr>
					<td>Approver: </td>
					<td class="approver"></td>
				</tr>
				<tr></tr>
			</table>
		</div>
		<div id="content-wrapper">
			<div id="path-canvas-wrapper">
				<div id="path-canvas"></div>
			</div>
		</div>
		<div id="right-wrapper" style="display: none">

			<div id="select-node">
				<span class="title">Add Node</span>
				<div class="start-node node-prototype node">
					<span class="node-title">Start</span>
					<div class="tcp"></div>
				</div>
				<div class="success-node node-prototype node">
					<span class="node-title">Success</span>
				</div>
				<div class="fail-node node-prototype node">
					<span class="node-title">Fail</span>
				</div>
				<div class="approval-node node-prototype node">
					<span class="node-title">Approval</span>
					<div class="tcp"></div>
					<div class="fcp"></div>
				</div>
				<div class="notice-node node-prototype node">
					<span class="node-title">Notice</span>
					<div class="tcp"></div>
				</div>
				<div class="check-node node-prototype node">
					<span class="node-title">Check</span>
					<div class="tcp"></div>
					<div class="fcp"></div>
				</div>


			</div>



		</div>




	</div>

</body>
</html>