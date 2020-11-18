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
			<div class="row">
				<div class="recipe-left-box mx-auto mb-5 col-lg-5">
					<p class="h2 font-weight-bold"><c:out value="${menu.name}" /></p>
					<div class="recipe-img">
						<img src="<%= request.getContextPath() %>/images/uploads/<c:out value="${menu.image}" />" alt="料理の写真" class="img-fluid center-block">
					</div>
				</div>
				<div class="recipe-right-box col-lg-7">
					<div class="recipe-box mb-5">
						<div class="recipe-quantity-box mb-5">
							<form action="GET">
								<p class="h4">調味料算出用の食材重量および調理器具重量</p>
								<c:forEach var="menu_food" items="${menu_foods}">
									<c:if test="${menu_food.foodId == 62}">
										<p class="text-primary mb-4 food-pre-weight">
											<span class="h5 font-weight-bold">下茹であり食材重量(パスタなど)</span><br>
											※パスタなど、下茹でが必要な食材がある場合は入力してください。<br>
											<input type="number" value="100" min="0" max="100000" oninput="validity.valid||(value='');"
												style="width: 100px" class="text-right ml-2" id="calc-food"><span class="text-dark"> グラム</span>
										</p>
									</c:if>
								</c:forEach>
								<p class="text-danger mb-4">
									<span class="h5 font-weight-bold food-weight">食材総重量</span><br>
									※使用する食材の合計重量を入力してください<br>
									<input type="number" value="100" min="0" max="100000" oninput="validity.valid||(value='');"
										style="width: 100px;" class="text-right ml-2" id="calc-food"><span class="text-dark"> グラム</span>
								</p>
								<p class="text-danger mb-4">
									<span class="h5 font-weight-bold">鍋･ボウル重量</span><br>
									※食材の重量計測時に使用した器具があればその重量を入力してください<br>
									<input type="number" value="0" min="0" max="100000" oninput="validity.valid||(value='');"
										style="width: 100px;" class="text-right ml-2" id="calc-items"><span class="text-dark"> グラム</span>
								</p>
								<p class="text-danger font-weight-bold h4" id="calc-message"></p>
							</form>
						</div>
						<div class="recipe-result-box">
							<p class="h4">必要調味料</p>
							<p class="font-weight-bold">
								※あくまで目安です。<br>
								※最初は調味料の8割を使用し、味が薄ければ追加するなどして目安としてご活用ください。
							</p>
							<c:forEach var="menu_food" items="${menu_foods}">
								<p class="text-center d-inline-block mb-2" style="width: 160px;"><c:out value="${menu_food.foodName}" /></p>
								<input type="number" value="${menu_food.quantity}" disabled class="registered-food-quantity text-right bg-light" style="width: 120px; font-size: 20px;"> グラム<br>
							</c:forEach>
						</div>
					</div>
					<div class="recipe-list-box">
						<div class="foodstuff-approximation">
							<p class="h4">目安の材料</p>
							<p>※食材の種類や分量はお好みで調整してください</p>
							<textarea class="col-10 ml-2 bg-light" id="foodstuff-text" disabled><c:out value="${menu.foodstuff}" /></textarea>
						</div>
						<div class="recipe-approximation">
							<p class="h4 mt-5">目安のレシピ</p>
							<p>※お好みで調整してください</p>
							<textarea class="col-10 ml-2 bg-light" id="recipe-text" disabled><c:out value="${menu.recipe}" /></textarea>
						</div>
					</div>
					<c:if test="${menu.userId == loginUserId}">
						<p class="mb-0 mt-4">
							<a href="edit?id=<c:out value="${menu.id}" />"><button type="button">編集する</button></a>
						</p>
					</c:if>
					<p class="mt-5">
						<a href="index">トップページへ戻る</a>
					</p>
				</div>
			</div>
		</div>
	</main>

	<c:import url="footer.jsp"/>

	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="js/form.js"></script>
	<script type="text/javascript" src="js/show.js"></script>
	<script type="text/javascript" src="js/search.js"></script>
</body>

</html>