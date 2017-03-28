<link rel="stylesheet" type="text/css" href="style.css">
<?php
	session_start();
	include 'dbh.php';
	// Get values passe from form in login.php file
	$username = $_POST['user'];
	$password = $_POST['pass'];

	// to present mysql injection
	//$username = stripcslashes($username);
	//$password = stripcslashes($password);

	/* Commented out by Rich on Sunday Jul 17th
	$link = mysqli_connect("localhost","root","");
	mysqli_select_db($link, "accounts");

	$username = mysqli_real_escape_string($link, $username);
	$password = mysqli_real_escape_string($link, $password);*/

	// connect to the server and select databases
	
	$sql = "SELECT * FROM user WHERE username='$username'";
	$result = mysqli_query($conn,$sql);
	$row=mysqli_fetch_assoc($result);
	$hash_pwd= $row['password'];
	$hash = password_verify ($password,$hash_pwd);

	if($hash == 0){
		echo "Failed to Log In!! <br> Please check your Username and Password";
		echo"	<form action= login.php>
					<button type='submit' name'submit'>Back</button>
			</form>";
		exit;
	}else{
		$sql = "SELECT * FROM user where username = '$username' and password = '$hash_pwd' ";
		echo $sql->num_rows;
		$result = mysqli_query($conn,$sql);

		if (!$result) {
	        echo 'MySQL Error: ' . mysqli_error();
	        exit;
	    }
		
		if(!$row=mysqli_fetch_assoc($result)){
			//echo "login seccess!!! wellcome back".$row['username'];
			echo "Failed to Log In!! <br> Please check your Username and Password";
			echo"	<form action= login.php>
						<button type='submit' name'submit'>Back</button>
				</form>";
		}else{

			/*Globale variable called $_SESSION[] let will retrieve the id from accounts*/
			$_SESSION['user_id']=$row['user_id'];
			header('Location: index.php');
		}
	}

?>