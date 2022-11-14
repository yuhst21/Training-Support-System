<%-- 
    Document   : reprort
    Created on : Nov 7, 2022, 2:31:02 PM
    Author     : win
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <% response.setContentType("application/vnd.ms-excel");
              response.setHeader("Content-Disposition", "inline; filename=traine_tempalte.xls"); %>
    </body>
</html>
