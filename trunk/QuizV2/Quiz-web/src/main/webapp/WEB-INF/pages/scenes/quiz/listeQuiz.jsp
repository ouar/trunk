<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach var="quiz" items="${lQuiz}">
	<a href="<c:url value="/web/quiz/get/stats/" />${quiz.id}">${quiz.datQuizz} ${quiz.prenomCandidat} ${quiz.nomCandidat} </a><br/>
</c:forEach>
