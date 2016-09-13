<?php

	echo $_GET['id'];

	
	session_start();


	include('../includes/db_connect.php');
	include 'login_header.php';
	if(!isset($_SESSION['user_id'])){
		header('Location: login.php');
		exit();
	}
	//echo $_SESSION['user_id'];
	$user_id = $_SESSION['user_id'];
	echo $user_id;
	if(isset($_POST['submit'])){
		$url = $_POST['url'];
		if($url){
			$query = $db->query("INSERT INTO Categories (category) VALUES ('$url')");
		}	
		
		// get the blog data
		$title = $_POST['title'];
		$body = $_POST['body'];
		$category = $_POST['url'];
		if(!$category){
			$category = $_POST['category'];
		}
		$title = $db->real_escape_string($title);
		$body = $db->real_escape_string($body);
		$us = $_SESSION['user_id'];
		echo $us;
		$date = date('Y-m-d G:i:s');
		$body = htmlentities($body);
		if($title && $body && $category){
			$query = $db->query("INSERT INTO posts (user_id, title, body, category_id, posted) VALUES ('$user_id', '$title', '$body', '$category', '$date')");
			if($query){
				echo "post added";
			}else{
				echo "error";
			}
		}else{
			echo "missing Data";
		}
	}
	
?>
<html>
	<head>
		<meta charset = "utf-8" />
		<meta http-equiv = "X-UA-Compatible" content = "IE=9" />
		<script src ="http://code.jquery.com/jquery-1.5.min.js"></script>
		<link rel="stylesheet" type = "text/css" href = "admin/style.css">
		<style>
			#wrapper{
				margin: auto;
				width: 800px;
			}
			label{display:block}
		</style>
	</head>
	<link rel="stylesheet" type = "text/css" href = "style.css">
	<body>
		<div id = "wrapper">
			<div id = "content">
				<form action = "<?php echo $_SERVER['PHP_SELF'] ?>" method = "post">
					<label>Title:</label><input type = "text" name = "title" />
					<label for = "body">Body:</label>
					<textarea name = "body" cols = 50 rows = 10></textarea>

					<label>URL:</label><input type = "text" name ="url" />

					<select name = "category">
						<?php
							$query = $db->query("SELECT * FROM Categories");
							while($row = $query->fetch_object()){
								echo "<option value = '".$row->category_id."'>".$row->category."</option>";
							}
						?>
					</select>
					<br />
					<input type = "submit" name = "submit" value = "Submit" />
				</form>
			</div>
		</div>

	</body>

</html> -->