<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp"%>
</head>
<body>
        <%@include file="navbar.jsp"%>
        <div class="container" style="min-height: 500px;">
            <div class="row mt-3">
                <div class="col-12">
                    <h3 class="text-center">PROFILE PAGE OF <%=(currentUser!=null?currentUser.getFullName():"")%></h3>
                </div>
            </div>
        </div>
</body>
</html>