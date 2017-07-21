<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<title>Insert title here</title>

	<script type="text/javascript">
		function kv(){
			$.ajax({
				type:"post",
				url:'${pageContext.request.contextPath }/requestKV.do',
				//输入时key value 形式 默认已经指定好了 contentType'application/json;charset=utf-8',
				data:'name=张三',
				success:function(data){
					alert(data.name);
				}
			});
		}
		function json(){
			$.ajax({
				type:"post",
				url:'${pageContext.request.contextPath }/requestJSON.do',
				//输入是json是 ，需要指定contentType为application/json
				contentType:'application/json;charset=utf-8',
				data:'{"name":"张三1","id":9}',
				success:function(data){
					alert(data.name);
				}
			});
		}
	</script>
</head>
<body>
	<input type="button" value="kv" onclick="kv()">
	<input type="button" value="json" onclick="json()" />
</body>
</html>