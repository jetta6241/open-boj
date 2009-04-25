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
            body p
            {
                text-align:center;
            }
            body .title
            {
                text-align:center;margin-bottom:10px;
            }
        </style>
    </head>
    <body>
       
        <s:include value="top.jsp" ></s:include>
        
        <div class="content_left">
            <div class="date">Error</div>
            <div class="newsitem">
                <div class="title">Sorry , we encounter a problem</div>
                <div class="body">
                    <script type="text/javascript">
                        alert("<s:property value="returnMsg" />");
                        window.location.href='admin';
                    </script>
                </div>
                <div class="clearer"><span></span></div>
            </div>
        </div>
    </body>
</html>