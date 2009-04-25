<%@page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="default.css"/>
        <title>Administrator Edit an Problem</title>
        <style type="text/css">
            body .title
            {
                margin-bottom:10px;
            }
            body span
            {
                width:8em;
                float:left;
                font-size:20px;
                font-weight:bold;
            }
            body p
            {
                clear:both;
                margin:5px 2px 10px 2px;
            }
            body p .input
            {
                border:1px solid #000;
                background:#fff;
            }
        </style>
<script type="text/javascript" src="./wymeditor/jquery/jquery.js"></script>
<!-- WYMeditor main JS file, packed version -->
<script type="text/javascript" src="./wymeditor/wymeditor/jquery.wymeditor.pack.js"></script>

<script type="text/javascript">

/* Here we replace each element with class 'wymeditor'
 * (typically textareas) by a WYMeditor instance.
 *
 * We could use the 'html' option, to initialize the editor's content.
 * If this option isn't set, the content is retrieved from
 * the element being replaced.
 */

jQuery(function() {
    jQuery('.wymeditor').wymeditor();
});

</script>


    </head>
    <body>
       <s:include value="top.jsp" ></s:include>

        <div class="content_left">
            <div class="date">Edit an new Problem</div>
            <div class="newsitem">
                <div class="title">Please Edit carefully</div>
                <ul class="warn">
                        <s:fielderror />
                        <s:actionerror />
                </ul>
                <div class="body">
                    <s:form action="imageUpload?pid=%{pid}" enctype="multipart/form-data">
                    <ul class="warn">
                        <s:actionerror />
                        PIC Upload :<br>
                            <s:url value="/problems/%{#session.pic}" />
                    </ul>
                    <p><span>Image File </span><s:file cssClass="input" name="file" />&nbsp;&nbsp;&nbsp;<s:submit value="Upload" /></p>
                    </s:form>
                    <s:form action="storeEditProblem?pid=%{pid}" method="post" enctype="multipart/form-data">
                    <p><span>Title</span><s:textfield cssClass="input" id="title" name="title" size="60" value="%{title}"></s:textfield></p>
                    <p><span>Time Limit</span><s:textfield cssClass="input" name="tle" size="60" value="%{tle}" /></p>
                    <p><span>Memory Limit</span><s:textfield cssClass="input" name="mle" size="60" value="%{mle}"></s:textfield></p>
                    <p><span>Lock</span><s:checkbox value="true" value="%{lock}" name="lock"></s:checkbox></p>
                    <p><span>HTML</span><s:textarea name="html" cssStyle="width:90%;height:600px;" cssClass="wymeditor" value="%{html}" /></p>
                    <p><span></span><s:submit cssClass="input" value="Update" cssClass="wymupdate"></s:submit><s:reset cssClass="input"></s:reset></p>
                    </s:form>
                </div>
                <div class="clearer"><span></span></div>
            </div>
        </div>
    </body>
</html>