<jsp:useBean id="message" scope="request" type="java.lang.String"/>
<html>
<head>
  <title>Spring MVC Ajax Demo</title>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
  <script type="text/javascript">
    function doAjax() {
      $.ajax({
        url: 'time.html',
        data: ({name : "me"}),
        success: function(data) {
          $('#time').html(data);
        }
      });
    }
  </script>
</head>
<body>
${message}
<button id="demo" onclick="doAjax()" title="Button">Get the time!</button>
<div id="time">
</div>
</body>
</html>
