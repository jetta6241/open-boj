<%--
    Document   : contest_list.jsp
    Created on : 2009-3-20, 21:31:52
    Author     : liheyuan
    Show Rank List of all submit
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>PK @ BJTU  Contest</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" type="text/css"/>
        <style>
            /**For Contest list*/
            table {width:96%;border:1px solid #467aa7;margin:10px auto 20px auto;border-collapse:collapse;}
            table th{background:#467aa7;color:white;line-height:2.5em;padding:3px;}
            table td{border-bottom:1px dashed #eee;line-height:2.5em;padding:3px;}
            table td a,table td a:visited{color:#06f;}
            table td a:hover{color:#f00;}
            /**For Pages*/
            p.page{padding-left:15px;}
            p.page a,p.page a:visited{border:1px solid #467aa7;padding:3px;text-decoration:none;}
            p.page a:hover{background:#f8c209;}
            p.page .cur {background:#f8c209;}

            .red{color:#ff0000;font-weight:bold;}
            .black{font-weight:bold;}
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
        
        <table>
            <thead><h2>Contest List</h2></thead>
            <thead>
                <tr>
                    <th style="width:10%">ID</th>
                    <th style="width:50%">Title</th>
                    <th style="width:25%">Start Time</th>
                    <th style="width:10%">Status</th>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="list">
                <tr>
                    <td><s:property value="cID" /></td>
                    <td><a href="contest?cid=<s:property value="cID" />"><s:property value="cTitle" /></a></td>
                    <td><s:property value="cStart" /></td>
                    <td>
                        <s:if test="cStatus=='Running'">
                            <span class="red"><s:property value="cStatus" /></span>
                        </s:if>
                        <s:else>
                            <span class="black"><s:property value="cStatus" /></span>
                        </s:else>
                    </td>
                </tr>
                </s:iterator>
            </tbody>
        </table>
        <!--Problem list table ends-->

        <!--Pages-->
        <!--<p class="page">Pages : <a href="f" class="cur">1</a><a href="f">2</a></p>--->
        <!--Pages end-->
        
        <!--Content ends-->
    </div>
    <s:include value="bottom.jsp"/>
    </div>
    </body>
</html>
