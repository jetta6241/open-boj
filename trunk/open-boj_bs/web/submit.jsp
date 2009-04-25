<%--
    Document   : reg.jsp
    Created on : 2009-3-20, 21:31:52
    Author     : liheyuan
    Submit an Run
--%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%
request.setCharacterEncoding("UTF-8");
%>
<html>
    <head>
        <title>PK @ BJTU  Submit <s:property value="#parameters.pid" /></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" type="text/css"/>
        <style>
            /*文本框*/
            .text{background:#ECFFF5;border:1px solid  #666666;}
            /**文本区域，代码区*/
            .textarea{background:#ECFFF5;border:1px solid  #666666;background-image:url(images/bjtu.gif);background-repeat:no-repeat;background-position:50% 50%;}
            /*按钮*/
            .btn{background:#FFFFFF;border:1px solid #666;}
            /**注册框*/
            .submit{float:none;width:98%;margin:10px auto 10px auto;text-align:left;}
            table td{font-weight:bold;line-height:2em;}

        </style>
        <script language="javascript">
            function shua()
            {
                img_code.src='genereatecode';
            }
        </script>
    </head>
    <body>
        <div id="container" >
            <!--Head(Logo)-->
            <s:include value="top.jsp" />
            <!---Head(Logo) ends-->

            <!--Content-->
            <div id="content" style="width:90%;">
                <div class="submit">
                    <h2>Submit Your solution <s:property value="#parameters.pid" /></h2>
                    <div class="box">
                        <s:form id="form" method="post" action="storesubmit">
                            <table width="100%" border="0">
                                <s:fielderror cssStyle="color:red;" />
                                <tr>
                                    <td width="20%">Problem ID</td>
                                    <td colspan="2">
                                        <s:textfield name="pid" style="width:4em;" id="pid" class="text" value="%{#parameters.pid}"></s:textfield>              </td>
                                </tr>
                                <tr>
                                    <td>Language </td>
                                    <td colspan="2">
                                        <s:select name="lang" class="text" list="#{'C++':'C++','C':'C','Java':'Java'}" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Your Code</td>
                                    <td colspan="2"></td>
                                </tr>
                                <tr>
                                    <td ></td>
                                    <td colspan="2"><s:textarea name="src" id="src" rows="20" cols="60" class="textarea" />                                    </td>
                                </tr>
                                <tr>
                                    <td>Enter code below</td>
                                    <td><s:textfield size="5" name="vcode"/></td>
                              </tr>
                                <tr>
                                    <td>Random Code</td>
                                    <td><img id="img_code" alt="Click to generate new code" onclick="this.src='gereratecode?'+Math.random();" src="gereratecode.action"/></td>
                              </tr>
                                <tr>
                                    <td></td>
                                    <td colspan="2"><input type="submit" class="btn" value="Submit" />&nbsp;&nbsp;&nbsp;<input type="reset" class="btn" value="Reset" /></td>
                                </tr>
                            </table>
                    </s:form>
                    </div>
                </div>
            </div>

            <!--Content ends-->

            <s:include value="bottom.jsp"/>
        </div>
    </body>
</html>
