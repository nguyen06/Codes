
<?php
//This file is only for chaecking connection if established between database and front end
$conn =mysqli_connect("localhost","root", "","blog");
if(!$conn){
	die("Connection Failed: ".mysqli_connect_error());
}