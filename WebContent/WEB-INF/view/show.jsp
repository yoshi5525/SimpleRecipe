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
                <h1 class="h2 mb-0 col-12 col-md-5"><a href="index">ずぼらレシピ</a></h1>
                <div class="search-box col-12 col-md-6 position-relative">
                	<form action="search" method="get" class="form-inline mr-2" id="search-form">
	                    <input type="text" name="search-name" placeholder="料理名を入力" class="form-control mr-2 w-75" id="search-box">
	                    <button id="btn search-btn" class="search-btn btn btn-outline-info my-2">検索</button>
	                </form>
	                <div class="result-box position-absolute fixed-bottom">
	                	<ul class="result-list bg-light">
	                	</ul>
	                </div>
                </div>
            </div>
        </div>
    </header>

	<main>
		<div class="container bg-light-skyblue p-5">
			<div class="recipe-left-box mx-auto mb-5">
				<p class="h2 font-weight-bold"><c:out value="${menu.name}" /></p>
				<div class="recipe-img">
					<img src="<%= request.getContextPath() %>/images/<c:out value="${menu.image}" />" alt="料理の写真" width="400px">
				</div>
			</div>
			<div class="recipe-right-box">
				<div class="recipe-box mb-5">
					<div class="recipe-quantity-box mb-5">
						<form action="GET">
							<p class="h4">調味料算出用の食材重量および調理器具重量</p>
							<p class="">
								食材総重量 (食材の総重量を入力してください)<br>
								<input type="number" value="100" min="0" max="100000" oninput="validity.valid||(value='');" style="width: 100px" class="text-right" id="calc-food"> グラム
							</p>
							<p class="">
								鍋･ボウル重量 (計量した器具の重量を入力してください)<br>
								<input type="number" value="0" min="0" max="100000" oninput="validity.valid||(value='');" style="width: 100px" class="text-right" id="calc-items"> グラム
							</p>
							<p class="text-danger font-weight-bold h4" id="calc-message"></p>
						</form>
					</div>
					<div class="recipe-result-box">
						<p class="h4">必要調味料</p>
						<c:forEach var="menu_food" items="${menu_foods}">
							<p class="text-center d-inline-block mb-2" style="width: 160px"><c:out value="${menu_food.foodName}" /></p>
							<input type="number" value="${menu_food.quantity}" disabled style="width: 120px" class="registered-food-quantity text-right bg-light"> グラム<br>
						</c:forEach>
					</div>
				</div>
				<div class="recipe-list-box">
					<div class="foodstuff-approximation">
						<p class="h4">目安の材料(お好みで調整してください)</p>
						<p><c:out value="${menu.foodstuff}" /></p>
					</div>
					<div class="recipe-approximation">
						<p class="h4">目安のレシピ(お好みで調整してください)</p>
						<p><c:out value="${menu.recipe}" />	</p>
					</div>
				</div>
				<c:if test="${menu.userId == loginUserId}">
					<p class="mb-0">
						<a href="edit?id=<c:out value="${menu.id}" />"><button type="button">編集する</button></a>
					</p>
				</c:if>
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
	<script type="text/javascript" src="js/calc.js"></script>
	<script type="text/javascript" src="js/search.js"></script>
</body>

</html>