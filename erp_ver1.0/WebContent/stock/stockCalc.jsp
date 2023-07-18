<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Stock Calculator</title>
<link href="css/bootstrap.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="js/jquery-3.7.0.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row text-center mb-5">
			<h2>제품 생산 계산</h2>
		</div>
		<div class="row justify-content-center">
			<div class="col-sm-4 border rounded pt-3">
				<table class="table">
					<thead>
						<tr class="table-info">
							<th><input class="form-check-input" type="checkbox"></th>
							<th>상품명</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="product" items="${rlist }">
							<tr>
								<td><input class="form-check-input" type="checkbox" name="product" value="${product.pr_code }"></td>
								<td>${product.pr_code }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="row justify-content-center mt-5">
			<div class="col-sm-1">
				<button type="button" class="btn btn-primary" onclick="f()">계산</button>
			</div>
		</div>
		<div class="row">
			<div class="col" id="txt">
			</div>
		</div>
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<script>
	function f() {
			let arr = [];
			let pro = $('input[name="product"]:checked');
			$(pro).each(function() {
				arr.push($(this).val());
			});
			
			$.ajax({
				url : "stockCalc",
				type : "post",
				data: {
					product : arr
				},
				dataType : "text",
				success : function(data) {
					$('#txt').html(data);
				}
			})
	}
</script>
</body>
</html>