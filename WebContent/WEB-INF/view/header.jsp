<%@ page pageEncoding="UTF-8"%>
<head>
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
</head>

<header class="bg-light-yellow py-3" id="header">
    <div class="container">
        <div class="row">
            <h1 class="h2 mb-0 col-9 col-md-5">
            	<a href="index"><img src="images/top-logo.png" alt="教えて！味付けアプリ" width="280"></a>
            </h1>
            <button class="search-icon col-2 d-block d-md-none ml-4 btn"><i class="fas fa-search fa-2x"></i></button>
            <div id="dark-display">
            	<p><i id="close-btn" class="fas fa-times fa-lg"></i></p>
            	<form action="search" method="get" class="form-inline search-form">
                	<input type="text" name="search-name" autocomplete="off" placeholder="料理名を入力" class="form-control mr-2 w-75 search-box">
                	<button class="search-btn btn bg-light my-2">検索</button>
             	</form>
             	<div class="result-box position-absolute fixed-bottom">
             		<ul class="result-list bg-light">
             		</ul>
             	</div>
            </div>
            <div class="search-box d-none d-md-block col-md-6 position-relative pt-2 ml-3">
            	<form action="search" method="get" class="form-inline mr-2" id="search-form">
                	<input type="text" name="search-name" autocomplete="off" placeholder="料理名を入力" class="form-control mr-2 w-75" id="search-box">
                	<button id="btn search-btn" class="search-btn btn btn-outline-info my-2">検索</button>
             	</form>
             	<div class="position-absolute fixed-bottom" id="result-box">
             		<ul class="bg-light" id="result-list">
             		</ul>
             	</div>
            </div>
        </div>
    </div>
</header>