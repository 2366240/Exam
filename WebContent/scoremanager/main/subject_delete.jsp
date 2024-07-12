<%-- 学生登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
		科目管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報削除</h2>
			<form action ="SubjectDeleteExecute.action" method="post">
				<div class="mx-3 py-2">
					<div class="mb-3">
						<label class="form-label" for="subject-name-input">科目名</label>
						<input readonly class="form-control-plaintext ms-3" type = "text"
							id="subject-cd-input" name="name" value="${name}" />
					</div>
					<p>${name}を削除してもよろしいでしょうか？</p>
					<div class="mb-3">
						<input class="btn btn-primary" type="submit" value="削除">
					</div>
				</div>
			</form>
			<div class="lh-lg row">
				<div class="mx-3 col-1">
					<a href="SubjectList.action">戻る</a>
				</div>
			</div>
		</section>
	</c:param>
</c:import>