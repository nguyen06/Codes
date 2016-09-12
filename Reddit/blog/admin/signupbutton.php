<?php
	session_start();
	include 'dbh.php';
	$uid=$_POST['user_id'];
	$pwd=$_POST['pwd'];
	if($uid=='' OR $pwd==''){
		echo"Reuired  field must be filled in.";
	}

	$encrypted_password=password_hash($pwd, PASSWORD_DEFAULT);

	$sql = "INSERT INTO user(username,password)
	 VALUES ('$uid','$encrypted_password')";
	$result=mysqli_query($conn,$sql);

	if(!$result){
		echo "Signed up unsucessful! <br>
				Username is used by other account. ";
		echo"	<form action= signup.php>
					<button type='submit' name'submit'>Back</button>
			</form>";
	}else{
		header("Location: login.php");
		echo "Signed Up sucessfull !";
	}
?>

