<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
function regist(){
	//데이터량이 많기 때문에  body에 실어서 보내야 하므로  POST 로 전송 
	$("#form1").attr({
		action:"/board/regist",
		method:"POST"
	});
	
	$("#form1").submit();	
}

$(function(){
	
	$("#bt_regist").click(()=>{
		regist();	
	});
});

</script>	
</head>
<body>

	<div class="container">
		<h2>Vertical (basic) form</h2>
		<form id="form1">
		
			<div class="form-group">
				<input type="text" class="form-control"  placeholder="Enter title" name="subject">
			</div>
			
			<div class="form-group">
				<input type="text" class="form-control"  placeholder="Enter writer" name="writer">
			</div>
			
			<div class="form-group">
				<textarea class="form-control"  placeholder="Enter writer" name="content"></textarea>
			</div>

			<button type="button" class="btn btn-default" id="bt_regist">Submit</button>
		</form>
	</div>

</body>
</html>