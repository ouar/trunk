
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="navbar">

	<div class="navbar-inner">

		<div class="col-md-8">
			<h2>
				<fmt:message key="Quiz.header.title" />
			</h2>
		</div>
		
		<div class="col-md-2">
			<div class="btn-group">
				<a class="btn" onclick="updateURLParameter('lang','en')">english</a>
				 <a class="btn" onclick="updateURLParameter('lang','fr')">fran&ccedil;ais</a>
			</div>		
		</div>
		
		<div class="col-md-2">
			<div class="btn-group">
				<a class="btn" href="<c:url value="/j_spring_security_logout" />">
					<i class="icon-off"></i> 
					<fmt:message key="Quiz.header.logout" />					
				</a>	
			</div>
		</div>
		
	</div>	

	<div class="modal" id="errorModal" style="display: none;">
	    <div class="modal-header center">
		    <a class="close" data-dismiss="modal">&times;</a>
		    <h3></h3>
		</div>
		<div class="modal-body">
		    <p>
		    	Une erreur est survenu dans la classe <strong></strong>.<em></em> avec le message :
		    	<br/>
		    	<br/> 
		    	<span class="red center"></span>
		    </p>
		</div>
	</div>	
</div>	


