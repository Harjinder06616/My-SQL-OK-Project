<?php 
	$con=mysqli_connect("localhost","shayari1_shayaridata","Sherusukha@708","shayari1_shayaridata");
	
	$fullname = $_POST["fullname"];
	$email = $_POST["email"];
	$password =$_POST["password"];
    $confpassword =$_POST["confpassword"];
    
	$sql = "INSERT INTO shayaridata(fullname,email,password,confpassword) VALUES ('$fullname','$email','$password','$confpassword')";
	$result = mysqli_query( $con,$sql );
	
	if($result) {
		echo "registered successfully";
	}
	else {
		echo "some error occured";
	}
?>