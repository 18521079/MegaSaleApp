<?php
	include "connect.php";
	// getting id and name from the client
	/*if(isset($_REQUEST))
	 {
		$num=$_REQUEST['SoLuong'];
		$masp= $_REQUEST['MaSP'];
		$magh=$_REQUEST['MaGH'];
		}*/
		$num="5";
		$masp= "sp02";
		$magh="GH02";
  
	$sql = "insert into CTGioHang values('$magh','$masp','$num') ";

	if ($conn->query($sql) === TRUE) {
	  echo "Record updated successfully";
	} else {
	  echo "Error updating record: " . $conn->error;
	}

	$conn->close();
	//close
?>