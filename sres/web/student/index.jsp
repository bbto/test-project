<%@include file="../layout/header.jspf" %>
<%@ page import="java.util.ArrayList, com.sres.model.Activity,com.sres.model.StudentSubject" %>
<!-- start content -->
<div id="content">
    <div class="post">
        <h1 class="title"><a>Competencias existentes</a></h1>
        <p class="byline">&nbsp;</p>
        <div class="entry">
            <ul>
                <%
                    ArrayList<StudentSubject> ss = current_user.getStudentsSubjects();
                    for(int i=0; i<ss.size(); i++) {
                        StudentSubject stud = ss.get(i);
                %>
                <li>
                    <%=stud.getSubjectName()%>
                </li>
                <%}%>
                </ul>
        </div>
    </div>
</div>
<!-- end content -->
<%@include file="../layout/footer.jspf" %>