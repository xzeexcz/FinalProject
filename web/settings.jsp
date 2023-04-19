<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="head.jsp"%>
</head>
<body>
    <%@include file="navbar.jsp"%>
    <div class="container" style="min-height: 500px;">
      <div class="row mt-3">
        <div class="col-12">
          <%
            Users us = (Users) request.getSession().getAttribute("CURRENT_USER");
            if(us!=null) {
          %>
            <%
                String passwordError = request.getParameter("password_error");
                if(passwordError!=null){
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                Incorrect password, try again!
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <%
                }
            %>
            <form action="/settings" method="post">
                <input type="hidden" value="<%=us.getId()%>" name = "us_id">
                <input type="hidden" value="<%=us.getEmail()%>" name = "email">
                <div class="row">
                    <div class="col-12">
                        <label> New Full Name:</label>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <input type="text" class="form-control" name="new_name" value="<%=us.getFullName()%>">
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <label>New Password:</label>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <input type="password" class="form-control" name="new_password">
                    </div>
                </div>
                <div class="row">
                <div class="col-12">
                    <label>Repeat new Password:</label>
                </div>
            </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <input type="password" class="form-control" name="new_password2">
                    </div>
                </div>

                <button class="btn btn-primary btn-sm mt-5">Save Changes</button>
            </form>
          <%
            }
          %>
        </div>
      </div>
    </div>
</body>
</html>
