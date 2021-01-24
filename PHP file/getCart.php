<?php
	include "connect.php";
	if(isset($_REQUEST))
	 {
		$username=$_REQUEST['TenDangNhap'];
	}
	//$username= "sal";
	$query = "select tongtien from Giohang where makh ='$username' " ;
	$data = mysqli_query($conn, $query);
	$mangsp= array();	
	while($row = mysqli_fetch_array($data))
	{
		$sp= new GioHang();
		$sp->makh=$username;
		$sp->tongtien= $row['tongtien'];
		array_push($mangsp,$sp);
	}
	//echo $mangsp;
	echo json_encode(['GioHang' => $mangsp]);
	class GioHang 
	{
		public $makh;
		public $tongtien;
	}
	
?>