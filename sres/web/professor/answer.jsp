<%@include file="../layout/header.jspf" %>
<%@ page import="java.util.ArrayList, com.sres.model.Subject, com.sres.model.Rates" %>
<jsp:scriptlet> String id = request.getParameter("id");</jsp:scriptlet>
<jsp:scriptlet> Rates rate = Rates.find_by_id(id);</jsp:scriptlet>
<!-- start content -->
<div id="content">
    <div class="post">
        <h1 class="title"><a>Respuesta</a></h1>
        <p class="byline">&nbsp;</p>
        <div class="entry">
            <form action="addRate" method="post">
                <table width="100%">
                    <input type="hidden" name="id" value="<%=rate.getId()%>" />
                    <tr>
                        <td colspan="3">
                            <table width="100%">
                                <tr>
                                    <td><label for="link">Nota</label></td>
                                    <td><input type="text" name="cuantification" value="<%=(rate.getCuantification()>0 ? rate.getCuantification() : "")%>" /></td>
                                </tr>
                                <tr>
                                    <td valign="top"><label for="answer">Nota Cualitativa</label></td>
                                    <td><textarea name="qualification" cols="40" rows="8"><%=(rate.getQualification()!=null ? rate.getQualification() : "")%></textarea></td>
                                </tr>
                                <tr>
                                    <td colspan="2" align="center"><input type="submit" name="submit" value="Guardar calificacion" /></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </form>
            <table width="100%" style="border: 2px solid #BCBCBC; padding:10px; margin-bottom:20px;">
                <tr>
                    <td>
                        <span><strong>Respuesta </strong></span><br />
                        <span class="name"><%= rate.getAnswer()%></span>
                    </td>
                </tr>
                <tr><td>&nbsp;</td></tr>
                <tr>
                    <td>
                        <% if (rate.getType() == 1) {%>
                        <script type='text/javascript' src='http://www.scribd.com/javascripts/view.js'></script>
                        <div id='embedded_flash'><a href="http://www.scribd.com"></a></div>
                        <script type="text/javascript">
                            var scribd_doc = scribd.Document.getDoc( <%= rate.getScribd_id()%>, '<%= rate.getScribd_key()%>' );
                            var oniPaperReady = function(e){
                                // scribd_doc.api.setPage(3);
                            }
                            scribd_doc.addParam( 'jsapi_version', 1 );
                            scribd_doc.addParam('height', 200);
                            //scribd_doc.addParam('width', 480);
                            scribd_doc.addParam('mode', 'slide');
                            scribd_doc.addParam('disable_related_docs', true);
                            scribd_doc.addEventListener( 'iPaperReady', oniPaperReady );
                            scribd_doc.write( 'embedded_flash' );
                        </script>
                        <% } else {%>
                        <a class="aaa" href="<%= rate.getLink()%>" rel="clearbox[width=700,height=490]">Ver Enlance</a>
                        <% }%>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
<!-- end content -->
<%@include file="../layout/footer.jspf" %>