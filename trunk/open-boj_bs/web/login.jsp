<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>PK @ BJTU  Login</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" type="text/css"/>
        <style>
            /*按钮*/
            .btn{background:#FFFFFF;border:1px solid #666;}
            /**登录框*/
            .login{float:none;width:430px;margin:10px auto 10px auto;}
            .form p span{width:5em;}
        </style>
    </head>
    <body>
    <div id="container" >
        <!--Head(Logo)-->
        <s:include value="top.jsp" />
        <!---Head(Logo) Nevigation ends-->

        <!--Content-->
        <div id="content" style="width:90%;">
       
        <div class="login">
        <h2>Login</h2>
        <div class="box">
            <ul class="warn">
                <s:actionerror />
                <s:fielderror />
            </ul>
            
                <s:form cssClass="form"  method="post" action="chkLogin">
                    <p><span>User :</span><s:textfield name="user.uUser" cssClass="text" /></p>
                  <p><span>Pass :</span><s:password name="user.uPass" cssClass="text" /></p>
                  <p><span></span><input type="submit" name="button" id="button" value="Login" class="btn" />
                  <a href="#">Find PWD</a> | <a href="reg.jsp">Register</a></p>
              </s:form>
        </div>
        </div>
        </div>

        <!--Content ends-->

        <s:include value="bottom.jsp"/>
    </div>
    </body>
</html>
