<%@include file="../layout/header.jspf" %>
<%@ page import="java.util.ArrayList, com.sres.model.Subject, com.sres.model.Activity" %>
<jsp:scriptlet> String id = request.getParameter("id");</jsp:scriptlet>
<jsp:scriptlet> Subject subject = Subject.find_by_id(id);</jsp:scriptlet>
<!-- start content -->
<div id="content">
    <div class="post">
        <h1 class="title"><a>Creacion de Boletines:</a></h1>
        <p class="byline">&nbsp;</p>
        <div class="entry">
            <form action="AddBulletin" method="post">
                <p><jsp:include page="competitions_select.jsp"></jsp:include></p>
                <input id="sent_on" type="text"  size="30" readonly="readonly" name="sent_on"/>
                <img class="calendar_date_select_popup_icon" style="border: 0px none ; cursor: pointer;" src="/sres/stylesheets/images/calendar_date_select/calendar.gif" onclick="new CalendarDateSelect( $(this).previous(), {popup:'force', time:false, year_range:10} );" alt="Calendar"/>
                <p><label>Titulo</label><input type="text" name="class"></p>
                <p><label>Cuerpo</label><textarea cols="30" name="body"></textarea></p>
                <p><input type="submit" value="Aceptar"></p>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    tinyMCE.init({
        mode : "textareas",
        theme : "advanced",
        theme_advanced_buttons1 : "mymenubutton,bold,italic,underline,separator,strikethrough, bullist,numlist,undo,redo",
        theme_advanced_buttons2 : "",
        theme_advanced_buttons3 : "",
        theme_advanced_toolbar_location : "top"
    });
</script>
<!-- end content -->
<%@include file="../layout/footer.jspf" %>