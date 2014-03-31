<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<!--Load the AJAX API-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript"
  src='https://www.google.com/jsapi?autoload={"modules":[{"name":"visualization","version":"1","packages":["corechart"]}]}'>
</script>

<script type="text/javascript">

 var datas = new Array();;
 datas['0']=['quiz',<c:out value="${stats.nbPointsObtenus}"/>,<c:out value="${stats.nbPointsPerdus}"/>,''];
 <c:forEach var="langage" items="${stats.lStatsLangages}">
  datas['1<c:out value="${langage.langage.id}"/>']=['<c:out value="${langage.langage.libelle}"/>',<c:out value="${langage.nbPointsObtenus}"/>,<c:out value="${langage.nbPointsPerdus}"/>,''];
	<c:forEach var="sujet" items="${langage.mSujets}">
   datas['2<c:out value="${sujet.value.sujet.id}"/>']=['<c:out value="${sujet.value.sujet.libelle}"/>',<c:out value="${sujet.value.nbPointsObtenus}"/>,<c:out value="${sujet.value.nbPointsPerdus}"/>,'']
</c:forEach></c:forEach>

  google.setOnLoadCallback(drawChart);
  function drawChart() {

      var data = google.visualization.arrayToDataTable([
        ['Sujets','Nombre de points récoltés','Nombre de points perdus', { role: 'annotation' } ],
        datas['0'],
      ]);

      var options = {
        width: 600,
        height: 400,
        legend: { position: 'top', maxLines: 3 },
	bar: {groupWidth: '75%'},
        isStacked: true,
      };

      var chart = new google.visualization.ColumnChart(document.getElementById('columnchart_stacked'));
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
    	}
  }
</script>
<div style="width: 900px; height: 300px; position: relative;" id="columnchart_stacked"></div>