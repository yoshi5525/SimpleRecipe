<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String[] tagNames = {"和食", "中華", "洋食"};
	Integer[] values = {1, 11, 21};

	request.setAttribute("tagNames", tagNames);
	request.setAttribute("values", values);
%>

<div class="container bg-light-skyblue pt-4 pb-4">
	<div class="form mx-auto w-75">
		<c:if test="${!empty errors.ERROR_MSG}"><p class="text-center h2 text-danger font-weight-bold mb-4"><c:out value="${errors.ERROR_MSG}" /></p></c:if>
		<form action="" method="post" enctype="multipart/form-data">
			<p class="mb-0">
				写真<br>
				<div class="preview-img mb-2">
					<img src="<c:if test="${!empty image}"><%= request.getContextPath() %>/images/uploads/<c:out value="${image}" /></c:if>" width="400px">
				</div>
				<input type="file" name="image" id="menu-img" <c:if test="${!empty image}">value="<c:out value="${image}" />"</c:if>>
			</p>
			<p>
				料理名<span class="form-require ml-2 text-light px-2 bg-danger rounded">必須</span><br>
				<input type="text" name="name" size="48" class="mt-1" <c:if test="${!empty name}"> value="<c:out value="${name}" />"</c:if>>
				<c:if test="${!empty errors.ERROR_NAME}"><p class="h5 text-danger font-weight-bold mb-4"><c:out value="${errors.ERROR_NAME}" /></p></c:if>
			</p>
			<p>
				料理名(ふりがな)<span class="form-require ml-2 text-light px-2 bg-danger rounded">必須</span><br>
				<input type="text" name="kana" size="48" class="mt-1" <c:if test="${!empty kana}"> value="<c:out value="${kana}" />"</c:if>>
				<c:if test="${!empty errors.ERROR_KANA}"><p class="h5 text-danger font-weight-bold mb-4"><c:out value="${errors.ERROR_KANA}" /></p></c:if>
			</p>
			<p>
				料理区分<span class="form-require ml-2 text-light px-2 bg-danger rounded">必須</span><br>
				<select name="tag_id" class="mt-1">
					<c:forEach var="i" begin="0" end="2">
						<optgroup label="<c:out value="${tagNames[i]}"/>">
							<c:forEach var="tag" items="${tags}">
								<c:choose>
									<c:when test="${tag.id >= values[i] && tag.id < values[i] + 10}">
										<option value="<c:out value="${tag.id}" />" <c:if test="${tag.id == tag_id}">selected</c:if>><c:out value="${tag.name}" /></option>
									</c:when>
								</c:choose>
							</c:forEach>
						</optgroup>
					</c:forEach>
				</select>
				<c:if test="${!empty errors.ERROR_TAG_ID}"><p class="h5 text-danger font-weight-bold mb-4"><c:out value="${errors.ERROR_TAG_ID}" /></p></c:if>
			</p>
			<p>
				<div class="d-inline-block w-25">調味料<span class="form-require ml-2 text-light px-2 bg-danger rounded">必須</span></div>
				<div class="d-inline-block w-50">調味料分量<span class="form-require ml-2 text-light px-2 bg-danger rounded">必須</span></div>
				<c:forEach var="j" begin="0" end="${menuFoodLength - 1}">
					<div class="menu-food mb-2">
						<select name="food_id" class="select-foods w-25 mt-1">
							<c:forEach var="food" items="${foods}">
								<option value="<c:out value="${food.id}" />" <c:if test="${food.id == foodIds[j]}">selected</c:if>><c:out value="${food.name}" /></option>
							</c:forEach>
						</select>
						<input type="number" name="food_quantity" class="select-numbers text-right mt-1"
							value="<c:choose><c:when test="${!empty quantities[j]}"><c:out value="${quantities[j]}" /></c:when><c:otherwise>0.0</c:otherwise></c:choose>"
							size="20" min="0.0" step="0.1" oninput="validity.valid||(value='');">
						<input type="button" value="+" class="add">
						<input type="button" value="－" class="del">
					</div>
				</c:forEach>
				<c:if test="${!empty errors.ERROR_QUANTITY}"><p class="h5 text-danger font-weight-bold mb-4"><c:out value="${errors.ERROR_QUANTITY}" /></p></c:if>
			</p>
			<c:if test="${!empty menuFoodIds}">
				<c:forEach var="menuFoodId" items="${menuFoodIds}">
					<p>
						<input type="hidden" name="menu_food_id" value="<c:out value="${menuFoodId}" />">
					</p>
				</c:forEach>
			</c:if>
			<c:if test="${!empty menu_id}">
				<p>
					<input type="hidden" name="menu_id" value="<c:out value="${menu_id}" />">
				</p>
			</c:if>
			<p>
				<input type="hidden" name="menu-food-length" value="1" id="menu-food-length">
			</p>
			<p>
				食材<br>
				<textarea name="foodstuff" cols="50" rows="8"><c:if test="${foodstuff != null}"><c:out value="${foodstuff}" /></c:if></textarea>
			</p>
			<p>
				レシピ<br>
				<textarea name="recipe" cols="50" rows="8"><c:if test="${recipe != null}"><c:out value="${recipe}" /></c:if></textarea>
			</p>
			<p class="mb-0">
				<c:if test="${empty url}">
					<input type="submit" value="登録する" id="register">
				</c:if>
				<c:if test="${!empty url}">
					<input type="submit" name="edit" value="更新する" id="register" class="mr-4">
					<input type="submit" name="delete" value="削除する" id="delete">
				</c:if>
			</p>
		</form>
		<p class="mt-3">
			<a href="index">トップページへ戻る</a>
		</p>
	</div>
</div>