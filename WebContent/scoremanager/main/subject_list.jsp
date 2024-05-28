<%-- 学生一覧 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secodary bg-opacity-10 py-2 px-4">成績一覧(科目)</h2>
			<div class="my-2 text-end px-4">
				<a href="StudentCreate.action">新規登録</a>
			</div>
			<div class="my-2 text-end px-4">
				<a href="StudentUpdate.action">学生変更</a>
			</div>
			<form method="get">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
					<div class="col-4">
						<label class="from-label" for="student-fl-select">入学年度</label>
						<select class="form-select" id="student-fl-select" name="fl">
							<option value="0">--------</option>
							<c:forEach var="year" items="${ent_year_set}">
								<%-- 現在のyearと選択されていたflが一致していた場合selectedを追記 --%>
								<option value="${yesr}" <c:if test="${year==fl}">select</c:if>>${year}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-4">
						<label class="form-label" for="student-f2-select">クラス</label>
						<select class="form-select" id="student-f2-select" name="f2">
							<option value="0">--------</option>
							<c:forEach var="num" items="${class_num_set}">
								<%--現在のnumと選択されていたf2が一致していた場合selecttedを追記 --%>
								<option value="${num}" <c:if test="${num==f2}">selectted</c:if>>${num}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			 </form>
			 <c:choose>
			 	<c:when test="${students.size()>0}">
			 		<div>検索結果:${students.size()}件</div>
			 		<table class="table table-hover">
			 			<tr>
			 				<th>入学年度</th>
			 				<th>クラス</th>
			 				<th>学生番号</th>
			 				<th>氏名</th>
			 				<th>1回目</th>
			 				<th>2回目</th>
			 				<th></th>
			 				<th></th>
			 			</tr>
			 			<c:forEach var="student" items="${students}">
			 				<tr>
			 					<td>${student.entYear}</td>
			 					<td>${student.no }</td>
			 					<td>${student.name}</td>
			 					<td>${student.classNum}</td>
			 					<td class="text-center">
			 						<%--在学フラグが立っている場合「〇」それ以外は「✕」を表示 --%>
			 					</td>
			 					<td><a href="StudentUpdate.action?no=${student.no}">変更</a></td>
			 					<td><a href="StudentDelete.action?no=${student.no}">削除</a></td>
			 				</tr>
			 			</c:forEach>
			 		</table>
			 	</c:when>
			 	<c:otherwise>
			 		<div>学生情報が存在しませんでした</div>
			 	</c:otherwise>
			 </c:choose>
		</section>
	</c:param>
</c:import>


