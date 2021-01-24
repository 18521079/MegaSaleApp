<?php
	include "connect.php";
	if(isset($_REQUEST))
	 {
		$username=$_REQUEST['TenDangNhap'];
	}
	$username='sal';
	$query = "select SP.masp,SP.tensp,SP.gia, Sp.imageLink from sanphamyeuthich SPYT inner join sanpham SP on Sp.masp = SPYT.masp where SPYT.makh = '$username' " ;
	$data = mysqli_query($conn, $query);
	$mangsp= array();	
	while($row = mysqli_fetch_array($data))
	{
		$sp= new CTGioHang();
		$sp->masp=$row['masp'];
		$sp->tensp= $row['tensp'];
		$sp->price=$row['gia'];
		$sp->image=$row['imageLink'];
		array_push($mangsp,$sp);
	}
	//echo $mangsp;
	echo json_encode(['CTGH' => $mangsp]);
	class CTGioHang 
	{
		public $masp;
		public $tensp;
		public $price;
		public $image;
	}
	
?>