<%@include file="../layout/header.jspf" %>
<%@ page import="java.util.ArrayList, com.sres.model.Subject, com.sres.model.Activity" %>
<jsp:scriptlet> String id = request.getParameter("id");</jsp:scriptlet>
<jsp:scriptlet> Activity activity = Activity.find_by_id(id);</jsp:scriptlet>
<!-- start content -->
<div id="content">
    <div class="post">
        <h1 class="title"><a>Actividad</a></h1>
        <p class="byline">&nbsp;</p>
        <div class="entry">
            <table width="100%">
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
                            scribd_doc.addEventListener( 'iPaperReady', oniPaperReady );
                            scribd_doc.write( 'embedded_flash' );
                        </script>
                        <% } else {%>
                        <a class="aaa" href="http://www.youtube.com/v/2Zps6dqnu5E" rel="clearbox[width=700,height=490]">Ver Enlance</a>
                        <% }%>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
<!-- end content -->
<%@include file="../layout/footer.jspf" %>