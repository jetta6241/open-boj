<%@page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="default.css"/>
        <title>Administrator Edit an Contest</title>
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
            <div class="date">Edit " <s:property value="contest.cTitle" /> "</div>
            <div class="newsitem">
                <div class="title">Information</div>
                <div class="body">
                    <ul class="warn">
                        <s:fielderror />
                        <s:actionerror />
                    </ul>
                    <s:form action="storeEditContest?cid=%{contest.cID}" method="post">
                    <p><span>Title</span><s:textfield cssClass="input" id="title" name="contest.cTitle" size="60" value="%{contest.cTitle}"></s:textfield></p>
                    <p><span>Author</span><s:textfield cssClass="input" id="title" name="contest.cAuthor" size="30" value="%{contest.cAuthor}"></s:textfield></p>
                    <p><span>Start Time</span><s:textfield cssClass="input" name ="contest.cStart" size="30" value="%{contest.cStart}" />&nbsp;&nbsp;YYYY-MM-DD HH:MM:SS</p>
                    <p><span>End Time</span><s:textfield cssClass="input" name ="contest.cEnd" size="30" value="%{contest.cEnd}" />&nbsp;&nbsp;YYYY-MM-DD HH:MM:SS</p>
                    <p><span></span><s:submit cssClass="input" value="Update"></s:submit><s:reset cssClass="input"></s:reset></p>
                    </s:form>
                </div>
                <div class="title">Problem</div>
                    <div class="body">
                    <table>
                        <tr><th width="10%">PID</th><th width="60%">Title</th><th width="10%">Delete</th></tr>
                        <s:iterator value="problemList">
                            <tr><td><s:property value="symbol" /> ( <s:property value="pID" /> ) </td><td><s:property value="pTitle" /></td><td>Del</td></tr>
                        </s:iterator>
                        
                    </table>
                        <s:form action="addProblem2Contest?cid=%{contest.cID}" method="post">
                            <p style="text-align:center;">
                                <strong>Add Problem</strong>&nbsp;&nbsp;&nbsp;
                                <s:textfield cssClass="input"  name="nextSymbol" cssStyle="width:1em;" value="%{nextSymbol}"></s:textfield>&nbsp;&nbsp;&nbsp;
                                <s:textfield cssClass="input"  name="pid" size="10" value=""></s:textfield>&nbsp;&nbsp;&nbsp;<s:submit cssClass="input" cssStyle="padding:1px;" value="Add"></s:submit></p>
                        </s:form>
                    <p></p>
                    
                    </div>
                <div class="clearer"><span></span></div>
            </div>
        </div>
    </body>
</html>