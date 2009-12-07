<%@include file="../layout/header.jspf" %>
<%@ page import="java.util.ArrayList, com.sres.model.Subject, com.sres.model.Activity" %>
<jsp:scriptlet> String id = request.getParameter("id");</jsp:scriptlet>
<jsp:scriptlet> Subject subject = Subject.find_by_id(id);</jsp:scriptlet>
<!-- start content -->
<div id="content">
    <div class="post">
        <h1 class="title"><a>Listado de Actividades: <%= subject.getCompetenceName()%></a></h1>
        <p class="byline">&nbsp;</p>
        <div class="entry">
            <table width="100%">
                <form action="select_students.jsp" method="post" enctype="multipart/form-data">
                    <tr>
                        <td colspan="3">
                            <table width="100%">
                                <tr>
                                    <td><label for="name">Nombre </label></td>
                                    <td><input type="text" name="name" /></td>
                                </tr>
                                <tr>
                                    <td valign="top"><label for="description">Descripcion</label></td>
                                    <td><textarea name="description" cols="16" rows="2"></textarea></td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <p>
                                            <span>Tipo de recurso:</span>
                                            <input id="type0" type="radio" name="type" value="0" />
                                            <label for="type0">Link</label>&nbsp;&nbsp;&nbsp;
                                            <input id="type1" type="radio" name="type" value="1" />
                                            <label for="type1">Video</label>&nbsp;&nbsp;&nbsp;
                                            <input id="type2" type="radio" name="type" value="2" />
                                            <label for="type2">Documento</label>
                                        </p>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label for="link">Link</label></td>
                                    <td><input type="text" name="link" /></td>
                                </tr>
                                <tr>
                                    <td><label for="video">Video</label></td>
                                    <td><input type="file" name="video" /></td>
                                </tr>
                                <tr>
                                    <td><label for="document">Documento</label></td>
                                    <td><input type="file" name="document" /></td>
                                </tr>
                                <tr>
                                    <td colspan="2" align="center"><input type="submit" name="submit" value="Agregar" /></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </form>
                <tr>
                    <th>Nombre</th>
                    <th>Descripcion</th>
                    <th>Accion</th>
                </tr>
                <%
            ArrayList<Activity> activities = subject.getActivities();
            for (int i = 0; i < activities.size(); i++) {
                Activity activity = activities.get(i);
                %>
                <tr>
                    <td><%=activity.getName()%></td>
                    <td><%=activity.getDescription()%></td>
                    <td>
                        <form action="activity.jsp" method="post">
                            <input type="hidden" name="id" value="<%=activity.getId()%>" />
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