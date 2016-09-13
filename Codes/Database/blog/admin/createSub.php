<?php
	session_start();
	include('../includes/db_connect.php');
	include 'login_header.php';
	if(!isset($_SESSION['user_id'])){
		header('Location: login.php');
		exit();
	}

	$user_id = $_SESSION['user_id'];
	//echo $user_id;
	//echo $_SESSION['user_id'];
	if(isset($_POST['submit'])){
			
		
		// get the blog data
		$title = $_POST['title'];
		$body = $_POST['body'];
		$isDefault = $_POST['isDefault'];
		
		$isDefault = $db->real_escape_string($isDefault);
		$title = $db->real_escape_string($title);
		$body = $db->real_escape_string($body);
		//$user_id = $_SESSION['user_id'];
		//echo $user_id;
		$date = date('Y-m-d G:i:s');
		$body = htmlentities($body);
		$row = $db->query("SELECT * FROM user WHERE user_id = '$user_id' ");
		//echo $who->num_rows;
		$who=mysqli_fetch_assoc($row);
		$result = $who['username'];
		//echo $isDefault;
		if($title && $body && $isDefault){
			$query = $db->query("INSERT INTO sub(user_id, title, body, isdefault, userName, posted) VALUES ('$user_id', '$title', '$body','$isDefault', '$result','$date')");
			

			if($query){
				echo "subsaiddit added";
			
				header ("Location: http://localhost/blog/default.php");
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
					<label for = "body">Description:</label>
					<textarea name = "body" cols = 50 rows = 10></textarea>

					<label>Is the in default page? (yes/no)</label><input type = "text" name = "isDefault" >
					

					
					<br />
					<input type = "submit" name = "submit" value = "Submit" />
				</form>
			</div>
		</div>

	</body>

</html>