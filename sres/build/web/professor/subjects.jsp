<%@include file="../layout/header.jspf" %>
<%@ page import="java.util.ArrayList, com.sres.model.Subject, com.sres.model.Competition" %>
<!-- start content -->
<div id="content">
    <div class="post">
        <h1 class="title"><a>Redes</a></h1>
        <p class="byline">&nbsp;</p>
        <div class="entry">
            <% if(current_user.newAnswers()) { %>
            <div style="text-align: center;">
                <h3>Hay respuestas de estudiantes, sin revisar! Vea las actividades para mas detalles!</h3>
            </div>
            <% } %>
            <table width="100%">
                <form action="select_students.jsp" method="post">
                    <tr>
                        <td><label for="name">Competencia</label></td>
                        <td colspan="3">
                            <jsp:include page="competitions_select.jsp"></jsp:include>
                        </td>
                        <td><input type="submit" name="submit" value="Agregar" onclick="if($('competition').value=='') return false;" /></td>
                    </tr>
                </form>
                <tr>
                    <th>Nombre</th>
                    <th>Fecha</th>
                    <th align="center" colspan="3">Accion</th>
                </tr>
                <%
            ArrayList<Subject> subjects = Subject.all();
            for (int i = 0; i < subjects.size(); i++) {
                Subject subject = subjects.get(i);
                %>
                <tr>
                    <td><%=Competition.find_by_id("" + subject.getCompetence_id()).getName()%></td>
                    <td><%=subject.getCreation_date()%></td>
                    <td>
                        <form action="activities.jsp" method="post">
                            <input type="hidden" name="id" value="<%=subject.getId()%>" />
                            <input type="submit" name="submit" value="Actividades" />
                        </form>
                    </td>
                    <td>
                        <form action="students.jsp" method="post">
                            <input type="hidden" name="id" value="<%=subject.getId()%>" />
                            <input type="submit" name="submit" value="Estudiantes" />
                        </form>
                    </td>
                    <td>
                        <form action="removeSubject" method="post">
                            <input type="hidden" name="id" value="<%=subject.getId()%>" />
                            <input type="submit" name="submit" value="Eliminar" />
                        </form>
                    </td>
                </tr>
                <%}%>
            </table>
        </div>
    </div>
</div>
<!-- end content -->
<%@include file="../layout/footer.jspf" %>