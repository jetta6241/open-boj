<%--
    Document   : index.jsp
    Created on : 2009-3-20, 21:31:52
    Author     : liheyuan
    Home page of Open-BOJ
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>PK @ BJTU  Ballons</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" type="text/css"/>
        <style>
            .form p
            {

                padding:2px;
                margin:1px;
            }

            .form p span
            {
                float:left;
                width:7em;
            }

            .form .text
            {
                border:1px solid #555;
                background:#fff;
                padding:2px;
            }

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
            .blue{color:#0066ff;font-weight:bold;}
            .red{color:#ff0000;font-weight:bold;}
            .green{color:#00ff00;font-weight:bold;}
        </style>
    </head>
    <body>
        <div id="container" >
            <!--Head(Logo)-->
            <s:include value="top.jsp" />
            <!---Head(Logo) ends-->

            <!--Content-->
            <div id="content" style="width:90%;">
                <ul class="warn">
                    <s:fielderror />
                    <s:actionerror />
                </ul>
                <h3>Ballons</h3>
                <s:form cssClass="form" action="ballon" method="get">
                    Running Contest : <select class="text" name="cid">
                        <s:iterator value="contestList">
                            <option value="<s:property value="cID" />"><s:property value="cTitle" /></option>
                        </s:iterator>
                    </select>
                    <s:submit cssClass="input" value="Update" />
                </s:form>
            </div>
            <table>
                <tr><th width="20%">Problem</th><th width="50%">User</th><th width="20%">Clear</th></tr>
                <s:iterator value="ballonList">
                    <tr><td><span class="blue"><s:property value="symbol" /></span> (<s:property value="pID" />) </td><td><s:property value="user" /></td><td><a class="red" href="sendBallon?bid=<s:property value="bID" />">Clear</a></td></tr>
                </s:iterator>
            </table>
            <!--Content ends-->

            <s:include value="bottom.jsp"/>
        </div>
    </body>
</html>
