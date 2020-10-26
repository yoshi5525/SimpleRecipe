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
	<header class="bg-light-yellow p-3">
		<div class="container">
			<div class="row">
				<h1 class="h4 mb-0 col-12 col-md-7">
					<a href="index.html">ずぼらレシピ</a>
				</h1>
				<div class="search-box col-12 col-md-5">
					<input type="text" placeholder="料理名を入力">
					<button class="search-btn">検索</button>
				</div>
			</div>
		</div>
	</header>

	<main>
		<div class="container bg-light-skyblue p-5">
			<div class="form w-50 mx-auto">
				<form action="POST">
					<p>
						ユーザーID<br>
						<input type="text" name="name" size="40">
					</p>
					<p>
						パスワード<br>
						<input type="text" name="name" size="40">
					</p>
					<p class="mb-0">
						<input type="submit" value="ログインする">
					</p>
				</form>
				<p class="mt-3">
					<a href="index.html">トップページへ戻る</a>
				</p>
			</div>
		</div>
	</main>

	<footer class="bg-light-yellow pt-3 pb-3">
		<div class="container">
			<p class="text-center mb-0">
				<a href="index.html">&copy;2020 All rights reserved.</a>
			</p>
		</div>
	</footer>
</body>

</html>