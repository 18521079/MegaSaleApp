<?php
	include "connect.php";
	if(isset($_REQUEST))
	 {
		$username=$_REQUEST['TenDangNhap'];
	}
	//$username= "sal";
	$query = "select SP.masp,SP.tensp,CTGH.soluong,SP.gia, Sp.imageLink from (giohang GH inner join ctGiohang CTGH on CTGH.magiohang= GH.magiohang ) inner join sanpham SP on Sp.masp = CTGH.masp where GH.makh = '$username' " ;
	$data = mysqli_query($conn, $query);
	$mangsp= array();	
	while($row = mysqli_fetch_array($data))
	{
		$sp= new CTGioHang();
		$sp->masp=$row['masp'];
		$sp->tensp= $row['tensp'];
		$sp->soluong=$row['soluong'];
		$sp->price=$row['gia'];
		$sp->image=$row['imageLink'];
		// printf ("%s (%s)\n", $row["MaSP"], $row["TenSP"]);
		array_push($mangsp,$sp);
	}
	//echo $mangsp;
	echo json_encode(['CTGH' => $mangsp]);
	class CTGioHang 
	{
		public $masp;
		public $tensp;
		public $soluong;
		public $price;
		public $image;
	}
	
?>