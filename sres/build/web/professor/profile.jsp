<%@include file="../layout/header.jspf" %>
<%@ page import="java.util.ArrayList, com.sres.model.Activity,com.sres.model.StudentSubject,com.sres.model.Subject" %>
<!-- start content -->
<div id="content">
    <div class="post">
        <h1 class="title"><a><%=current_user.getName()%></a></h1>
        <p class="byline">&nbsp;</p>
        <div class="entry">
            <h3>Competencias dirigidas</h3>
            <ol>
                <% ArrayList<Subject> studentsSubjects = current_user.getProfessorSubjects();
            for (Subject act : studentsSubjects) {
                %>
                <li><%=act.getCompetenceName()%> </li>
                <%}%>
            </ol>
        </div>
    </div>
</div>
<!-- end content -->
<%@include file="../layout/footer.jspf" %>