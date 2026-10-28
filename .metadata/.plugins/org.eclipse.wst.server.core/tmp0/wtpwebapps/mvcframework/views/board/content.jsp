<%@page import="com.hexagon.mvc.dto.Board"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
//ContentController가 여기서 꺼내먹을 수 있도록 request에 board 이라는 이름으로 DTO를 저장해 놓았으므로
//꺼내서 화면세출력하자!
BoardDTO board = (BoardDTO)request.getAttribute("board");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function edit(){
			//데이터량이 많아서 body에 실어서 보내야하므로 POST 로 전송
			$("#form1").attr({
				action:"/board/update",
				method:"POST"
			});
			
			$("#form1").submit();
			
		}
		
	$(function(){
		
		$("#bt_update").click(()=>{
			edit();	
		});
	});
	
	</script>
</head>
<body>

	<div class="container">
		<h2>상세보기</h2>
		<form id="form1">
			<input type="hidden" name="board_id" vlaue="<%=board.getBoardId()%>">
			
			<div class="form-group">
				<input type="text" class="form-control" id="email" value="<%=board.getTitle() %>" name="title">
			</div>

			<div class="form-group">
				<input type="text" class="form-control" id="email" value="<%=board.getWriter() %>" name="writer">
			</div>
			
			<div class="form-group">
				<textarea  class="form-control" id="pwd" name="content"><%=board.getContent() %></textarea>
			</div>

			<button type="button" class="btn btn-default" id="bt_update">수정</button>
			<button type="button" class="btn btn-default" id="bt_delete">삭제</button>
			<button type="button" class="btn btn-default" id="bt_list">목록</button>
		</form>
	</div>

</body>
</html>
