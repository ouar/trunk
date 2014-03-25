<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


				<c:forEach var="quiz" items="${lQuiz}">
					<option value="${quiz.id}">${quiz.datQuizz} ${quiz.nomCandidat} ${quiz.prenomCandidat}</option>
				</c:forEach>
		  	</select>
		  </p>

		  <div>
		  	<input type="submit" value="Consulter"/>
		  </div>
	 </form:form>