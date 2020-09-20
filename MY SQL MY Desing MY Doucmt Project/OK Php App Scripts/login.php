<?php 
		$con=mysqli_connect("localhost","shayari1_shayaridata","Sherusukha@708","shayari1_shayaridata");
	
		$email = $_POST["email"];
		$password = $_POST["password"];

		$sql = "SELECT * FROM shayaridata WHERE  email = '$email' AND password = '$password'";
		$result = mysqli_query($con,$sql);
		
		if($result->num_rows > 0){
			echo "logged in successfully" ;
		}else{
  			 echo "user not found";
}
?>