<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>PK @ BJTU  Compile Error</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" type="text/css"/>
        <style>
            /*文本框*/
            .text{background:#eee;border:1px solid  #666666;}
            /*按钮*/
            .btn{background:#FFFFFF;border:1px solid #666;}
            /**链接*/
            #content a,#content a:visited{text-decoration:none;border:1px solid black;color:#06f;padding:5px;margin:5px;}
            #content a:hover{background:#eee;}
        </style>
    </head>
    <body>
    <div id="container" >
        <!--Head(Logo)-->
        <s:include value="top.jsp" />
        <!---Head(Logo) ends-->

        <!--Content-->
        <div id="content" style="width:90%;">
        <h3>Compile error</h3>
        <div class="box">
        <ul>
            <s:fielderror></s:fielderror>
            <s:actionerror></s:actionerror>
            <li><pre><s:property value="error" /></pre></li>
        </ul>
        </div>       
        </div>
        <!--Content ends-->

        <s:include value="bottom.jsp"/>
    </div>
    </body>
</html>
