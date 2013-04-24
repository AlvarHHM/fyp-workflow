<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="header.jsp"%>
</head>
<body>
	<div id="bodyContainer">
		<%@ include file="head.jsp"%>
		<div id="mainContainer">
			<%@ include file="menu.jsp"%>
			<div id="mainBody">

				<fieldset class="float_left">
					<legend align='center'>Latest Approve Applications</legend>
					<table class="viewFormTable">
						<thead>
							<tr>
								<th>Application</th>
								<th>Apply Date</th>
								<th>Status</th>
								<th>Last approval</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<td><a href="approve-app.html" target="_blank">Day
										leave</a></td>
								<td>12-10-2012</td>
								<td>Processing</td>
								<td>---</td>
							</tr>
							<tr>
						</tbody>
					</table>
				</fieldset>

				<fieldset class="float_right">
					<legend>Recent Use</legend>
					<table>
						<tr>
							<td><a href="form.html" target="_blank">Day leave</a></td>
						</tr>
						<tr>
							<td><a href="#">Room booking</a></td>
						</tr>
						<tr>
							<td><a href="#">CAPS Application</a></td>
						</tr>
					</table>
				</fieldset>
				<fieldset class="float_left">
					<legend align='center'>Latest Application</legend>
					<table class="viewFormTable">
						<thead>
							<tr>
								<th class="">Application</th>
								<th>Apply Date</th>
								<th>Status</th>
								<th>Last approval</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<td><a href="applied-app.html" target="_blank">Day
										leave</a></td>
								<td>12-10-2012</td>
								<td>Processing</td>
								<td>---</td>
							</tr>
							<tr>
								<td><a href=''>Continuing Education Fund</a></td>
								<td>22-01-2012</td>
								<td>Approved</td>
								<td>Tom</td>
							</tr>
							<tr>
								<td><a href=''>Room booking</a></td>
								<td>02-01-2012</td>
								<td>Reject</td>
								<td>Mary</td>
							</tr>
						</tbody>
					</table>
				</fieldset>
			</div>
		</div>
	</div>
</body>
</html>