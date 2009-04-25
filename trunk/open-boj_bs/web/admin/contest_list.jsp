<%@page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="default.css"/>
        <title>Administrator List all Contest</title>
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
                line-height:2em;
                border-bottom:1px dashed #555;
            }
        </style>
    </head>
    <body>
        <s:include value="top.jsp" ></s:include>
        <div class="content_left">
            <div class="date">Edit an Contest</div>
            <div class="newsitem">
                <div class="body">
                    <ul class="warn">
                        <s:actionerror />
                    </ul>
                    <table>
                            <tr><th width="5%">CID</th><th width="60%">Contest Title</th><th width="10%">Edit</th><th width="10%">Delete</th></tr>
                        <s:iterator value="contestList">
                            <tr><td><s:property value="cID" /></td><td><s:property value="cTitle" /></td><td><a href="editContest?cid=<s:property value="cID" />">Edit</a></td><td>Del</td></tr>
                        </s:iterator>
                    </table>
                </div>
                <div class="clearer"><span></span></div>
            </div>
        </div>
    </body>
</html>