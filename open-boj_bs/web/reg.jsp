<%--
    Document   : reg.jsp
    Created on : 2009-3-20, 21:31:52
    Author     : liheyuan
    Register new account
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>PK @ BJTU  Register</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" type="text/css"/>
        <style>
            /**注册框*/
            .reg{float:none;width:400px;margin:10px auto 10px auto;text-align:left;}
        </style>
    </head>
    <body>
    <div id="container" >
        <!--Head(Logo)-->
        <s:include value="top.jsp" />
        <!---Head(Logo) ends-->
        <!--Content-->
        <div id="content" style="width:90%;">
       
        <div class="reg">
        <h2>Register an new Account.</h2>
        <div class="box" style="width:450px;">
         User pass make up of a-zA-Z0-9*_ length 4-30
        <s:form cssClass="form" method="post" action="storeReg">
                <ul class="warn">
                        <s:fielderror></s:fielderror>
                        <s:actionerror></s:actionerror>
                </ul>
                <p><span class="require">Username</span><s:textfield cssClass="text" name="user.uUser" /></p>
                <p><span class="require">PassWord1</span><s:password cssClass="text" name="user.uPass" /></p>
                <p><span class="require">PassWord2</span><s:password cssClass="text" name="pass2" /></p>
                <p><span>Email</span><s:textfield cssClass="text" name="user.uEmail" /></p>
                <p><span>Nick</span><s:textfield cssClass="text" name="uEmail.uSchool" /></p>
                <p><span></span><s:submit cssClass="text" type="submit" name="button" id="button" value="Register" class="btn" />
                <s:reset type="reset" cssClass="text" name="button2" id="button2" value="Reset" class="btn" />
                </p>
        </s:form>
        </div>
        </div>
        </div>

        <!--Content ends-->

        <s:include value="bottom.jsp"/>
    </div>
    </body>
</html>
