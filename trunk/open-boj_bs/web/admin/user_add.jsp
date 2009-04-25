<%@page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="default.css"/>
        <title>Administrator  Add Users</title>
    </head>
    <body>
        <s:include value="top.jsp" ></s:include>

        <div class="content_left">
            <div class="date">Edit an Contest</div>
            <div class="newsitem">
                <div class="title">Please fill the following item</div>
                <div class="body">
                    <ul class="warn">
                        <s:actionerror />
                        <s:fielderror />
                    </ul>
                    <s:form cssClass="form" action="storeAddUsers" method="post" enctype ="multipart/form-data">
                        <p><span>File Format : </span>(In every line)&nbsp;&nbsp;user&nbsp;&nbsp;pass&nbsp;&nbsp;realname</p>
                        <p><span>User file : </span><s:file cssClass="input" name ="user" label ="userData" /></p>
                        <p><span></span><s:submit cssClass="input" /></p>
                    </s:form>
                </div>
                <div class="clearer"><span></span></div>
            </div>
        </div>
    </body>
</html>