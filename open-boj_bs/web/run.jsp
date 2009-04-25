<%--
    Document   : contest_list.jsp
    Created on : 2009-3-20, 21:31:52
    Author     : liheyuan
    Show All of the Runs
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
int start = 0;
try
{
    start = Integer.parseInt(request.getParameter("start"));
}
catch(Exception e)
{
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>PK @ BJTU  Run List</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" type="text/css"/>
        <style>
            /**For Problem list*/
            table {width:96%;border:1px solid #467aa7;margin:10px auto 20px auto;border-collapse:collapse;}
            table th{background:#467aa7;color:white;line-height:2.5em;padding:3px;text-align:center;}
            table td{border-bottom:1px dashed #eee;line-height:1.8em;padding:3px;text-align:center;}
            /**For Pages*/
            p.page{padding-left:15px;text-align:center;}
            p.page a,p.page a:visited{border:1px solid #467aa7;padding:3px;text-decoration:none;}
            p.page a:hover{background:#f8c209;}
            p.page .cur {background:#f8c209;}
            /**For relust color*/
            .ac{color:#0066ff;font-weight:bold;}
            .wa{color:#ff0000;font-weight:bold;}
            .re{color:#fa0000;font-weight:bold;}
            .tle{color:#4f0000;font-weight:bold;}
            .mle{color:#000000;font-weight:bold;}
            .pe{color:#4411bb;font-weight:bold;}
            .ce{color:green;font-weight:bold;}
            .q{color:green;font-weight:bold;}
            .se{color:#000;font-weight:bold;}
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
        <s:actionmessage />
        <table>
            <thead><h3>Run List</h3></thead>
            <thead>
                <tr>
                    <th style="width:9%">ID</th>
                    <th style="width:10%">User</th>
                    <th style="width:5%">Problem</th>
                    <th style="width:24%">Result</th>
                    <th style="width:10%">Memory</th>
                    <th style="width:10%">Time</th>
                    <th style="width:10%">Language</th>
                    <th style="width:25%">Submit At</th>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="list" >
                    <tr>
                        <td><s:property value="rID" /></td>
                        <td><s:property value="user" /></td>
                        <td><s:property value="pID" /></td>
                        <td>
                            <s:if test="rResult=='QUEUE'" >
                                <span class="q">Queue</span>
                            </s:if>
                            <s:elseif test="rResult=='WA'">
                                <span class="wa">Wrong Answer</span>
                            </s:elseif>
                            <s:elseif test="rResult=='WAIT'" >
                                <span class="q">Waiting and Juding</span>
                            </s:elseif>
                            <s:elseif test="rResult=='AC'">
                                <span class="ac">Accepted</span>
                            </s:elseif>
                            <s:elseif test="rResult=='TLE'">
                                <span class="tle">Time Limit Exceed</span>
                            </s:elseif>
                            <s:elseif test="rResult=='RE'">
                                <span class="re">Runtime Error</span>
                            </s:elseif>
                            <s:elseif test="rResult=='CE'">
                                <span class="ce"><a href="complileError?rid=<s:property value="rID" />">Compile Error</a></span>
                            </s:elseif>
                            <s:elseif test="rResult=='PE'">
                                <span class="pe">Format Error</span>
                            </s:elseif>
                            <s:elseif test="rResult=='MLE'">
                                <span class="mle">Memory Limit Exceed</span>
                            </s:elseif>
                            <s:elseif test="rResult=='SE'">
                                <span class="mle">System Error</span>
                            </s:elseif>
                        </td>
                        <td><s:property value="rMemory" />Kb</td>
                        <td><s:property value="rTime" />ms</td>
                        <td><s:property value="rLang" /></td>
                        <td><s:property value="rSubtime" /></td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
        <!--Problem list table ends-->

        <!--Pages-->
                <p class="page">
                    <s:if test="start>=30">
                        <a href="run?start=<s:property value="start-30" />" >&lt;&lt;&nbsp;Previous&nbsp;</a>&nbsp;&nbsp;&nbsp;
                    </s:if>
                    <s:else>
                        <a href="#" >&lt;&lt;&nbsp;Previous&nbsp;</a>&nbsp;&nbsp;&nbsp;
                    </s:else>
                    <a href="run?start=<s:property value="start+30" />">&nbsp;Next&nbsp;&gt;&gt;</a></p>
        <!--Pages end-->
        
        <!--Content ends-->
    </div>
    <s:include value="bottom.jsp"/>
    </div>
    </body>
</html>
