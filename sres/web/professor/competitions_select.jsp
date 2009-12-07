<%@ page import="java.util.ArrayList, com.sres.model.Subject, com.sres.model.Competition" %>
<select id="competition" name="competition" style="width:100%;">
    <option value="">Seleccione un elemento</option>
    <%
    ArrayList<Competition> competitions = Competition.all();
    for (int i = 0; i < competitions.size(); i++) {
        Competition competition = competitions.get(i);
    %>
    <option value="<%=competition.getId()%>"><%=competition.getName()%></option>
    <%}%>
</select>