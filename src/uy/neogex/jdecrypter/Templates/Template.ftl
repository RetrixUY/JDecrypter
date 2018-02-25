<html>
	<head>
		<title>JDecrypter v4.0</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<style type=\"text/css\">
			.table {
				border-radius: 5px;
				width: 80%;
				margin: 0px auto;
				float: none;
			}
		</style>
	</head>
	<body>
		<div class="title text-center">
			<h1>JDecrypt v4.0</h1>
		</div>
		<div class=links>
			<div class="container" id=wrap>
				<table class="table table-striped table-hover">
					<tr>
						<th>N&deg;</th>
						<th>Link</th>
					</tr>
					<tbody>
						<#list links as Link>
							<tr>
								<th>${Link.getNumber()}</th>
								<th><a href="${Link.getUrl()}" target="_blank">${Link.getUrl()}</a></th>
							</tr>
						</#list>
					</tbody>
				</table>
			</div>
		</div>
		<div class="Footer navbar-fixed-bottom">
			<div clas=container>
				<p class="text-muted text-center">
					Copyright (C) 2018 <a href="https://www.fb.com/neogexstudios">Neogex Studios</a>
				</p>
			</div>
		</div>
	</body>
</html>
						

	