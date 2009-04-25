<%@page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="default.css"/>
        <title>Administrator Post Message</title>
    </head>
    <body>
        <s:include value="top.jsp" ></s:include>
        <div class="content_left">
            <div class="date">Post Message on the front</div>
            <div class="newsitem">
                <s:if test="#application.pk_msg!=null">
                <p><marquee scrollAmount=2>Notice : <s:text name="#application.pk_msg" /></marquee></p>
                </s:if>
                <div class="title">Please fill the following item</div>
                <div class="body">
                    <s:actionerror />
                    <s:form cssClass="form" action="postMessage" method="post">
                        <p>滚动条文字 : </p>
                        <p>如果要发布链接，请将所有的信息写入a标签内</p>
                        <p><s:textarea cssClass="input" id="title" cssStyle="width:600px;height:250px;" name="topMsg" value="%{topMsg}"/></p>
                        <p><s:submit value="Post" /></p>
                    </s:form>
                </div>
                <div class="clearer"><span></span></div>
            </div>
        </div>
    </body>
</html>