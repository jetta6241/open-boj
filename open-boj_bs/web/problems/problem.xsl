<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">

<html>
<head></head>
<body>
<xsl:for-each select="problem">
<h3>Description</h3>
<p><xsl:value-of select="description"/></p>
<h3>Input</h3>
<p><xsl:value-of select="input"/></p>
<h3>Output</h3>
<p><xsl:value-of select="output"/></p>
<h3>Sample Input</h3>
<p><xsl:value-of select="sampleinput"/></p>
<h3>Sample Output</h3>
<p><xsl:value-of select="sampleoutput"/></p>
<h3>Source</h3>
<p><xsl:value-of select="source"/></p>
<xsl:if test="count(child::hint)>0">
    <h3>Hint</h3>
    <p><xsl:value-of select="hint"/></p>
</xsl:if>
</xsl:for-each>
</body>
</html>

</xsl:template>
</xsl:stylesheet>