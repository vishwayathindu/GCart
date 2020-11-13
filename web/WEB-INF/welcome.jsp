<%-- 
    Document   : welcome
    Created on : Oct 15, 2020, 11:57:53 AM
    Author     : vishwa_p
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <s:form action="nextItem" namespace="/">
            <s:submit value="submit"></s:submit>
            
        </s:form>
    </body>
</html>
