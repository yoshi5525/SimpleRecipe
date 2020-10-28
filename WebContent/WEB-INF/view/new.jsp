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
		<div class="container bg-light-skyblue pt-4 pb-4">
			<div class="form mx-auto w-50">
				<form action="" method="post">
					<p class="mb-0">
						写真<br>
						<div class="preview-img mb-2">
							<img src="" alt="">
						</div>
						<input type="file" name="image" id="menu-img">
					</p>
					<p>
						料理名<br>
						<input type="text" name="name" size="40">
					</p>
					<p>
						料理区分<br>
						<select name="teg_name">
							<optgroup label="和食">
								<option value="1">主菜(和)</option>
								<option value="2">副菜(和)</option>
								<option value="3">その他(和)</option>
							</optgroup>
							<optgroup label="中華">
								<option value="11">主菜(中)</option>
								<option value="12">副菜(中)</option>
								<option value="13">その他(中)</option>
							</optgroup>
							<optgroup label="洋食">
								<option value="21">主菜(洋)</option>
								<option value="22">副菜(洋)</option>
								<option value="23">その他(洋)</option>
							</optgroup>
						</select>
					</p>
					<p>
						<div class="d-inline-block w-25">調味料</div>
						<div class="d-inline-block w-50">調味料分量</div>
						<div class="menu-food mb-2">
							<select name="food" class="w-25">
								<option value="シオ">シオ</option>
								<option value="サトウ">サトウ</option>
								<option value="ショウユ">ショウユ</option>
							</select>
							<input type="number" name="quantity" size="20">
							<input type="button" value="+" class="add"> <input type="button" value="－" class="del">
						</div>
					</p>
					<p>
						食材<br>
						<textarea name="foodstuff" cols="50" rows="5"></textarea>
					</p>
					<p>
						レシピ<br>
						<textarea name="recipe" cols="50" rows="8"></textarea>
					</p>
					<p class="mb-0">
						<input type="submit" value="登録する">
					</p>
				</form>
				<p class="mt-3">
					<a href="index">トップページへ戻る</a>
				</p>
			</div>
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
	<script type="text/javascript" src="js/form.js"></script>
</body>

</html>