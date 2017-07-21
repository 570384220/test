<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<title>查询商品列表</title>
	<script type="text/javascript">
		function deleteSel(){
			$("#itemForm").attr("action","${pageContext.request.contextPath }/deleteSel.do");
			$('#itemForm').submit();
		}
		function editSel(){
			$("#itemForm").attr("action","${pageContext.request.contextPath }/editSel.do");
			$('#itemForm').submit();
		}
	</script>
</head>
<body>
	<form id="itemForm" action="${pageContext.request.contextPath }/items/itemsList.do"
		method="post">
		条件：
		<table width="100%" border=1>
			<tr>
				<td><input type="submit" value="查询" /></td>
				<td><input type="button" value="批量删除" onclick="deleteSel()" /></td>
				<td><input type="button" value="批量修改" onclick="editSel()" /></td>
			</tr>
		</table>
		商品列表：
		<table width="100%" border=1>
			<tr>
				<td></td>
				<td>商品名称</td>
				<td>商品价格</td>
				<td>生产日期</td>
				<td>商品描述</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${itemsList }" var="items" varStatus="state">
				<tr>
					<td><input type="checkbox" name="id" value="${items.id}" />
						<input type="hidden" name="itemsList[${state.index}].id" value="${items.id }" />
					</td>
					<td><input type="text" name="itemsList[${state.index }].name" value="${items.name }" /></td>
					<td><input type="text" name="itemsList[${state.index }].price" value="${items.price }" /></td>
					<td><input type="text" name="itemsList[${state.index }].createtime"
					value="<fmt:formatDate value="${items.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
					</td>
					<td><input type="text" name="itemsList[${state.index }].detail" value="${items.detail }" /></td>

					<td><a
						href="${pageContext.request.contextPath }/itemsEdit.do?id=${items.id}">修改</a></td>

				</tr>
			</c:forEach>

		</table>
	</form>
</body>

</html>