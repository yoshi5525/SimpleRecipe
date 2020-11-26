<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String[] colors = {"light-green", "light-pink", "light-blue"};
	String[] tags = {"japanese", "chanese", "western"};
	String[] tagNames = {"和食", "中華", "洋食"};
	String[] types = {"Main", "Sub", "Ex"};
	String[] typeNames = {"主菜", "副菜", "その他"};
	Integer[][] values = {{1, 2, 3}, {11, 12, 13}, {21, 22, 23}};

	request.setAttribute("colors", colors);
	request.setAttribute("tags", tags);
	request.setAttribute("tagNames", tagNames);
	request.setAttribute("types", types);
	request.setAttribute("typeNames", typeNames);
	request.setAttribute("values", values);
%>

<c:forEach var="i" begin="0" end="2">
	<section class=" bg-<c:out value="${colors[i]}" />">
		<div class="container py-2">
			<div class="row pt-4">
				<div class="main-img-box col-lg-4 mb-4">
					<p class="h4 font-weight-bold"><c:out value="${tagNames[i]}" /></p>
					<div class="main-tag-img center-block">
						<img src="images/<c:out value="${tags[i]}" />Food.jpg" alt="<c:out value="${tagNames[i]}" />の画像" class="img-fluid center-block">
					</div>
				</div>
				<div class="main-menu-box col-lg-8">
					<c:forEach var="j" begin="0" end="2">
						<div class="row mb-2">
							<div class="main-recipe-img col-3 center-block">
								<img src="images/<c:out value="${tags[i]}"/><c:out value="${types[j]}"/>.jpg" alt="調理法の画像" class="img-fluid">
								<p class="font-weight-bold"><c:out value="${typeNames[j]}" /></p>
							</div>
							<div class="menu-list-box col-9">
								<ul class="row text-center">
									<c:forEach var="menu" items="${menus}">
										<c:choose>
											<c:when test="${menu.tagId == values[i][j]}">
												<li class="index-menu-list col-5 col-xl-3 bg-light mb-2 py-2 mr-4">
													<a href="show?id=<c:out value="${menu.id}" />"><c:out value="${menu.name}" /></a>
												</li>
											</c:when>
										</c:choose>
									</c:forEach>
								</ul>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</section>
</c:forEach>