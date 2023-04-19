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
                    if((currentUser!=null) && (currentUser.getRole_id()==1)) {
                %>
                <a type="button" class="btn btn-danger btn-sm ms-1 mt-3" data-bs-toggle="modal" data-bs-target="#deleteNews">
                    Delete news
                </a>
                <a type="button" class="btn btn-primary btn-sm ms-1 mt-3" data-bs-toggle="modal" data-bs-target="#editNews">
                    Edit news
                </a>
                <div class="modal fade" id="editNews" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Editing News</h5>
                                <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="/editNews" method="post">
                                    <input type="hidden" value="<%=ns.getId()%>" name = "news_id">
                                    <div class="row">
                                        <div class="col-12">
                                            <label>TITLE:</label>
                                        </div>
                                    </div>
                                    <div class="row mt-2">
                                        <div class="col-12">
                                            <input type="text" value="<%=ns.getTitle()%>" class="form-control" name="re_title">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-12">
                                            <label>CONTENT:</label>
                                        </div>
                                    </div>
                                    <div class="row mt-2">
                                        <div class="col-12">
                                            <input type="text" value="<%=ns.getContent()%>" class="form-control" name="re_content">
                                        </div>
                                    </div>
                                    <button class="btn btn-primary btn-sm mt-5">Save News</button>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="deleteNews" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <form action="/deleteNews" method="post">
                                <input type="hidden" name="news_id" value="<%=ns.getId()%>">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5">CONFIRM DELETING</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <h5 class="text-center">Are you sure, my man?</h5>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                                    <button type="submit" class="btn btn-danger">Yes</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <%
                    }
                %>
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
                    <%
                        if(currentUser!=null) {
                        if((currentUser.getId()==come.getUsers_id().getId())) {
                    %>
                    <a type="button" class="btn btn-danger btn-sm ms-1 mt-3" data-bs-toggle="modal" data-bs-target="#deleteComment">
                        Delete comment
                    </a>
                    <a type="button" class="btn btn-primary btn-sm ms-1 mt-3" data-bs-toggle="modal" data-bs-target="#editComment">
                        Edit comment
                    </a>
                    <div class="modal fade" id="editComment" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Editing Comment</h5>
                                    <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form action="/editComment" method="post">
                                        <input type="hidden" value="<%=come.getId()%>" name = "com_id">
                                        <div class="row">
                                            <div class="col-12">
                                                <label>COMMENT:</label>
                                            </div>
                                        </div>
                                        <div class="row mt-2">
                                            <div class="col-12">
                                                <textarea name="recomment" class="form-control" rows="5"><%=come.getComment()%></textarea>
                                            </div>
                                        </div>
                                        <button class="btn btn-primary btn-sm mt-5">Save Comment</button>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal fade" id="deleteComment" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <form action="/deleteComment" method="post">
                                    <input type="hidden" name="com_id" value="<%=come.getId()%>">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5">CONFIRM DELETING</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <h5 class="text-center">Are you sure, my man?</h5>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                                        <button type="submit" class="btn btn-danger">Yes</button>
                                    </div>
                                </form>
                            </div>
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