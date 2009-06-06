<jsp:root xmlns="http://www.w3.org/1999/xhtml"
          xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
          xmlns:html="http://struts.apache.org/tags-html"
          xmlns:c="http://java.sun.com/jsp/jstl/core"
          version="2.0">

<c:url var="redireccion" value="MostrarEstadoSala.do">
	<c:param name="idSesion" value="${sesion}"/>
</c:url>

<jsp:forward  page="${redireccion}"/>

</jsp:root>