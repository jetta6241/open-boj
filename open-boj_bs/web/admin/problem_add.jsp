<%@page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="default.css"/>
        <title>Administrator  Add an Problem</title>
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
            <div class="date">Add an new Problem</div>
            <div class="newsitem">
                <div class="title">Please fill the following item</div>
                <div class="body">
                    <s:form action="storeAddProblem" method="post" enctype="multipart/form-data">
                    <p><span>Title</span><s:textfield cssClass="input" id="title" name="title" size="60"></s:textfield></p>
                    <p><span>STD DATAIN</span><s:file cssClass="input" name ="datain" label ="STDIN" /></p>
                    <p><span>STD DATAOUT</span><s:file cssClass="input" name ="dataout" label ="STDOUT" /></p>
                    <p><span>Time Limit</span><s:textfield cssClass="input" name="tle" size="60" value="1000" /></p>
                    <p><span>Memory Limit</span><s:textfield cssClass="input" name="mle" size="60" value="65535"></s:textfield></p>
                    <p><span>Lock</span><s:checkbox value="false" name="lock" name="lock"></s:checkbox></p>
                    <p><span>Source</span><s:textfield  name="source" size="60"></s:textfield></p>
                    <p><span>Description</span><textarea  name="description" class="wymeditor" style="width:90%;height:200px;"></textarea></p>
                    <p><span>Input</span><textarea  name="input" class="wymeditor" style="width:90%;height:200px;"></textarea></p>
                    <p><span>Output</span><textarea name="output" class="wymeditor" style="width:90%;height:200px;"></textarea></p>
                    <p><span>Sample Input</span><textarea  name="sampleinput" class="wymeditor" style="width:90%;height:200px;"></textarea></p>
                    <p><span>Sample Output</span><textarea name="sampleoutput" key="sampleoutput" class="wymeditor" style="width:90%;height:200px;"></textarea></p>
                    <p><span>Hint</span><s:textarea name="hint" cssStyle="width:90%;height:200px;" cssClass="wymeditor" /></p>
                    <p><span></span><s:submit cssClass="input" value="Add" cssClass="wymupdate"></s:submit><s:reset cssClass="input"></s:reset></p>
                    </s:form>
                </div>
                <div class="clearer"><span></span></div>
            </div>
        </div>
    </body>
</html>