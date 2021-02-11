<!DOCTYPE HTML>
<html>

	<head>
    	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/my-test.css">
	</head>

	<body>
		<main>
  			<h1>URL Shortener</h1>
  			<form action="shortenURL" method="post">
  				<input type="text" name=longURL id="input" placeholder="Enter URL here"">
  				<div class="buttons">
    				<button id="shorten">Shorten</button>
  				</div>
  			</form>
  			<section id="section">${shortURL}</section>
		</main>
	</body>

</html>