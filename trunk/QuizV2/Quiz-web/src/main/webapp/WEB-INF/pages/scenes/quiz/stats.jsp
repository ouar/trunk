<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script	src='<c:url value="/resources/js/libs/highcharts/highcharts.js" />'></script>


<script type="text/javascript">

tabLibelle = new Array();
tabNbPointsObtenus = new Array();
tabNbPointsPerdusMauvaiseReponse = new Array();
tabNbPointsPerdusQuestionNonRepondue = new Array();
tabNbPointsPerdusQuestionPartielle = new Array();


tabLibelle.push('<b>Quiz</b>');
tabNbPointsObtenus.push(${stats.nbPointsObtenus});
tabNbPointsPerdusMauvaiseReponse.push(${stats.nbPointsPerdusMauvaiseReponse});
tabNbPointsPerdusQuestionNonRepondue.push(${stats.nbPointsPerdusQuestionNonRepondue});
tabNbPointsPerdusQuestionPartielle.push(${stats.nbPointsPerdusQuestionPartielle});


<c:forEach var="langage" items="${stats.lStatsLangages}">
tabLibelle.push('<i><c:out value="${langage.langage.libelle}"/></i>');
tabNbPointsObtenus.push(${langage.nbPointsObtenus});
tabNbPointsPerdusMauvaiseReponse.push(${langage.nbPointsPerdusMauvaiseReponse});
tabNbPointsPerdusQuestionNonRepondue.push(${langage.nbPointsPerdusQuestionNonRepondue});
tabNbPointsPerdusQuestionPartielle.push(${langage.nbPointsPerdusQuestionPartielle});
</c:forEach>
<c:forEach var="langage" items="${stats.lStatsLangages}">
<c:forEach var="sujet" items="${langage.mSujets}">
tabLibelle.push('<i><c:out value="${langage.langage.libelle}"/></i><br/><c:out value="${sujet.value.sujet.libelle}"/><br/>(<c:out value="${sujet.value.difficulte.libelle}"/>)');
tabNbPointsObtenus.push(${sujet.value.nbPointsObtenus});
tabNbPointsPerdusMauvaiseReponse.push(${sujet.value.nbPointsPerdusMauvaiseReponse});
tabNbPointsPerdusQuestionNonRepondue.push(${sujet.value.nbPointsPerdusQuestionNonRepondue});
tabNbPointsPerdusQuestionPartielle.push(${sujet.value.nbPointsPerdusQuestionPartielle});
</c:forEach>
</c:forEach>





$(function () {
    $('#chartContainer').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: 'Résultat du quiz de <c:out value="${stats.candidat.prenom}"/> <c:out value="${stats.candidat.nom}"/>'
        },
        xAxis: {
            categories: tabLibelle
        },
        yAxis: {
        	min: 0,
            title: {
                text: 'Note'
            }
        },
        tooltip: {
            pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.percentage:.1f}%</b> ({point.y:,.0f})<br/>',
            shared: true
        },
        plotOptions: {
            column: {
                stacking: 'normal'
            }
        },
        series: [{
        	name: 'Réponse fausse',
            color: "#DD0000",
            index:0,
            data: tabNbPointsPerdusMauvaiseReponse
        },{
        	name: 'Réponse incomplète',
            color: "#FFBF00",
            index:2,
            data: tabNbPointsPerdusQuestionPartielle
        },{
        	name: 'Sans réponse',
            color: "#FA8258",
            index:1,
            data: tabNbPointsPerdusQuestionNonRepondue
        },{
        	name: 'Réponse correcte',
            color: "#009000",
            index:3,
            data: tabNbPointsObtenus
        }]
    });
});


</script>
<div class="row">
	<div class="col-md-8 col-md-offset-2" id="chartContainer"></div>
	</div>
<!-- 	<div style="width: 300px; height: 300px; position: relative;" id="orgChart"></div>
	<div style="width: 300px; height: 300px; position: relative;" id="barChart"></div> -->