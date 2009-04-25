<%@taglib prefix="s" uri="/struts-tags"%>
<%@page import="tool.*" %>
<div id="header">
    <h2>&nbsp;</h2>
    <h1>Code now ! Program future!</h1>
</div>
<!--Nevigation-->
<div id="navigation">
    <ul>
        <li><a href="index">Home</a></li>

        <li><a href="contestList">Contest</a></li>
        <li><a href="run">Run</a></li>
        
        <s:if test="#session.login!=null">
            <li><a href="editUser">User</a></li>
        </s:if>
        <s:else>
            <li><a href="login">Login</a></li>
        </s:else>
        <s:if test="#session.login!=null">
            <li><a href="logout">Logout</a></li>
            <li style="background:#af4;color:red;">&nbsp;Welcome you <s:property value="#session.login.uUser" />&nbsp;&nbsp</li>
        </s:if>
        <li style="width:400px;font-weight:bold;"><marquee scrollAmount=2> Note : <% out.println(Const.CONFIG_TOP_TEXT);%></marquee></li>
    </ul>
</div>