<%--
    Document   : contest_list.jsp
    Created on : 2009-3-20, 21:31:52
    Author     : liheyuan
    Show All of the Runs
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	int start = 0;
	try {
		start = Integer.parseInt(request.getParameter("start"));
	} catch (Exception e) {
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>PK @ BJTU Run List</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="style.css" type="text/css" />
		<style>
/**For Problem list*/
table {
	width: 96%;
	border: 1px solid #467aa7;
	margin: 10px auto 20px auto;
	border-collapse: collapse;
}

table th {
	background: #467aa7;
	color: white;
	line-height: 2.5em;
	padding: 3px;
	text-align: center;
}

table td {
	border-bottom: 1px dashed #eee;
	line-height: 1.8em;
	padding: 3px;
	text-align: center;
}

/**For Pages*/
p.page {
	padding-left: 15px;
	text-align: center;
}

p.page a,p.page a:visited {
	border: 1px solid #467aa7;
	padding: 3px;
	text-decoration: none;
}

p.page a:hover {
	background: #f8c209;
}

p.page .cur {
	background: #f8c209;
}

/**For relust color*/
.ac {
	color: #0066ff;
	font-weight: bold;
}

.wa {
	color: #ff0000;
	font-weight: bold;
}

.re {
	color: #fa0000;
	font-weight: bold;
}

.tle {
	color: #4f0000;
	font-weight: bold;
}

.mle {
	color: #000000;
	font-weight: bold;
}

.pe {
	color: #4411bb;
	font-weight: bold;
}

.ce {
	color: green;
	font-weight: bold;
}

.q {
	color: green;
	font-weight: bold;
}

.se {
	color: #000;
	font-weight: bold;
}
</style>
	</head>
	<body>
		<div id="container">
			<!--Head(Logo)-->
			<s:include value="top.jsp" />
			<!---Head(Logo) ends-->

			<!--Nevigation-->
			<div id="navigation">
				<ul>
					<li>
						<a href="index">Home</a>
					</li>

					<li>
						<a href="contest">Contest</a>
					</li>
					<li class="selected">
						<a href="#">Run</a>
					</li>
					<li>
						<a href="#">Rank</a>
					</li>
					<li>
						<a href="#">User</a>
					</li>
				</ul>
			</div>
			<!--Nevigation ends-->

			<!--Content-->
			<!--Problem list table-->
			<div id="content" style="width: 96%;">

				<table>
					<thead>
						<h3>
							Run List
						</h3>
					</thead>
					<thead>
						<tr>
							<th style="width: 9%">
								Rank
							</th>
							<th style="width: 10%">
								User
							</th>
							<th style="width: 5%">
								Solved
							</th>
							<th style="width: 24%">
								Problem A
							</th>
							<th style="width: 10%">
								Problem B
							</th>
							<th style="width: 10%">
								Problem C
							</th>
							<th style="width: 10%">
								Problem D
							</th>
							<th style="width: 25%">
								Penalty
							</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="rankSet" status="rank">
							<tr>
								<td>
									<s:property value="#rank.count" />
								</td>
								<td>
									<s:property value="user" />
								</td>
								<td>
									<s:property value="solved" />
								</td>
								<td>
									<s:property value="penalty['1000'].acTime" />
									<s:property value="penalty['1000'].reject" />
								</td>
								<td>
									<s:property value="penalty['1001'].acTime" />
									<s:property value="penalty['1001'].reject" />
								</td>
								<td>
									<s:property value="penalty['1002'].acTime" />
									<s:property value="penalty['1002'].reject" />
								</td>
								<td>
									<s:property value="penalty['1003'].acTime" />
									<s:property value="penalty['1003'].reject" />
								</td>
								<td>
									<s:property value="total" />
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<!--Problem list table ends-->

				<!--Pages-->
				<p class="page">
					<a href="run?start=<% out.println(Math.max(0, start-30)); %>">&lt;&lt;&nbsp;Previous&nbsp;</a>&nbsp;&nbsp;&nbsp;
					<a href="run?start=<s:property value="start+30" />">&nbsp;Next&nbsp;&gt;&gt;</a>
				</p>
				<!--Pages end-->

				<!--Content ends-->
			</div>
			<s:include value="bottom.jsp" />
		</div>
	</body>
</html>
