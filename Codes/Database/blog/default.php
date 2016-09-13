<?php

	// connect to database
	include('includes/db_connect.php');
	include 'admin/login_header.php';


	//get record of database
	$record_count = $db->query("SELECT * FROM sub");

	// amount displayed
	$per_page = 2;
	//number of pages
	$pages = ceil($record_count->num_rows/$per_page);
	

	//get page number
	if(isset($_GET['p']) && is_numeric($_GET['p'])){
		$page = $_GET['p'];

	}else{
		$page = 1;
	}

	if($page <= 0){
		$start = 0;
	}else{
		$start = $page * $per_page - $per_page;
	}

	$pre = $page - 1;
	$next = $page + 1;

	$query = $db->prepare("SELECT sub_id, user_id, title, LEFT(body, 100) AS body, userName, posted FROM sub order by sub_id desc limit $start, $per_page");
	$query->execute();
	$query->bind_result($sub_id, $user_id, $title, $body, $userName, $posted);

?>
<html lang = "en">
	<head >
		<meta charset = "utf-8" />
		<meta http-equiv = "X-UA-Compatible" content = "IE=9" />
		<script src = "http://code.jquery.com/jquery-1.5.min.js"></script>
		<link rel="stylesheet" type = "text/css" href = "admin/style.css">
	</head>
	<style>
		#container{
			margin: auto;
			width: 800px;

		}
	</style>
	<body>
		<div id = "container">
			<?php
				while($query->fetch()):
				$lastspace = strrpos($body, ' ');
			?>
				<article>

					<h2>Title: <?php echo $title ?></h2>
					<h4>User_id: <?php echo $user_id ?></h4>
					<p>Description: <?php echo substr($body, 0, $lastspace)."<a href = 'new_post.php?id=$sub_id'><br>[more...]</a>" ?></p>
					<p>Created By: <?php echo $userName ?></p>
					<p>Created Time: <?php echo $posted ?></p>
					
				</article>
				<?php endwhile?>

				<?php
					if($pre >0){
						echo "<a href = 'default.php?p=$pre'>Prev</a>";
					}

					if($page < $pages){
						echo "<a href = 'default.php?p=$next'>&nbsp Next</a>";
					}
				?>
	</body>