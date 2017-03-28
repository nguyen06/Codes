<?php
	/*
	session_start();
	if(!isset($_SESSION['user_id'])){
		header('Location: login.php');
		exit();
	}
	$id = $_SESSION['user_id'];
	// connect to database
	*/
	include('includes/db_connect.php');

	include 'admin/login_header.php';
	//$id = $_SESSION['user_id'];
	//get record of database
	$record_count = $db->query("SELECT * FROM posts");

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
	//echo " hi";
	
	$query = $db->prepare("SELECT post_id, user_id, title, LEFT(body, 100) AS body,category_id, posted, uVote, dVote, EditDate, subsaidit, subID FROM posts order by post_id desc limit $start, $per_page");
	$query->execute();
	$query->bind_result($post_id, $user_id, $title, $body, $category_id, $posted, $uVote, $dVote, $EditDate, $subsaidit, $subID);
	/*
	echo "pass";
	$va = $query->fetch();
	$uV = $uVote;
	$u = 0;
	echo $uV;
	/*
	if(isset($_POST['up'])){
		$u = $uV + 1;
		//echo $u;
		$qeu = $db->query("UPDATE posts SET uVote = $u WHERE user_id = $id");
		if(!$qeu){
			echo "could not ubdate";
		}else{
			echo "Update date seccefully";
		}
	}
		

	if(isset($_POST['delete'])){
					//echo $_POST['delete_rec_id'];
       				$id = $_POST['delete_rec_id'];
       				echo $id; 
       				$sql = "DELETE FROM posts WHERE post_id = '$id' ";     				
       				if(mysqli_query($db,$sql)){
       					echo " work";
       				}else{
       					 echo "not work";
       				}
    			}
	
	*/
	

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
					<p>Post Id: <?php echo $post_id ?></p>
					<p>User Id: <?php echo $user_id ?></p>
					<p>Content: <?php echo substr($body, 0, $lastspace)."<a href='post.php?id=$post_id'>[MORE..]</a>" ?></p>
					<p>URL: <?php echo $category_id ?></p>
					<p>Created date: <?php echo $posted ?></p>
					<p>Up: <?php echo $uVote ?></p>
					<p>Down: <?php echo $dVote ?></p>
					<p>Date Edit: <?php echo $EditDate ?></p>
					<p>Subsaidit ID: <?php echo $subID ?></p>

				</article>
			<?php endwhile ?>
		</div>

					<!-- <form id = "delete" method = "post">
					<input type = "hidden" name ="delete_rec_id" value = "<?php //print $posted; ?>" />
					
					<input type = "submit" name = "down" value = "down" /><br>
					<input type = "submit" name = "delete" value = "Delete!" />
					</form>

					<form id = "up" method = "post">
						<input type = "submit" name = "up" value = "up" /> &emsp; &emsp;
					</form>
					
				</article>
				<?php //endwhile?>
				<br><br>
 -->
				<?php
					if($pre >0){
						echo "<a href = 'index.php?p=$pre'>Prev</a>";
					}

					if($page < $pages){
						echo "<a href = 'index.php?p=$next'>&nbsp Next</a>";
					}
				?>
	</body>
</html>
