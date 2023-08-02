<%@page import="util.PageCheck"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<!-- CSS only -->
<link href="/erp_ver2.0/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/erp_ver2.0/css/erp.css">
<script src="/erp_ver2.0/js/jquery.js"></script>
<style>
	* {
		box-sizing: border-box;
	}
	
	#page {
        width: 1600px; height: 900px;
        margin: 20px auto;
        display: grid;
        grid-gap: 10px;
        grid-template-columns: 266px 1fr;
        grid-template-rows: 100px 50px 1fr;
        grid-template-areas: 
            "header header"
            "nav nav"
            "aside section"
        ;
	}
	
	header {
		grid-area : header;
	}
	
	aside {
		grid-area : aside;
	}
	
	nav {
		grid-area : nav;
	}
	
	section {
		grid-area : section;
	}
</style>
</head>
<body>
<div id = "page">
	<jsp:include page="../header.jsp" />
	<jsp:include page="nav.jsp" />
	<jsp:include page="aside.jsp" />
	<section class="container-fluid border shadow p-3  bg-body rounded">
		<jsp:include page="saleAnalysis.jsp" />
	</section>
</div>
<!-- JavaScript Bundle with Popper -->

<script src="/erp_ver2.0/js/bootstrap.bundle.js"></script>
</body>
</html>