<!DOCTYPE html>
<html lang="en">
 

  <body class="preview" id="top" data-spy="scroll" data-target=".subnav" data-offset="80">
  <!--  <script src="../js/bsa.js"></script> -->

<jsp:include page="include/menu.jsp" />
  
<div class="wrapper">
	<div class="container">
	
		<div class="upbtnwrapp"><div class="upbtn" style="display: none;"><span class="icon-up-open-big"></span></div></div>
	
	
		<div class="container-fluid page">
		
			<!-- ABOUT ME -->
			<section id="about_me" class="row-fluid about_me">
				<aside class="span3 aside_el">
					<h2>About me</h2>
					<h5>professional profile<br> and contact info</h5>
				</aside>
				<article class="span6 borderleft par_el">
					<span class="circle"><img alt="" src="img/mypic.jpg" style="float:left"></span>
					<p>Hello, Im Lorem ipsum dolor sit amet, conseur adipiscing elit puella magna est.</p>
					<p>Etiam sem eros, interdum at rutrum et, hendrerit id nisi. Etiam iaculis lorem eget arcu gravida lacinia. Fringilla justo ullamcorper ac. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Raesent sem elit, volutpat id vulputate faucibus, fringilla vel massa. Proin nec mi a mi tincidunt elementum sed vel ipsum.</p>
					
				</article>
				<div class="span3 cont_info">
					<div class="soc">
						<ul>
							<li><a href=""><img alt="" src="<%=request.getContextPath()%>/bootstrap/img/facebook.png"></a></li>
				<li><a href=""><img alt="" src="<%=request.getContextPath()%>/bootstrap/img/twitter.png"></a></li>
				<li><a href=""><img alt="" src="<%=request.getContextPath()%>/bootstrap/img/linkedin.png"></a></li>
				<li><a href=""><img alt="" src="<%=request.getContextPath()%>/bootstrap/img/google-plus.png"></a></li>
				<li><a href=""><img alt="" src="<%=request.getContextPath()%>/bootstrap/img/rss.png"></a></li>
						</ul>
					</div>
					<div class="info">
						<ul class="dropdown-menu" id="swatch-menu">
							<li><span class="icon-mobile"></span><a>+387 51 000 111</a></li>
							<li><span class="icon-location"></span><a>Brace Jugovica 21</a></li>
							<li><span class="icon-mail"></span><a>mail@mail.com</a></li>
						</ul>
					</div>
				</div> <!-- /cont_info -->
			</section> <!-- /about_me -->
			<!-- end ABOUT ME -->		
		
		</div> <!-- /page -->
		
	


	</div> <!-- /container -->
</div>


<jsp:include page="include/piedpage.jsp" />

  </body>
</html>
