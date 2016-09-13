<!DOCTYPE html>
<html>
<head>
	<title>SIGNUP</title>
</head>
<link rel="stylesheet" type="text/css" href="style.css">
<body>
	<?php
	session_start();

	if(isset($_SESSION['id'])){
		echo "You are already Logged in!!";
	}else{
		echo "<form action='signupbutton.php' method='POST'>
				<input type='text' name='user_id' placeholder='Username'> <br>
				<input type='password' name='pwd' placeholder='Password'> <br>
				<button type='submit'>SIGN UP</button>
			</form>";
	}

?>
</body>
</html>


