<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Problem <s:property value="problem.pID" /></title>
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
            /**题目的头部信息*/
            #ptitle{border:0px;margin:5px auto 5px auto;color:green;}
            h2{font-weight:bold;}
            /**题目的内容信息*/
            #pcontent{margin:2px auto 3px auto;width:96%;border-top:1px solid #666;float:none;clear:both;}
            #pcontent p{font-size:14px;margin:3px;line-height:1.4em;}
            #pcontent h3{margin:2px;line-height:1.0em;font-size:20px;}
            /**提交区*/
            #psubmit {text-align:center;border-top:1px solid #777;}
            #psubmit a, #psubmit a:visited{color:#000;padding:5px;border:1px solid #555;}
            #psubmit a:hover{background:#ddd;}
        </style>
    </head>
    <body>
    <div id="container" >
        <!--Head(Logo)-->
        <s:include value="top.jsp" />
        <!---Head(Logo) ends-->

        <!--Content-->
        <div id="content" style="width:90%;">
                <h2 style="text-align:center;"><s:property value="problem.pTitle" /></h2>
                <!--Head of Problem-->
                <table id="ptitle">
                    <tbody>
                        <tr>
                            <td style="width:6.5em;">Time Limit:</td>
                            <td style="width:6em;"><s:property value="problem.pTLE" />ms</td>
                            <td style="width:10em;">Memory Limit:</td>
                            <td><s:property value="problem.pMLE" />Kb</td>
                        </tr>
                        <tr>
                        <tr>
                            <td>Accepted :</td>
                            <td><s:property value="problem.pAC" /></td>
                            <td>Total Submission:</td>
                            <td><s:property value="problem.pSubmit" /></td>
                        </tr>
                        </tr>
                    </tbody>
                </table>
                <!--Edn head of Problem-->
        </div>

        <!--Content of Problem-->
        <div id="pcontent">
            <s:include value="%{problem.pFile}" />
        <p id="psubmit"><br /><a href="submit?pid=<s:property value="problem.pID" />">Submit</a></p>
        </div>
        <!--End Content of Problem-->
        
        <!--Content ends-->

        <s:include value="bottom.jsp"/>
    </div>
    </body>
</html>
