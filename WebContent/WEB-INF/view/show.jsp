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

<header class="bg-light-yellow p-3">
	<div class="container">
		<div class="row">
			<h1 class="h4 mb-0 col-12 col-md-7">
				<a href="index">ずぼらレシピ</a>
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
		<div class="recipe-left-box mx-auto">
			<div class="recipe-img">
				<img src="" alt="料理の写真">
			</div>
		</div>
		<div class="recipe-right-box">
			<div class="recipe-box">
				<div class="recipe-quantity-box">
					<form action="GET">
						<p class="">
							食材重量<br>
							<input type="number">
						</p>
						<p class="">
							食材重量<br>
							<input type="number">
						</p>
						<p class="">
							鍋･ボウル重量
							<input type="nuber">
						</p>
					</form>
				</div>
				<div class="recipe-result-box">
					<p class="h5">必要調味料</p>
					<p>シオ: 2.0g</p>
					<p>サトウ: 5.0g</p>
				</div>
			</div>
			<div class="recipe-list-box">
				<div class="foodstuff-approximation">
					<p>豚肉、人参、キャベツ、玉ねぎ</p>
				</div>
				<div class="recipe-approximation">
					<p>
						野菜を切る<br>
						豚肉を炒める<br>
						野菜を炒める<br>
						完成
					</p>
				</div>
			</div>
		</div>
		<p class="mt-3">
			<a href="index">トップページへ戻る</a>
		</p>
	</div>
</main>

<footer class="bg-light-yellow pt-3 pb-3">
	<div class="container">
		<p class="text-center mb-0">
			<a href="index">&copy;2020 All rights reserved.</a>
		</p>
	</div>
</footer>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>

</script>
</body>

</html>