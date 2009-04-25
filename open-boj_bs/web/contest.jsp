<%--
    Document   : problem_list.jsp
    Created on : 2009-3-20, 21:31:52
    Author     : liheyuan
    Show problems Of an contest
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import="tool.*" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>PK @ BJTU  Problem</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" type="text/css"/>
        <style>
            /**For Problem list*/
            table {width:96%;border:1px solid #467aa7;margin:10px auto 20px auto;border-collapse:collapse;}
            table th{background:#467aa7;color:white;line-height:2.5em;padding:3px;}
            table td{border-bottom:1px dashed #eee;line-height:2.5em;padding:3px;}
            table td a,table td a:visited{color:#06f;}
            table td a:hover{color:#f00;}
            /*Contest Title*/
            .con_title{text-align:center;margin:5px;color:green;}
            .con_title h3{color:#000;}
            .con_title strong{color:#06f;}
            .con_title .time{font-size:13px;font-weight:bold;color:green;padding-bottom:5px;}
            /**For Pages*/
            p.page{padding-left:15px;}
            p.page a,p.page a:visited{border:1px solid #467aa7;padding:3px;text-decoration:none;}
            p.page a:hover{background:#f8c209;}
            p.page .cur {background:#f8c209;}
        </style>
    </head>
    <body>
    <div id="container" >
        <!--Head(Logo)-->
        <s:include value="top.jsp" />
        <!---Head(Logo) ends-->

        <!--Content-->
        <!--Problem list table-->
        <div id="content" style="width:96%;">
        <h2><s:property value="contest.cTitle" /></h2>
        <table>
            <thead class="con_title">
            <strong>From &nbsp; Time : </strong><s:property value="contest.cStart" />&nbsp;&nbsp;
            <strong>To Time : </strong> <s:property value="contest.cEnd" /><br>
            <strong>Current Time : </strong><%out.println(Tools.getTime());%>&nbsp;&nbsp;
            <strong>State : </strong><s:property value="contest.cStatus" />
            <br>
            <strong>Rank : </strong><a href="rank/<s:property value="contest.cID" />.html" target="_blank">View</a>
            <br>
            </thead>
            <thead>
                <tr>
                    <th style="width:10%">ID</th>
                    <th style="width:40%">Title</th>
                    <th style="width:10%">AC/Submit</th>
                    <th style="width:10%">Ratio</th>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="listProblem" >
                <tr>
                    <td>&nbsp;&nbsp;<s:property value="symbol"/> (<s:property value="pID" />)</td>
                    <td><a href="problem?pid=<s:property value="pID" />"><s:property value="pTitle" /></a></td>
                    <td><s:property value="pAC" />/<s:property value="pSubmit" /></td>
                    <td><s:property value="pAC" />/<s:property value="pSubmit" /></td>
                </tr>
                </s:iterator>
            </tbody>
        </table>
        <p class="page" style="text-align:center;"><a href="rank/<s:property value="contest.cID" />.html" target="_blank">View The Rank</a></p>
        <!--Problem list table ends-->

        <!--Pages-->
        <!--<p class="page">Pages : <a href="f" class="cur">1</a> <a href="f">2</a></p>-->
        <!--Pages end-->
        
        <!--Content ends-->
    </div>
    <s:include value="bottom.jsp"/>
    </div>
    </body>
</html>
