<?php
	session_start();
	if(!isset($_SESSION['user_id'])){
		header('Location: login.php');
		exit();
	}
	include 'login_header.php';

	include('../includes/db_connect.php');

	$id = $_SESSION['user_id'];
	$user_name = $db->query("SELECT userName FROM user WHERE user_id = $id");
	$row = $user_name->fetch_assoc();
	//echo $row['userName'];

	$reputation = $db->query("SELECT reputation FROM user WHERE user_id = $id");
	$rep = $reputation->fetch_assoc();

	// post count
	$post_count = $db->query("SELECT * FROM posts");

	// comment count
	$comment_count = $db->query("SELECT * FROM comments");
?>
<html lang = "en">
	<head >
		<meta charset = "utf-8" />
		<meta http-equiv = "X-UA-Compatible" content = "IE=9" />
		<script src = "http://code.jquery.com/jquery-1.5.min.js"></script>
		<link rel="stylesheet" type = "text/css" href = "style.css">
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
					
					<li><a href="../index.php">DEFAULTPOSTS</a></li>
					<li><a href="createSub.php">C_SUBSAIDIT</a></li>
					<li><a href="getSortA.php">POSTSFROMACCOUNT</a></li>
					
					<li><a href="logout.php">LOGOUT</a></li>
					<li><a href="getPosts.php">GETPOSTS</a></li>
					<li><a href="favPosts.php">FAVOURITE</a></li>
					<li><a href="getSub.php">G_SUBSAIDDITS</a></li>
					<li><a href="../default.php">ALLSUBSAIDDITS</a></li>
					<li><a href="getAllPosts.php">ALLPOSTSofSUBSAIDDIT</a></li>
				</ul>
			</div>
			<div id = "mainContent">
				<table>
					<tr>
						<br>
						<br>
						<td>Welcome:  </td>
						<td><?php echo  $row['userName']?></td>
					</tr>

					<!-- <tr>
						<td>Reputation: </td>
						<td><?php echo  10?></td>
					</tr>  -->
					<tr>
						<td>Total Blog Posts Now: </td>
						<td><?php echo $post_count->num_rows ?></td>
					</tr>

					<tr>
						<td>Total Comments Now: </td>
						<td><?php echo $comment_count->num_rows ?></td>
					</tr>













	</body>
</html>