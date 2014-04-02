<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>


<!--Load the AJAX API-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript"
  src='https://www.google.com/jsapi?autoload={"modules":[{"name":"visualization","version":"1","packages":["orgchart","corechart"]}]}'>
</script>

<script type="text/javascript">


  var orgChart;
  var barChart;
  var dataBar = google.visualization.arrayToDataTable([
['Categorie', 'nbPointsGagnes', 'nbPointsPerdus', { role: 'annotation' } ],
['Quiz', <c:out value="${stats.nbPointsObtenus}"/>, <c:out value="${stats.nbPointsPerdus}"/>, ''],
]);

  google.setOnLoadCallback(drawCharts);
  
  var dataOrg = google.visualization.arrayToDataTable([
['Nom',																	'niveauSup', 'Tooltip'],
[{v:'Quiz',f:'Quiz<div class="note"><c:out value="${stats.noteSur20}/20"/></div>'},																null,		''],
<c:forEach var="langage" items="${stats.lStatsLangages}">
[{v:'1_<c:out value="${langage.langage.id}"/>',f:'<c:out value="${langage.langage.libelle}"/><div class="note"><c:out value="${langage.noteSur20}/20"/></div>'},				'Quiz',''],
<c:forEach var="sujet" items="${langage.mSujets}">
['<c:out value="${sujet.value.sujet.libelle}"/><div class="difficulte">(<c:out value="${sujet.value.difficulte.libelle}"/>)</div><div class="note"><c:out value="${sujet.value.noteSur20}/20"/></div>',			'1_<c:out value="${langage.langage.id}"/>',''],
</c:forEach>
</c:forEach>
]);

  function drawCharts() {

/* 	  var dataOrg = google.visualization.arrayToDataTable([
	['Nom',																	'niveauSup', 'Tooltip'],
	['Quiz',																null,		''],
	<c:forEach var="langage" items="${stats.lStatsLangages}">
	[{v:'<c:out value="${langage.langage.libelle}"/>',f:'<c:out value="${langage.noteSur20}/20"/>'},				'Quiz',''],
	<c:forEach var="sujet" items="${langage.mSujets}">
	[{v:'<c:out value="${sujet.value.sujet.libelle}"/>',f:'<c:out value="${sujet.value.noteSur20}/20"/>'},			'<c:out value="${langage.langage.libelle}"/>',''],
	</c:forEach>
	</c:forEach>
	]); */


	  
       
      orgChart = new google.visualization.OrgChart(document.getElementById('orgChart'));
      //barChart = new google.visualization.BarChart(document.getElementById('barChart'));
      
      
      
      orgChart.draw(dataOrg, {allowHtml: true,width: "400px", height: "400px"});
      //var options = {orientation:'horizontal',isStacked: true};
      //barChart.draw(dataBar,options );
      
      /* function selectHandler() {
    	  alert(dataOrg.getValue(orgChart.getSelection()[0].row, 0));

        }
      
      google.visualization.events.addListener(orgChart, 'select', selectHandler);    
       */
      }
      
/*       var options = {
        width: 600,
        height: 400,
        legend: { position: 'top', maxLines: 3 },
	bar: {groupWidth: '75%'},
        isStacked: true,
      }; */

     /*  var chart = new google.visualization.ColumnChart(document.getElementById('columnchart_stacked'));
      chart.draw(data, options);

      google.visualization.events.addListener(chart, 'select', selectHandler);

      function selectHandler() {
    	  var selection = chart.getSelection();
    	  var message = '';
    	  for (var i = 0; i < selection.length; i++) {
    	    var item = selection[i];
    	    if (item.row != null && item.column != null) {
    	      var str = data.getFormattedValue(item.row, item.column);
    	      message += '{row:' + item.row + ',column:' + item.column + '} = ' + str + '\n';
    	    } else if (item.row != null) {
    	      var str = data.getFormattedValue(item.row, 0);
    	      message += '{row:' + item.row + ', column:none}; value (col 0) = ' + str + '\n';
    	    } else if (item.column != null) {
    	      var str = data.getFormattedValue(0, item.column);
    	      message += '{row:none, column:' + item.column + '}; value (row 0) = ' + str + '\n';
    	    }
    	  }
    	  if (message == '') {
    	    message = 'nothing';
    	  }
    	  alert('You selected ' + message);
    	} */

</script>
<div class="row">
	<div class="col-md-6" style="height: 400px" id="orgChart"></div>
	<div class="col-md-6" style="height: 300px" id="barChart"></div>
</div>
<!-- 	<div style="width: 300px; height: 300px; position: relative;" id="orgChart"></div>
	<div style="width: 300px; height: 300px; position: relative;" id="barChart"></div> -->