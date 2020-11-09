<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                <div class="search-box col-12 col-md-6">
                	<form action="search" method="get" class="form-inline mr-2">
	                    <input type="text" name="search" placeholder="料理名を入力" class="form-control mr-2 w-75">
	                    <button type="submit" class="search-btn btn btn-outline-info my-2">検索</button>
	                </form>
                </div>
            </div>
        </div>
    </header>

    <main>
        <div class="container bg-light-pink p-2">
            <div class="row pt-4">
                <div class="main-img-box col-sm-3 mb-4 ml-3">
                    <p class="h4 font-weight-bold">和食</p>
                    <div class="main-tag-img center-block">
                        <img src="images/japanese_food.jpg" alt="和食の画像" class="img-fluid center-block">
                    </div>
                </div>
                <div class="main-menu-box col-sm-7 ml-5">
                    <div class="row mb-2">
                        <div class="main-recipe-img col-3 center-block">
                            <img src="images/japanese_main.jpg" alt="調理法の画像" class="img-fluid">
                            <p class="font-weight-bold">主菜</p>
                        </div>
                        <div class="menu-list-box col-8 ml-4">
                            <ul class="row text-center">
     							<c:forEach var="menu" items="${menus}">
	       							<c:choose>
										<c:when test="${menu.tagId == 1}">
			                                <li class="col-5 col-sm-10 col-md-5 col-xl-3 bg-light mr-3 mb-2">
			                                	<a href="show?id=<c:out value="${menu.id}" />"><c:out value="${menu.name}" /></a>
			                                </li>
										</c:when>
									</c:choose>
   								</c:forEach>
                            </ul>
                        </div>
                    </div>
                    <div class="row mb-2">
                        <div class="main-recipe-img col-3 center-block">
                            <img src="images/japanese_sub.jpg" alt="調理法の画像" class="img-fluid">
                            <p class="font-weight-bold">副菜</p>
                        </div>
                        <div class="menu-list-box col-8 ml-4">
                            <ul class="row text-center">
                                <c:forEach var="menu" items="${menus}">
	       							<c:choose>
										<c:when test="${menu.tagId == 2}">
			                                <li class="col-5 col-sm-10 col-md-5 col-xl-3 bg-light mr-3 mb-2">
			                                	<a href="show?id=<c:out value="${menu.id}" />"><c:out value="${menu.name}" /></a>
			                                </li>
										</c:when>
									</c:choose>
   								</c:forEach>
                            </ul>
                        </div>
                    </div>
                    <div class="row mb-2">
                        <div class="main-recipe-img col-3 center-block">
                            <img src="images/japanese_ex.jpg" alt="調理法の画像" class="img-fluid">
                            <p class="font-weight-bold">その他</p>
                        </div>
                        <div class="menu-list-box col-8 ml-4">
                            <ul class="row text-center">
                               <c:forEach var="menu" items="${menus}">
	       							<c:choose>
										<c:when test="${menu.tagId == 3}">
			                                <li class="col-5 col-sm-10 col-md-5 col-xl-3 bg-light mr-3 mb-2">
			                                	<a href="show?id=<c:out value="${menu.id}" />"><c:out value="${menu.name}" /></a>
			                                </li>
										</c:when>
									</c:choose>
   								</c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="container bg-light-green p-2">
            <div class="row pt-4">
                <div class="main-img-box col-sm-3 mb-4 ml-3">
                    <p class="h4 font-weight-bold">中華</p>
                    <div class="main-tag-img center-block">
                        <img src="images/chanese_food.jpg" alt="和食の画像" class="img-fluid center-block">
                    </div>
                </div>
                <div class="main-menu-box col-sm-7 ml-5">
                    <div class="row mb-2">
                        <div class="main-recipe-img col-3 center-block">
                            <img src="images/chanese_main.jpg" alt="調理法の画像" class="img-fluid">
                            <p class="font-weight-bold">主菜</p>
                        </div>
                        <div class="menu-list-box col-8 ml-4">
                            <ul class="row text-center">
                                <c:forEach var="menu" items="${menus}">
	       							<c:choose>
										<c:when test="${menu.tagId == 11}">
			                                <li class="col-5 col-sm-10 col-md-5 col-xl-3 bg-light mr-3 mb-2">
			                                	<a href="show?id=<c:out value="${menu.id}" />"><c:out value="${menu.name}" /></a>
			                                </li>
										</c:when>
									</c:choose>
   								</c:forEach>
                            </ul>
                        </div>
                    </div>
                    <div class="row mb-2">
                        <div class="main-recipe-img col-3 center-block">
                            <img src="images/chanese_sub.jpg" alt="調理法の画像" class="img-fluid">
                            <p class="font-weight-bold">副菜</p>
                        </div>
                        <div class="menu-list-box col-8 ml-4">
                            <ul class="row text-center">
                                <c:forEach var="menu" items="${menus}">
	       							<c:choose>
										<c:when test="${menu.tagId == 12}">
			                                <li class="col-5 col-sm-10 col-md-5 col-xl-3 bg-light mr-3 mb-2">
			                                	<a href="show?id=<c:out value="${menu.id}" />"><c:out value="${menu.name}" /></a>
			                                </li>
										</c:when>
									</c:choose>
   								</c:forEach>
                            </ul>
                        </div>
                    </div>
                    <div class="row mb-2">
                        <div class="main-recipe-img col-3 center-block">
                            <img src="images/chanese_ex.jpg" alt="調理法の画像" class="img-fluid">
                            <p class="font-weight-bold">その他</p>
                        </div>
                        <div class="menu-list-box col-8 ml-4">
                            <ul class="row text-center">
                                <c:forEach var="menu" items="${menus}">
	       							<c:choose>
										<c:when test="${menu.tagId == 13}">
			                                <li class="col-5 col-sm-10 col-md-5 col-xl-3 bg-light mr-3 mb-2">
			                                	<a href="show?id=<c:out value="${menu.id}" />"><c:out value="${menu.name}" /></a>
			                                </li>
										</c:when>
									</c:choose>
   								</c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="container bg-light-blue p-2">
            <div class="row pt-4">
                <div class="main-img-box col-sm-3 mb-4 ml-3">
                    <p class="h4 font-weight-bold">洋食</p>
                    <div class="main-tag-img center-block">
                        <img src="images/western_food.jpg" alt="和食の画像" class="img-fluid center-block">
                    </div>
                </div>
                <div class="main-menu-box col-sm-7 ml-5">
                    <div class="row mb-2">
                        <div class="main-recipe-img col-3 center-block">
                            <img src="images/western_main.jpg" alt="調理法の画像" class="img-fluid">
                            <p class="font-weight-bold">主菜</p>
                        </div>
                        <div class="menu-list-box col-8 ml-4">
                            <ul class="row text-center">
                               <c:forEach var="menu" items="${menus}">
	       							<c:choose>
										<c:when test="${menu.tagId == 21}">
			                                <li class="col-5 col-sm-10 col-md-5 col-xl-3 bg-light mr-3 mb-2">
			                                	<a href="show?id=<c:out value="${menu.id}" />"><c:out value="${menu.name}" /></a>
			                                </li>
										</c:when>
									</c:choose>
   								</c:forEach>
                            </ul>
                        </div>
                    </div>
                    <div class="row mb-2">
                        <div class="main-recipe-img col-3 center-block">
                            <img src="images/western_sub.jpg" alt="調理法の画像" class="img-fluid">
                            <p class="font-weight-bold">副菜</p>
                        </div>
                        <div class="menu-list-box col-8 ml-4">
                            <ul class="row text-center">
                                <c:forEach var="menu" items="${menus}">
	       							<c:choose>
										<c:when test="${menu.tagId == 22}">
			                                <li class="col-5 col-sm-10 col-md-5 col-xl-3 bg-light mr-3 mb-2">
			                                	<a href="show?id=<c:out value="${menu.id}" />"><c:out value="${menu.name}" /></a>
			                                </li>
										</c:when>
									</c:choose>
   								</c:forEach>
                            </ul>
                        </div>
                    </div>
                    <div class="row mb-2">
                        <div class="main-recipe-img col-3 center-block">
                            <img src="images/western_ex.jpg" alt="調理法の画像" class="img-fluid">
                            <p class="font-weight-bold">その他</p>
                        </div>
                        <div class="menu-list-box col-8 ml-4">
                            <ul class="row text-center">
                               <c:forEach var="menu" items="${menus}">
	       							<c:choose>
										<c:when test="${menu.tagId == 23}">
			                                <li class="col-5 col-sm-10 col-md-5 col-xl-3 bg-light mr-3 mb-2">
			                                	<a href="show?id=<c:out value="${menu.id}" />"><c:out value="${menu.name}" /></a>
			                                </li>
										</c:when>
									</c:choose>
   								</c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <footer class="bg-light-yellow pt-3 pb-3">
        <div class="container">
            <p class="text-center mb-0"><a href="index">&copy;2020 All rights reserved.</a></p>
        </div>
    </footer>
</body>

</html>