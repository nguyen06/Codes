<?php
	//echo $_GET['id'];

	if(!isset($_GET['id'])){
		header("Location: index.php");
		exit();
	}else{
		$id = $_GET['id'];
	}

	include('includes/db_connect.php');
	include 'admin/login_header.php';
	if(!is_numeric($id)){
		header('Location: index.php');
	}

	$fav = "no";
	if(isset($_POST['favourite'])){
		$fav = "yes";
		$qu = "UPDATE posts SET favourite = '$fav' WHERE post_id = '$id'";
		$quer = $db->query($qu);
		if($quer === TRUE){
			echo "updated favourite";
		}else{
			echo "error update favourite";
		}
	}

	$q = $db->query("SELECT * FROM posts WHERE post_id = '$id'");
	$re = $q->fetch_assoc();

	$up = $re['uVote'];
	$down = $re['dVote'];
	//echo $down;
	if(isset($_POST['up'])){
		$up = $up + 1;
		$qu = "UPDATE posts SET uVote = '$up' WHERE post_id = '$id'";
		$quer = $db->query($qu);
		if($quer === TRUE){
			echo "up Vote Success";
		}else{
			echo "error error of up Vote";
		}
	}

	if(isset($_POST['down'])){
		$down = $down + 1;
		$qu = "UPDATE posts SET dVote = '$down' WHERE post_id = '$id'";
		$quer = $db->query($qu);
		if($quer === TRUE){
			echo "down Vote Success";
		}else{
			echo "error error of up Vote";
		}

	}


	if(isset($_POST['delete'])){
		//$down = $down + 1;
		$de = "DELETE FROM posts WHERE post_id = '$id'";
		$quer = $db->query($de);
		if($quer === TRUE){
			echo "delete Success";
		}else{
			echo "error error of delete";
		}
		$deCom = "DELETE FROM Comments WHERE post_id = '$id'";
		$q = $db->query($deCom);
		if($q === TRUE){
			echo "delete Comment Success";
		}else{
			echo "error error of delete Comment";
		}
	}


	//$totalV = $up + $down;
	//echo $totalV;

	$sql = "SELECT title, body, uVote, dVote FROM posts WHERE post_id = '$id'";
	$query = $db->query($sql);
	//echo "$query->num_rows";
	if($query->num_rows != 1){

		header('Location: index.php');
		exit();
	}
	if(isset($_POST['submit'])){
		$email = $_POST['email'];
		$name = $_POST['name'];
		$comment = $_POST['comment'];
		$date = date('Y-m-d G:i:s');
		if($email && $name && $comment){
			$email = $db->real_escape_string($email);
			$name = $db->real_escape_string($name);
			$id = $db->real_escape_string($id);
			$comment = $db->real_escape_string($comment);
			
			$dat = $db->real_escape_string($date);

			$que = $db->query("INSERT INTO comments (name, post_id, email_add, comment, created_time) VALUES ('$name', '$id', '$email', '$comment', '$dat')");
			if($que){
				echo "comment added";
				//header("Location: http://localhost/blog/index.php");
				//$que->close();
			}else{
				echo "error";
			}

			/*
			if($addComment = $db->prepare("INSERT INTO comments(name, post_id, email_add, comment) VALUES (?,?,?,?)")){
					$addComment->bind_param('ssss', $name, $id, $email, $comment);
					$addComment->execute();
					//echo "Thank you comment was added";
					$addComment->close();
			}else{
				echo "ERROR";
			}
			*/
		}else{
			echo "ERROR";
		}
	}

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
			<div id = "post">
				<?php
				$row = $query->fetch_object();
				echo "<h2>".$row->title."</h2>";
				echo "<p>".$row->body."</p>";
				echo "<label>Up &emsp; $row->uVote </label>";
				echo "<label>Down &emsp; $row->dVote </label>";
				//echo "<label>".$row->uVote."</label>";
				?>
			</div>
			<hr />
			<div id = "down">
				<form action = "<?php echo $_SERVER['PHP_SELF']."?id=$id"?>" method = "post">
				<div>
						<input type = "submit" name = "down" value = "Down"/>
				</div>
				
			</div>
			<div id = "up">
				<form action = "<?php echo $_SERVER['PHP_SELF']."?id=$id"?>" method = "post">
				<div>
						<input type = "submit" name = "up" value = "Up"/>
				</div>
				
			</div>
			<div id = "favourite">
				<form action = "<?php echo $_SERVER['PHP_SELF']."?id=$id"?>" method = "post">
				<div>
						<input type = "submit" name = "favourite" value = "Favourite"/>
				</div>
				
			</div>
			<div id = "delete">
				<form action = "<?php echo $_SERVER['PHP_SELF']."?id=$id"?>" method = "post">
				<div>
						<input type = "submit" name = "delete" value = "Delete"/>
				</div>
				
			</div>
			<hr />
			<div id = "add-comments">
				<form action = "<?php echo $_SERVER['PHP_SELF']."?id=$id"?>" method = "post">
					<div>
						<label>Email Address</label><input type = "text" name ="email" />
					</div>
					<div>
						<label>Name</label><input type = "text" name ="name" />
					</div>
					<div>
						<label>Comment</label><textarea name ="comment"></textarea>
					</div>
					<input type = "submit" name = "submit" value = "Submit"/>
				</form>
			</div>
			<hr />
			<label>Comments</label>

			<div id = "comment">
				<?php
					$query = $db->query("SELECT * FROM comments WHERE post_id = '$id' ORDER BY comment_id DESC");
					while($row = $query->fetch_object()):
				?>

					<div>
						<br>
						<br>
						<h5>Create By: <?php echo $row->name ?></h5>
						<blockquote><?php echo $row->comment ?></blockquote>
						<h6>Created time: <?php echo $row->created_time ?></h6>
					</div>
				<?php endwhile; ?>
		</div>
	</body>
</html>
