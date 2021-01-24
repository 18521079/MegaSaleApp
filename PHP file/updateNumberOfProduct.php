<?php
	include "connect.php";

	// getting id and name from the client
	/* if(isset($_REQUEST))
	 {
		$num=$_REQUEST['SoLuong'];
		$masp= $_REQUEST['MaSP'];
		}*/
	$num="30";
	$masp= "sp03";
   // variable used to tell the client whether data is stored in database or not
	$sql = "update CTGioHang set soluong='$num' where MaSP='$masp'";

	if ($conn->query($sql) === TRUE) {
	  echo "Record updated successfully";
	} else {
	  echo "Error updating record: " . $conn->error;
	}

	$conn->close();
?>