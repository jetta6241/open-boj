<%@page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="default.css"/>
        <title>Administrator List all Problem</title>
        <style type="text/css">
            body .title
            {
                margin-bottom:10px;
            }
            body span
            {
                width:8em;
                float:left;
                font-size:20px;
                font-weight:bold;
            }
            body p
            {
                clear:both;
                margin:5px 2px 10px 2px;
            }
            body p .input
            {
                border:1px solid #000;
                background:#fff;
                padding:3px;
            }
            .body ul li
            {
                font-size:18px;
                line-height:1.5em;
            }
            table
            {
                width:80%;
                border:1px solid #555;
                margin:10px auto 10px auto;
                border-collapse:collapse;
                text-align:center;
            }
            table th
            {
                font-size:18px;
                background:#555;
                color:#fff;
                line-height:1.5em;
            }
            table td
            {
                font-size:16px;
                font-weight:normal;
                line-height:2.3em;
                border-bottom:1px dashed #555;
            }
        </style>
    </head>
    <body>
        <s:include value="top.jsp" ></s:include>
        <div class="content_left">
            <div class="date">Edit Problems</div>
            <div class="newsitem">
                <div class="body">
                    <ul class="warn">
                        <s:actionerror />
                    </ul>
                    <h3 style="text-align:center;">Problem List</h3>
                    <table>
                            <tr><th width="5%">PID</th><th width="60%">Problem Title</th><th width="10%">Edit</th><th width="10%">Delete</th></tr>
                        <s:iterator value="listProblem">
                            <tr><td><s:property value="pID" /></td><td><s:property value="pTitle" /></td><td><a href="editProblem?pid=<s:property value="pID" />">Edit</a></td><td>Del</td></tr>
                        </s:iterator>
                    </table>
                    <h3 style="text-align:center;">Upload Data File</h3>
                    <ul class="warn">
                        <s:actionmessage />
                    </ul>
                    <s:form action="dataUpload" enctype="multipart/form-data" cssClass="form" cssStyle="width:90%;padding-left:50px;">
                        <p>Filename : <s:textfield size="10" cssClass="input" name="filename" value="1000.in" />&nbsp;&nbsp;Data File : <s:file cssClass="input" name="file" />&nbsp;&nbsp;&nbsp;&nbsp;<s:submit value="Upload" /></p>
                    </s:form>
                </div>
                <div class="clearer"><span></span></div>
            </div>
        </div>
    </body>
</html>