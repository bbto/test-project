<%@include file="../layout/header.jspf" %>
<%@ page import="java.util.ArrayList, com.sres.model.Activity,com.sres.model.StudentSubject,com.sres.model.Subject" %>
<!-- start content -->
<div id="content">
    <div class="post">
        <h1 class="title"><a>Listado de Actividades por competencias </a></h1>
        <p class="byline">&nbsp;</p>
        <div class="entry">
            <ul>
                <%
                    ArrayList<StudentSubject> ss = current_user.getStudentsSubjects();
                    for(int i=0; i<ss.size(); i++) {
                        StudentSubject stud = ss.get(i);
                        Subject subject = stud.getSubject();
                %>
                <li>
                    <%=stud.getSubjectName()%>
                    <ol>
                    <% ArrayList<Activity> activities = subject.getActivities();
                        for(Activity act : activities){
                    %>
                    <li><%=act.getName()%></li>

                    <%
                        }

                    %>
                    </ol>
                </li>
                <%}%>
                
                </ul>
        </div>
    </div>
</div>
<!-- end content -->
<%@include file="../layout/footer.jspf" %>