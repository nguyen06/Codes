<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type = "text/css" href = "style.css">
  <title></title>
</head>
<body>

</body>
</html>
<?php
/*
Attempt MySQL server connection. Assuming you are running MySQL
server with default setting (user 'root' with no password)
*/
$link = mysqli_connect("localhost", "root", "", "blog");
 
// Check connection
if($link === false){
    die("ERROR: Could not connect. " . mysqli_connect_error());
}
 
// Escape user inputs for security
$title = mysqli_real_escape_string($link, $_POST['title']);
$desc = mysqli_real_escape_string($link, $_POST['description']);
//$email_address = mysqli_real_escape_string($link, $_POST['email']);
 
/* get the user_id from the user account and insert into the    
 * subsaidit because the subsaid must be belong to who create it 
 */ 
$user_name = "SELECT user_name FROM Accounts";

$retval = mysqli_query($link, $user_name);

if(! $retval ) {
      die('Could not get data: ' . mysqli_connect_error());
   }
   
$row = mysqli_fetch_assoc($retval);
$u_name = $row['user_name'];

// attempt insert query execution
$sql = "INSERT INTO Subsaiddits (sub_title, sub_description,sub_created_by) VALUES ('$title', '$desc','$u_name')";
if(mysqli_query($link, $sql)){
    echo "Records added successfully.";
} else{
    echo "ERROR: Could not able to execute $sql. " . mysqli_error($link);
}
 
 // back to the index page, then we can do display what we write 
 //header('location: index.php');
// close connection
//mysqli_close($link);

 $saiddit = "SELECT sub_title, sub_description, sub_created_time, sub_created_by FROM Subsaiddits";

 $saiddit_val = mysqli_query($link, $saiddit);

 if(! $saiddit_val ) {
      die('Could not get data: ' . mysql_error());
   }
  while($row = mysqli_fetch_assoc($saiddit_val)){
  	echo "Title : {$row['sub_title']} <br>".
  	     "Description : {$row['sub_description']} <br>".
  	     "created Time : {$row['sub_created_time']} <br>".
  	     "Created By : {$row['sub_created_by']} <br>".
  	     "-----------------------------------------<br>";
  }
?>