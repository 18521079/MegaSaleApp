<?php
	$host = "localhost";
	$username= "root";
	$password="";
	$database ="qlbh";	
	$conn = mysqli_connect($host, $username, $password, $database );
	mysqli_query($conn, "SET NAME 'utf8'");
	/*if($conn)
	{
		echo "Ket noi thanh cong";
	}
	else 
	{
		echo "ket noi that bai";
	}*/
?>