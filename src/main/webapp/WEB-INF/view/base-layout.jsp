<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/shared/header.jsp" %>

<main>
    <jsp:include page="${viewPage}" />
</main>

<%@ include file="/WEB-INF/view/shared/footer.jsp" %>

<!-- Default CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=3" />

<!-- Default JS -->
<script src="${pageContext.request.contextPath}/js/script.js?v=1"></script>