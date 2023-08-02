<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고정비 목록</title>
<link href="/erp_ver2.0/css/bootstrap.css" rel="stylesheet" >
<script src="/erp_ver2.0/js/jquery.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row text-center">
			<div class="offset-sm-4 col-sm-4">
				<h2>고정비 목록</h2>
				<hr>
			</div>
		</div>
		
		<div class="row">
			<div class="offset-sm-2 col-sm-8">
				<table class="table table-border">
					<thead>
						<tr class="table-primary text-center">
							<th>번호</th>
							<th onclick="getList()">이름</th>
							<th>가격</th>
							<th>날짜</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="row text-center">
			<div class="offset-sm-5 col-sm-2">
				<button type="button" class="btn btn-info container-fluid" onclick="">추가</button>
			</div>
		</div>
	</div>
<script>
	

	function getList() {
		$.ajax({
			url: "fixed-cost?data=1",
			type: "GET",
			dataType: "text",
			success: function(data) {
				let jobj = JSON.parse(data);
				let tbl = "";
				$.each(jobj, function(i){
					tbl += "<tr>"
					tbl += "<td style='width:50px'><input type='text' class='form-control' name='name' value=" + jobj[i].fi_no + " readonly></td>"
					tbl += "<td><input type='text' class='form-control' name='name' value=" + jobj[i].fi_name + "></td>"
					tbl += "<td><div class='input-group mb-3'><input type='text' class='form-control text-end' name='cost' value=" + jobj[i].fi_cost + "><span class='input-group-text'>원</span></div></td>"
					tbl += "<td style='width:120px;'><div class='input-group mb-3'><input type='text' class='form-control text-end' name='date' value=" + jobj[i].fi_date + "><span class='input-group-text'>일</span></div></td>"
					tbl += "<td><div class='d-grid gap-2 d-md-block'><button type='button' class='btn btn-outline-primary modi-btn me-2' data-info='+'>수정</button>"
					tbl += 		"<button type='button' class='btn btn-outline-primary del-btn'>삭제</button></div></td>"
					tbl += "</tr>"
				});
				$('tbody').append(tbl);
			},
			error: function(error) {
				alert(error);
			}
		})
	}
	
	
</script>
<script src="/erp_ver2.0/js/bootstrap.bundle.js" ></script>
</body>
</html>