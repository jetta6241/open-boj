<%@page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="default.css"/>
        <title>Administrator  Add an Contest</title>
    </head>
    <body>
        <s:include value="top.jsp" ></s:include>

        <div class="content_left">
            <div class="date">Edit an Contest</div>
            <div class="newsitem">
                <div class="title">Please fill the following item</div>
                <div class="body">
                    <s:form cssClass="form" action="storeAddContest" method="post">
                    <p><span>Title</span><s:textfield cssClass="input" id="title" name="contest.cTitle" size="60"></s:textfield></p>
                    <p><span>Author</span><s:textfield cssClass="input" id="title" name="contest.cAuthor" size="30"></s:textfield></p>
                    <p><span>Start Time</span><s:textfield cssClass="input" name ="contest.cStart" size="30" value="2009-03-30 00:00:00" />&nbsp;&nbsp;YYYY-MM-DD HH:MM:SS</p>
                    <p><span>End Time</span><s:textfield cssClass="input" name ="contest.cEnd" size="30" value="2009-04-01 00:00:00" />&nbsp;&nbsp;YYYY-MM-DD HH:MM:SS</p>
                    <p><span></span><s:submit cssClass="input" value="Add"></s:submit><s:reset cssClass="input"></s:reset></p>
                    </s:form>
                </div>
                <div class="clearer"><span></span></div>
            </div>
        </div>
    </body>
</html>