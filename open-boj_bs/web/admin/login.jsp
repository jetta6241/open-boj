<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="default.css"/>
        <title>Administrator  Login</title>
        <style type="text/css">

        </style>
    </head>
    <body>
       
        <s:include value="top.jsp" ></s:include>
        
        <div class="content_left">
            <div class="date">Login</div>
            <div class="newsitem">
                <div class="title">Please Login first</div>
                <div class="body">
                    <ul class="warn">
                        <s:fielderror />
                        <s:actionerror cssStyle="" />
                    </ul>

                    <s:form cssClass="form" action="checkLogin" method="POST">
                        <p><span>Username</span><s:textfield cssClass="input"  name="user"></s:textfield></p>
                    <p><span>Password</span><s:password cssClass="input"  name="pass" key="pass"></s:password></p>
                    <p><span>VCode</span><s:textfield cssClass="input"  name="vcode" key="vcode" size="5"></s:textfield></p>
                    <p><span>&nbsp;</span><img style="width:60px;height:23px;margin:0px;" alt="Click to generate new code" onclick="this.src='gereratecode?'+Math.random();" src="gereratecode.action"/></p>
                    <p><span>&nbsp;</span><s:submit cssClass="input" value="Login"></s:submit>
                    <s:reset cssClass="input"></s:reset></p>
                    </s:form>
                </div>
                <div class="clearer"><span></span></div>
            </div>
        </div>
    </body>
</html>