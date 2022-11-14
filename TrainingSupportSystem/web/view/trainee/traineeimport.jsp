<%-- 
    Document   : traineeimport
    Created on : Oct 20, 2022, 10:25:11 PM
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
        <h1>Hello World!</h1>
        <form method="post" action="traineelist" enctype="multipart/form-data">
            <input type="hidden" name="action" value="import"/>
            <select  name="class_choice">
                <option value="-1">Class</option>
                <option value="1">SE1632</option>
                <option value="2">SE1631</option>
            </select><br/>
            Select file to upload: <input type="file" name="file" size="60" /><br /><br /> 
            <input type="submit" value="Upload" />
        </form>
    </body>
</html>
