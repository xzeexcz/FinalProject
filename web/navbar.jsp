<%@ page import="db.Users" %><%

    Users currentUser = (Users) session.getAttribute("CURRENT_USER");

%>
<div class="container">
    <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: rgba(35,67,105,0.85);">
        <div class="container-fluid">
            <a class="navbar-brand" href="/home">BITLAB BLOG</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <%
                        if(currentUser!=null){
                    %>
                    <li class="nav-item">
                        <a class="nav-link" href="#"><%=currentUser.getFullName()%></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/profile">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/settings">Settings</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/logout">Logout</a>
                    </li>
                    <%
                        String redirect = "/404";
                        if(currentUser.getRole_id().getId()==1) {
                            redirect = "/addNews";
                    %>
                    <li class="nav-item">
                        <a class="nav-link" href=<%=redirect%>>Add News</a>
                    </li>
                    <%
                        }
                    %>
                    <%
                    }else{
                    %>
                    <li class="nav-item">
                        <a class="nav-link" href="/home">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/login">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/register">Register</a>
                    </li>
                    <%
                        }
                    %>
                </ul>
            </div>
        </div>
    </nav>
</div>