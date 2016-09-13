<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Login</title>
		<link rel="stylesheet" type="text/css" href="style.css">
	</head>
<body>
	
</body>

<header>
	<nav>
		<ul>
			<li><a href="index.php">HOME</a></li>
				<?php
					if(isset($_SESSION['user_id'])){
						echo"<form action = 'logout.php'>
								<button>LOG OUT</button>
							</form>";
					}else{
						echo"<form  action = 'process.php' method = 'POST'>
								<input type = 'text' name = 'user' placeholder='Username: ' />
								<input type = 'password' name = 'pass' placeholder='Password: '/>
								<button type='submit'>LOGIN</button>
							</form>";
						echo"<form action = '../default.php'>
								<button>HOME</button>
							</form>";

					}
				?>
				<li><a href="signup.php">SIGNUP</a></li>
		</ul>
	</nav>
</header>