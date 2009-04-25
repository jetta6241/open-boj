<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="tool.*" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>PK @ BJTU  Home</title>
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
        <h3>Welcome to PK here!</h3>
        <div class="box">
        <p>Are you crazy about programming ? Yes , code is one of the most beautiful things in this world . But it takes a very hard time to become a real strong man in our computer world .
        Participate the contest now , you will learn more than just coding skill or Algorithm. Fight for honer now!</p>
        <p style="color:red;font-weight:bold;"></p>
        </div>

        <s:if test="#session.login==null">
        <div class="splitcontentleft" style="width:30%;">
        <div class="box" >
            <h2>Navigation</h2>
            <ul>
                <li><a href="login.jsp">Login</a></li>
                <li><a href="reg.jsp">Register</a></li>
            </ul>
        </div>
        </div>
        <div class="splitcontentright" style="width:50%">
        <div class="box">
            <h2>Login</h2>
            <s:form cssClass="form" method="post" action="chkLogin">
              <p><span>Username</span><s:textfield name="user.uUser" cssClass="text" /></p>
              <p><span>Password</span><s:password name="user.uPass" cssClass="text"/></p>
              <p><span></span><input type="submit" name="button" id="button" value="Login" class="btn" />
              <a href="#">Find PWD</a> | <a href="reg.jsp">Register</a></p>
            </s:form>
          </div>
        </div>
        </s:if>
        </div>
        <!--Content ends-->

        <s:include value="bottom.jsp"/>
    </div>
    </body>
</html>
