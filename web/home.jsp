<%@ page import="db.News" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BITLAB BLOG</title>
    <%@include file="head.jsp"%>
</head>
<body>
    <%@include file="navbar.jsp"%>
    <div class="container" style="min-height: 500px;">
        <div class="row mt-3">
            <div class="col-12">
                <%
                    ArrayList<News> news = (ArrayList<News>) request.getAttribute("novosti");
                    if(news!=null){
                        for(News ns : news){
                %>
                <div class="row mt-3">
                    <div class="col-11 mx-auto p-3" style="background-color: lightgrey;">
                        <a href="/details?id=<%=ns.getId()%> "class="text-dark text-decoration-none"><%=ns.getTitle()%></a>
                        <p class="mt-2"><%=ns.getContent()%></p>
                        <p class="mt-2"> Posted at <strong><%=ns.getPost_date()%></strong>
                        </p>
                    </div>
                </div>
                <%
                    }
                    }
                %>
            </div>
        </div>
    </div>
</body>
</html>
