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
    for (int i = 0; i < ss.size(); i++) {
        StudentSubject stud = ss.get(i);
        Subject subject = stud.getSubject();
                %>
                <li>
                    <%=stud.getSubjectName()%>
                    <ol>
                        <% ArrayList<Activity> activities = subject.getActivities();
                            for (Activity act : activities) {
                        %>
                        <li>
                            <%=act.getName()%>
                            <% if(!current_user.submitted_activity_answer(act.getId()+"")) { %>
                            <form action="submit_answer.jsp" method="post">
                                <input type="hidden" name="id" value="<%=act.getId()%>" />
                                <input type="hidden" name="student_id" value="<%=current_user.getStudentSubjectId(act.getId()+"")%>" />
                                <input type="submit" value="Enviar respuesta" />
                            </form>
                            <% } else { %>
                            <p><i>Respuesta enviada!</i></p>
                            <% } %>
                        </li>

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