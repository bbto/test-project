<%@include file="../layout/header.jspf" %>
<%@ page import="java.util.ArrayList, com.sres.model.Rates, com.sres.model.Activity" %>
<jsp:scriptlet> String id = request.getParameter("id");</jsp:scriptlet>
<jsp:scriptlet> if(id==null) id = (String)request.getAttribute("id");</jsp:scriptlet>
<jsp:scriptlet> Activity activity = Activity.find_by_id(id);</jsp:scriptlet>
<!-- start content -->
<div id="content">
    <div class="post">
        <h1 class="title"><a>Listado de Respuestas: <%= activity.getName()%></a></h1>
        <p class="byline">&nbsp;</p>
        <div class="entry">
            <table width="100%">
                <tr>
                    <th>Estudiante</th>
                    <th align="center" width="1%">Accion</th>
                </tr>
                <%
            ArrayList<Rates> rates = activity.getRates();
            for (int i = 0; i < rates.size(); i++) {
                Rates rate = rates.get(i);
                User student = User.find_by_student_subject_id(""+rate.getStudent_subject_id());
                %>
                <tr>
                    <td><%=student.getFirstname() + " " + student.getLastname()%></td>
                    <td>
                        <form action="answer.jsp" method="post">
                            <input type="hidden" name="id" value="<%=rate.getId()%>" />
                            <input type="submit" name="submit" value="Ver" />
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