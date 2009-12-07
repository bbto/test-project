<%@include file="../layout/header.jspf" %>
<%@ page import="java.util.ArrayList, com.sres.model.User" %>
<!-- start content -->
<div id="content">
    <div class="post">
        <h1 class="title"><a>Competencias existentes</a></h1>
        <p class="byline">&nbsp;</p>
        <div class="entry">
            <table width="100%">
                
                <form action="addProfessor" method="post">
                    <tr>
                        <td><label for="firstname">Nombre</label></td>
                        <td><input type="text" name="firstname" /></td>
                        
                    </tr>
                    <tr>
                        <td><label for="lastname">Apellido</label></td>
                        <td><input type="text" name="lastname" /></td>

                    </tr>
                    <tr>
                        <td><label for="email">Email</label></td>
                        <td><input type="text" name="email" /></td>
                        
                    </tr>
                    <tr>
                        <td><label for="password">Password</label></td>
                        <td><input type="password" name="password" /></td>
                        <td colspan="2"><input type="submit" name="submit" value="Agregar" /></td>
                    </tr>
                    <tr>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Email</th>
                    <th>Accion</th>
                </tr>
                </form>
                <%
                    ArrayList<User> users = User.all_professors();
                    for(int i=0; i<users.size(); i++) {
                        User user = users.get(i);
                %>
                <tr>
                    <td><%=user.getId()%></td>
                    <td><%=user.getEmail()%></td>
                    <td><%=user.getFirstname()+" "+user.getLastname()%></td>
                    <td>
                        <form action="removeUser" method="post">
                            <input type="hidden" name="id" value="<%=user.getId()%>" />
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