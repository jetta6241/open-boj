<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>PK @ BJTU User Edit</title>
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
        <h2>Edit your Profile.</h2>
        <div class="box" style="width:450px;">
        Enter the old password if you want to change information.
        <s:form cssClass="form" method="post" action="storeEditUser">
                <ul class="warn">
                        <s:fielderror></s:fielderror>
                        <s:actionerror></s:actionerror>
                        <s:actionmessage />
                </ul>
                <p><span class="require">Username</span><s:property value="%{#session.login.uUser}" /></p>
                <p><span class="require">Old Pass</span><s:password cssClass="text" name="pass2" /></p>
                <p><span class="require">New Pass</span><s:password cssClass="text" name="user.uPass" /></p>
                <p><span>Email</span><s:textfield cssClass="text" name="user.uEmail" value="%{#session.login.uEmail}" /></p>
                <p><span>Nick</span><s:textfield cssClass="text" name="user.uSchool" value="%{#session.login.uSchool}"/></p>
                <p><span></span><s:submit cssClass="text" type="submit" name="button" id="button" value="Update" class="btn" />
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
