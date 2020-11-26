<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>教えて！味付けアプリ</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
</head>

<body>
	<c:import url="header.jsp" />

    <main>
	    <section class="visual-img">
	    	<div class="container">
		    	<div class="main-visual">
		    		<div class="text-box center-block p-4 bg-light">
						<p class="visual-text h5">味付けを調べることができるレシピアプリです！</p>
						<p class="visual-text h5">使いたい食材の重さを入力いただくことで、必要な調味料を算出します。</p>
						<button class="visual-btn visual-text h4 mt-3 btn">使い方を見る ⇒</button>
		    		</div>
			        <div id="gray-display"></div>
			    	<div class="video center-block">
			    		<video src="images/simple-recipe.mp4" controls autoplay muted id="video"></video>
			        </div>
		        </div>
	        </div>
	    </section>
        <c:import url="indexItems.jsp">
			<c:param name="menus" value="${menus}" />
		</c:import>
		<div id="page_top">
			<a href="#header"><i class="fas fa-arrow-up"></i></a>
		</div>
    </main>

    <c:import url="footer.jsp"/>

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="js/form.js"></script>
	<script type="text/javascript" src="js/search.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
</body>

</html>