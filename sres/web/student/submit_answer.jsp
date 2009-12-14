<%@include file="../layout/header.jspf" %>
<%@ page import="java.util.ArrayList, com.sres.model.Subject, com.sres.model.Activity" %>
<jsp:scriptlet> String id = request.getParameter("id");</jsp:scriptlet>
<jsp:scriptlet> String ss_id = request.getParameter("student_id");</jsp:scriptlet>
<jsp:scriptlet> if(id == null) id = (String) request.getAttribute("id"); </jsp:scriptlet>
<jsp:scriptlet> Activity activity = Activity.find_by_id(id);</jsp:scriptlet>
<!-- start content -->
<div id="content">
    <div class="post">
        <h1 class="title"><a>Responder Actividad</a></h1>
        <p class="byline">&nbsp;</p>
        <div class="entry">
            <table width="100%" style="border: 2px solid #BCBCBC; padding:10px; margin-bottom:20px;">
                <tr>
                    <td>
                        <span><strong>Nombre </strong></span><br />
                        <span class="name"><%= activity.getName()%></span>
                    </td>
                </tr>
                <tr>
                    <td>
                        <span><strong>Descripcion </strong></span><br />
                        <span class="description"><%= activity.getDescription()%></span>
                    </td>
                </tr>
                <tr><td>&nbsp;</td></tr>
                <tr>
                    <td>
                        <% if (activity.getType() == 1) {%>
                        <script type='text/javascript' src='http://www.scribd.com/javascripts/view.js'></script>
                        <div id='embedded_flash'><a href="http://www.scribd.com"></a></div>
                        <script type="text/javascript">
                            var scribd_doc = scribd.Document.getDoc( <%= activity.getScrib_id()%>, '<%= activity.getScrib_key()%>' );
                            var oniPaperReady = function(e){
                                // scribd_doc.api.setPage(3);
                            }
                            scribd_doc.addParam( 'jsapi_version', 1 );
                            scribd_doc.addParam('height', 400);
                            //scribd_doc.addParam('width', 480);
                            scribd_doc.addParam('mode', 'slide');
                            scribd_doc.addParam('disable_related_docs', true);
                            scribd_doc.addEventListener( 'iPaperReady', oniPaperReady );
                            scribd_doc.write( 'embedded_flash' );
                        </script>
                        <% } else {%>
                        <a class="aaa" href="<%= activity.getLink()%>" rel="clearbox[width=700,height=490]">Ver Enlance</a>
                        <% }%>
                    </td>
                </tr>
            </table>
            <form action="addAnswer" method="post" enctype="multipart/form-data">
                <table width="100%">
                    <input type="hidden" name="activity_id" value="<%=activity.getId()%>" />
                    <input type="hidden" name="student_subject_id" value="<%=ss_id%>" />
                    <tr>
                        <td colspan="3">
                            <table width="100%">
                                <tr>
                                    <td valign="top"><label for="answer">Respuesta</label></td>
                                    <td><textarea name="answer" cols="40" rows="8"></textarea></td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <p>
                                            <span>Tipo de recurso:</span>
                                            <input id="type0" type="radio" name="type" value="0" />
                                            <label for="type0">Link</label>&nbsp;&nbsp;&nbsp;
                                            <input id="type1" type="radio" name="type" value="1" />
                                            <label for="type1">Adjunto</label>&nbsp;&nbsp;&nbsp;
                                        </p>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label for="link">Link</label></td>
                                    <td><input type="text" name="link" /></td>
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
                </table>
            </form>
        </div>
    </div>
</div>
<!-- end content -->
<%@include file="../layout/footer.jspf" %>