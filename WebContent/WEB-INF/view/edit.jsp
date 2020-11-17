<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ずぼらレシピアプリ</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
</head>

<body>
    <c:import url="header.jsp" />

	<main>
		<c:import url="form.jsp">
			<c:param name="url" value="${url}" />
			<c:param name="foods" value="${foods}" />
			<c:param name="tags" value="${tags}" />

			<c:param name="menuFoodLength" value="${menuFoodLength}" />
			<c:param name="image" value="${image}" />
			<c:param name="name" value="${name}" />
			<c:param name="kana" value="${kana}" />
			<c:param name="foodstuff" value="${foodstuff}" />
			<c:param name="recipe" value="${recipe}" />
			<c:param name="tag_id" value="${tag_id}" />
			<c:param name="menu_id" value="${menu_id}" />
			<c:param name="foodIds" value="${foodIds}" />
			<c:param name="quantities" value="${quantities}" />
			<c:param name="menuFoodIds" value="${menuFoodIds}" />
			<c:param name="errors" value="${errors}" />
		</c:import>
	</main>

	<c:import url="footer.jsp"/>

	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="js/form.js"></script>
	<script type="text/javascript" src="js/search.js"></script>
</body>

</html>