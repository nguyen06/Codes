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

		$query = $db->prepare("SELECT post_id, user_id, title, LEFT(body, 100) AS body,category_id, posted, uVote, dVote, EditDate, subsaidit, subID FROM posts WHERE user_id = '$var' AND favourite = 'yes'");
		$query->execute();
		$query->bind_result($post_id, $user_id, $title, $body, $category_id, $posted, $uVote, $dVote, $EditDate, $subsaidit, $subID);

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
					<p>Post Id: <?php echo $post_id ?></p>
					<p>User Id: <?php echo $user_id ?></p>
					<p>Content: <?php echo $body ?></p>
					<p>URL: <?php echo $category_id ?></p>
					<p>Created date: <?php echo $posted ?></p>
					<p>Up: <?php echo $uVote ?></p>
					<p>Down: <?php echo $dVote ?></p>
					<p>Date Edit: <?php echo $EditDate ?></p>
					<p>Subsaidit ID: <?php echo $subID ?></p>

				</article>
			<?php endwhile ?>
		</div>
		<hr/>
		<div id = "get-userName">
			<form action = "<?php echo $_SERVER['PHP_SELF']?>" method = "post">
				<div>
						<label>User Name: </label><input type = "text" name ="user_name" />
				</div>
				<input type = "submit" name = "submit" value = "Find favourite Posts"/>