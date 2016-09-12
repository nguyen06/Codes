<?php
	include('../includes/db_connect.php');
	include 'login_header.php';
	$name = "";
	if(isset($_POST['user_name'])){
		$name = $_POST['user_name'];
		$name = $db->real_escape_string($name);
	}
		//echo $name;

		$userID = $db->query("SELECT * FROM user WHERE username = '$name'");
		$result = $userID->fetch_assoc();
		$var = $result["user_id"];
		echo $var;

		// we have already got the user_id of user, we can go to post to get all post belong to this user

		$query = $db->prepare("SELECT sub_id, user_id, title, body,isdefault, username, posted FROM sub WHERE user_id = '$var'");
		$query->execute();
		$query->bind_result($sub_id, $user_id, $title, $body, $isdefault, $username, $posted);

?>

<html lang = "en">
	<head >
		<meta charset = "utf-8" />
		<meta http-equiv = "X-UA-Compatible" content = "IE=9" />
		<script src = "http://code.jquery.com/jquery-1.5.min.js"></script>
		<link rel="stylesheet" type = "text/css" href = "admin/style.css">
		<style tyle = "text/css">
			#container{
				width: 800px;
				padding:5px;
				margin: auto;
			}
			label{
				display:block;
			}
		</style>
	</head>
	<body>
		<div id = "container">
			<?php
				
				while($query->fetch()):
				$lastspace = strrpos($body, ' ');
			?>
				<article>
					<h2>Title: <?php echo $title ?></h2>
					<p>Subsaiddit Id: <?php echo $sub_id ?></p>
					<p>User Id: <?php echo $user_id ?></p>
					<p>Description: <?php echo $body ?></p>
					<p>User name: <?php echo $username ?></p>
					<p>Created date: <?php echo $posted ?></p>
					

				</article>
			<?php endwhile ?>
		</div>
		<hr/>
		<div id = "get-userName">
			<form action = "<?php echo $_SERVER['PHP_SELF']?>" method = "post">
				<div>
						<label>User Name: </label><input type = "text" name ="user_name" />
				</div>
				<input type = "submit" name = "submit" value = "Find Subsaiddits"/>