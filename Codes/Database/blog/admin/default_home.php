<html lang = "en">
	<head >
		<meta charset = "utf-8" />
		<meta http-equiv = "X-UA-Compatible" content = "IE=9" />
		<script src = "http://code.jquery.com/jquery-1.5.min.js"></script>
		<style>
		body{

		}
		#container{
			padding:10px;
			width:800px;
			margin:auto;
			background:moccasin;
		}
		#menu{
			height:40px;
			line-height:40px;
		}
		#menu ul{
			margin:0;
			padding:0;
		}
		#menu ul li{
			display:inline;
			list-style:none;
			margin-right:10px;
			font-size:18px;
		}
		#mainContent{
			clear:both;
			margin-top:5px;
			font-size:25px;
		}
		#header{
			height:80px;
			line-height: 80px;

		}
		#container #header h1{
			font-size:45px;
			margin:0;
		}
	</style>
	</head>
	<link rel="stylesheet" type = "text/css" href = "style.css">
	<body>
		<div id = "container">
			<div id = "menu">
				<ul>
					<li><a href="index.php">HOME</a></li>
					<li><a href="new_post.php">CREATE NEW SUBSAIDIT</a></li>
					<li><a href="#">DELETE</a></li>
					<li><a href="logout.php">LOG OUT</a></li>
					<li><a href="#">BLOG HOME PAGE</a></li>
				</ul>
			</div>
			<div id = "mainContent">
				<table>
					<tr>
						<td>Total Blog Post</td>
						<td><?php echo $post_count->num_rows ?></td>
					</tr>

					<tr>
						<td>Total Comments </td>
						<td><?php echo $comment_count->num_rows ?></td>
					</tr>
