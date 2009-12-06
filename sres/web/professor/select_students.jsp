<%@include file="../layout/header.jspf" %>
<%@ page import="java.util.ArrayList, com.sres.model.User, com.sres.model.Competition" %>
<jsp:scriptlet> String id = request.getParameter("competition");</jsp:scriptlet>
<jsp:scriptlet> Competition competition = Competition.find_by_id(id);</jsp:scriptlet>
<!-- start content -->
<div id="content">
    <div class="post">
        <h1 class="title"><a>Creando nueva red: <%=competition.getName()%></a></h1>
        <p class="byline">&nbsp;</p>
        <div class="entry">
            <form action="addSubject" method="post">
                <input type="hidden" name="competition" value="<%=competition.getName()%>" />
                <ul class="checks">
                    <%
                    ArrayList<User> students = User.all_students();
                    for (int i = 0; i < students.size(); i++) {
                        User student = students.get(i);
                    %>
                        <li>
                            <input id="student_<%=student.getId()%>" type="checkbox" name="students" />
                            <label for="student_<%=student.getId()%>"><%=(student.getFirstname() + " " + student.getLastname())%></label>
                        </li>
                    <%}%>
                </ul>
                <p>
                    <input type="submit" name="submit" value="Guardar" />
                    o <a href="subjects.jsp">Cancelar</a>
                </p>
            </form>
        </div>
    </div>
</div>
<!-- end content -->
<%@include file="../layout/footer.jspf" %>