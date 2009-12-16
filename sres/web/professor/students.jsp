<%@include file="../layout/header.jspf" %>
<%@ page import="java.util.ArrayList, com.sres.model.Subject, com.sres.model.User" %>
<jsp:scriptlet> String id = request.getParameter("id");</jsp:scriptlet>
<jsp:scriptlet> if(id==null) id = (String)request.getAttribute("id");</jsp:scriptlet>
<jsp:scriptlet> Subject subject = Subject.find_by_id(id);</jsp:scriptlet>
<!-- start content -->
<div id="content">
    <div class="post">
        <h1 class="title"><a>Listado de Estudiantes: <%= subject.getCompetenceName()%></a></h1>
        <p class="byline">&nbsp;</p>
        <div class="entry">
            <table width="100%">
                <tr>
                    <th>Nombre</th>
                    <th>Notas</th>
                    <th>Promedio</th>
                </tr>
                <%
            ArrayList<User> students = subject.getStudents();
            for (int i = 0; i < students.size(); i++) {
                User student = students.get(i);
                %>
                <tr>
                    <td><%=student.getName()%></td>
                    <td><%=student.getGrades(subject.getId()+"")%></td>
                    <td><%=student.getAvgGrade(subject.getId()+"")%></td>
                </tr>
                <%}%>
            </table>
        </div>
    </div>
</div>
<!-- end content -->
<%@include file="../layout/footer.jspf" %>