<%@include file="../layout/header.jspf" %>
<%@ page import="java.util.ArrayList, com.sres.model.Competition" %>
<!-- start content -->
<div id="content">
    <div class="post">
        <h1 class="title"><a>Competencias existentes</a></h1>
        <p class="byline">&nbsp;</p>
        <div class="entry">
            <table width="100%">
                <tr>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Accion</th>
                </tr>
                <form action="addCompetition" method="post">
                    <tr>
                        <td><label for="name">Nombre</label></td>
                        <td><input type="text" name="name" /></td>
                        <td><input type="submit" name="submit" value="Agregar" /></td>
                    </tr>
                </form>
                <%
                    ArrayList<Competition> competitions = Competition.all();
                    for(int i=0; i<competitions.size(); i++) {
                        Competition competition = competitions.get(i);
                %>
                <tr>
                    <td><%=competition.getId()%></td>
                    <td><%=competition.getName()%></td>
                    <td>
                        <form action="removeCompetition" method="post">
                            <input type="hidden" name="id" value="<%=competition.getId()%>" />
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