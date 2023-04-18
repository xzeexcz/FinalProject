<%@ page import="db.News" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="db.Comments" %>
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
          <%
            News ns = (News) request.getAttribute("nv");
            if(ns!=null){
          %>
          <div class="row mt-3">
            <div class="col-11 mx-auto p-3" style="background-color: lightgrey;">
              <h2><%=ns.getTitle()%></h2>
              <p class="mt-2"><%=ns.getContent()%></p>
              <p class="mt-2">
                Posted at <strong><%=ns.getPost_date()%></strong>
              </p>
              <%
                if(currentUser!=null){
              %>
              <div class="row mt-2">
                <div class="col-12">
                  <form action="/addComment" method="post">
                    <input type="hidden" name="news_id" value="<%=ns.getId()%>">
                    <textarea class="form-control" name="comment" placeholder="Write a comment"></textarea>
                    <button class="btn btn-success mt-3">ADD COMMENT</button>
                  </form>
            </div>
          </div>
          <%
            }
          %>
              <hr>
              <%
                ArrayList<Comments> comments = (ArrayList<Comments>) request.getAttribute("com");
                if(comments!=null){
                  for(Comments come:comments) {
              %>
              <div class="row mt-2">
                <div class="col-12">
                  <h5><%=come.getUsers_id().getFullName()%></h5>
                  <p><%=come.getComment()%></p>
                  <p>At <strong><%=come.getPost_date()%></strong></p>
                </div>
              </div>
              <%
                  }
                }
              %>
        </div>
      </div>
          <%
            }
          %>
    </div>
      </div>
    </div>
</body>
</html>