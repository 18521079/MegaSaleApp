<?php
	include "connect.php";
	//mysql_select_db($db,$con) or die("db selection failed");

	// getting id and name from the client
	 if(isset($_REQUEST))
	 {
		$username=$_REQUEST['TenDangNhap'];
		$password= $_REQUEST['MatKhau'];
	}
	//$username='sal';
	//$password='123';
	$query = "select * from khachhang where tendangnhap ='$username' and matkhau='$password' ";
	$data = mysqli_query($conn, $query);
	$mangsp= array();
	while($row = mysqli_fetch_assoc($data))
	{
		$sp= new KhachHang();
		$sp->username=$row['TenDangNhap'];
		$sp->password= $row['MatKhau'];
		$sp->name=$row['HoTen'];
		$sp->diachi=$row['DiaChi'];
		$sp->sdt=$row['SDT'];
		array_push($mangsp,$sp);
	}
	echo json_encode(['KhachHang' => $mangsp]);
	class KhachHang 
	{
		 public $username;
		public $password;
		public $name;
		public $diachi;
		public $sdt;
	}
	
?>