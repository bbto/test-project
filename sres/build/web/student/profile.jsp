<%@include file="../layout/header.jspf" %>
<%@ page import="java.util.ArrayList, com.sres.model.Activity,com.sres.model.StudentSubject,com.sres.model.Subject" %>
<!-- start content -->
<div id="content">
    <div class="post">
        <h1 class="title"><a><%=current_user.getName()%></a></h1>
        <p class="byline">&nbsp;</p>
        <div class="entry">
            <h3>Competencias Aprovadas</h3>
            <ol>
                <% ArrayList<StudentSubject> studentsSubjects = current_user.getCompetitions();
            for (StudentSubject act : studentsSubjects) {
                %>
                <li><%=act.getSubjectName()%> - <%=act.getFinal_grade()%></li>
                <%}%>
            </ol>
            <h3>Competencias en curso</h3>
            <ol>
                <% ArrayList<StudentSubject> studentsSubjects2 = current_user.getCompetitionsOnCourse();
            for (StudentSubject act : studentsSubjects2) {
                %>
                <li><%=act.getSubjectName()%></li>
                <%}%>
            </ol>
        </div>
    </div>
</div>
<!-- end content -->
<%@include file="../layout/footer.jspf" %>