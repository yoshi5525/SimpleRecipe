<%@ page pageEncoding="UTF-8"%>

<header class="bg-light-yellow p-3">
    <div class="container">
        <div class="row">
            <h1 class="h2 mb-0 col-12 col-md-5"><a href="index">味付けどうする？</a></h1>
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