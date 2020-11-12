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
                <h1 class="h2 mb-0 col-12 col-md-5"><a href="index">ずぼらレシピ</a></h1>
                <div class="search-box col-12 col-md-6 position-relative">
                	<form action="search" method="get" class="form-inline mr-2" id="search-form">
	                    <input type="text" name="search-name" autocomplete="off" placeholder="料理名を入力" class="form-control mr-2 w-75" id="search-box">
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
		<div class="container bg-light-skyblue pt-4 pb-4">
			<div class="form mx-auto w-75">
				<c:if test="${!empty errorMsg}"><p class="text-center h2 text-danger font-weight-bold mb-4"><c:out value="${errorMsg}" /></p></c:if>
				<form action="" method="post" enctype="multipart/form-data">
					<p class="mb-0">
						写真<br>
						<div class="preview-img mb-2">
							<img src="<c:if test="${!empty image}"><c:out value="${image}" /></c:if>" width="400px">
						</div>
						<input type="file" name="image" id="menu-img">
					</p>
					<p>
						料理名<br>
						<input type="text" name="name" size="48"<c:if test="${!empty name}"> value="<c:out value="${name}" />"</c:if>>
						<c:if test="${!empty errorName}"><p class="h5 text-danger font-weight-bold mb-4"><c:out value="${errorName}" /></p></c:if>
					</p>
					<p>
						料理名(ふりがな)<br>
						<input type="text" name="kana" size="48"<c:if test="${!empty kana}"> value="<c:out value="${kana}" />"</c:if>>
						<c:if test="${!empty errorKana}"><p class="h5 text-danger font-weight-bold mb-4"><c:out value="${errorKana}" /></p></c:if>
					</p>
					<p>
						料理区分<br>
						<select name="tag_id">
							<optgroup label="和食">
								<c:forEach var="tag" items="${tags}">
									<c:choose>
										<c:when test="${tag.id <= 10}">
											<option value="<c:out value="${tag.id}" />" <c:if test="${tag.id == tag_id}">selected</c:if>><c:out value="${tag.name}" /></option>
										</c:when>
									</c:choose>
								</c:forEach>
							</optgroup>
							<optgroup label="中華">
								<c:forEach var="tag" items="${tags}">
									<c:choose>
										<c:when test="${tag.id > 10 && tag.id <= 20}">
											<option value="<c:out value="${tag.id}" />" <c:if test="${tag.id == tag_id}">selected</c:if>><c:out value="${tag.name}" /></option>
										</c:when>
									</c:choose>
								</c:forEach>
							</optgroup>
							<optgroup label="洋食">
								<c:forEach var="tag" items="${tags}">
									<c:choose>
										<c:when test="${tag.id > 20 && tag.id <= 30}">
											<option value="<c:out value="${tag.id}" />" <c:if test="${tag.id == tag_id}">selected</c:if>><c:out value="${tag.name}" /></option>
										</c:when>
									</c:choose>
								</c:forEach>
							</optgroup>
						</select>
						<c:if test="${!empty errorTagId}"><p class="h5 text-danger font-weight-bold mb-4"><c:out value="${errorTagId}" /></p></c:if>
					</p>
					<p>
						<div class="d-inline-block w-25">調味料</div>
						<div class="d-inline-block w-50">調味料分量</div>
						<div class="menu-food mb-2">
							<select name="food_id" class="select-foods w-25">
								<c:forEach var="food" items="${foods}">
									<option value="<c:out value="${food.id}" />"><c:out value="${food.name}" /></option>
								</c:forEach>
							</select>
							<input type="number" name="food_quantity" class="select-numbers text-right" value="0" size="20" min="0"
								oninput="validity.valid||(value='');">
							<input type="button" value="+" class="add">
							<input type="button" value="－" class="del">
						</div>
						<c:if test="${!empty errorQuantity}"><p class="h5 text-danger font-weight-bold mb-4"><c:out value="${errorQuantity}" /></p></c:if>
					</p>
					<p>
						<input type="hidden" name="menu-food-length" value="1" id="menu-food-length">
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
						<input type="submit" value="登録する" id="register">
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
	<script type="text/javascript" src="js/search.js"></script>
</body>

</html>