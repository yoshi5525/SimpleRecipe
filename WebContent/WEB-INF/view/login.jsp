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
		<div class="container bg-light-skyblue p-5">
			<div class="form w-50 mx-auto">
				<form action="" method="post">
					<c:if test="${!empty error}">
						<p class="text-center h5 text-danger font-weight-bold mb-4"><c:out value="${error}" /></p>
					</c:if>
					<p>
						ユーザーID<br>
						<input type="text" name="login_id" size="40">
					</p>
					<p>
						パスワード<br>
						<input type="password" name="login_pass" size="40">
					</p>
					<p class="mb-0">
						<input type="submit" value="ログインする" name="login"><br>
					</p>
				</form>
				<c:if test="${loginStatus != null}">
					<form action="logout" method="post"class="mt-3">
						<input type="submit" value="ログアウトする" name="logout">
					</form>
					<p class="mt-3">
						<a href="new"><button style="border-radius: 5px;">レシピの新規投稿</button></a>
					</p>
				</c:if>
				<p class="mt-5">
					<a href="index">トップページへ戻る</a>
				</p>
			</div>
		</div>
	</main>

	<c:import url="footer.jsp"/>

	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="js/form.js"></script>
	<script type="text/javascript" src="js/search.js"></script>
</body>

</html>