<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="com.hexagon.mvcframework.dto.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
//ListController에서 request 객체에 저장해놓은 boardList를 꺼내먹자
	//DispatcherServlet 이 list.jsp를 포워딩 했기 때문에 즉 응답을 끊지 않고 list.jsp로 요청을 전달해놓았기 때문에
	//이 .jsp에서 사용될 request, reponse는 새롭게 생성된 객체등이 아니라 과거 /board/list 요청 떄 생성된 request, response를 그대로 전달받음
	List<BoardDTO> boardList = (List)request.getAttribute("boardList");
%>
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

</script>
</head>
<body>

	<div class="container">
		<h2>Hover Rows</h2>
		<p>The .table-hover class enables a hover state on table rows:</p>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>No</th>
					<th>제목</th>
					<th>작성자</th>
					<th>등록일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
			<%
			for(BoardDTO board : boardList){
			%>
				<tr>
					<td>1</td>
					<td><a href="/board/content?board_id=<%=board.getBoardId()%>"><%=board.getTitle() %></a></td>
					<td><%=board.getWriter() %></td>
					<td><%=board.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) %></td>
					<td><%=board.getHit() %></td>
				</tr>
				<%} %>
				<tr>
					<td colspan="5">
						<button onClick="location.href='/board/write.jsp';">글쓰기</button>
					</td>
				</tr>
			</tbody>
		</table>
	</div>

</body>
</html>
