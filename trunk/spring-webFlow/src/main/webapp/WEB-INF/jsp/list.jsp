<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<center>  


	    <table>
	        <tr>
	            <th>id</th>
	            <th>STOCK_CODE</th>
	            <th>STOCK_NAME</th>
	        </tr>
	        
	        <h2>Stock list</h2>
	       <c:forEach items="${stock}" var="stock"> 
	            <tr>
	                <td><c:out value="${stock.stockId}" /></td>
	                <td><c:out value="${stock.stockCode}" /></td>
	                <td><c:out value="${stock.stockName}" /></td>
	            </tr>
	        </c:forEach>
	    </table>
	 
	</center> 