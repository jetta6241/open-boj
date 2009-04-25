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
            /*按钮*/
            .btn{background:#FFFFFF;border:1px solid #666;}
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
        <s:form cssClass="form" action="ballonLogin">
            Password for ballons : <s:password name="ballonPass" cssClass="text" />&nbsp;&nbsp;&nbsp;<s:submit cssClass="btn" value="Login"></s:submit>
        </s:form>
        </div>
        
        <!--Content ends-->

        <s:include value="bottom.jsp"/>
    </div>
    </body>
</html>
