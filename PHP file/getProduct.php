<?php
	include "connect.php";
	$query = "select * from sanpham" ;
	$data = mysqli_query($conn, $query);
	$mangsp= array();	
	while($row = mysqli_fetch_array($data))
	{
		$sp= new SanPham();
		$sp->id=$row['MaSP'];
		$sp->tensp= $row['TenSP'];
		$sp->price=$row['Gia'];
		$sp->image=$row['ImageLink'];
		// printf ("%s (%s)\n", $row["MaSP"], $row["TenSP"]);
		array_push($mangsp,$sp);
	}
	//echo $mangsp;
	echo json_encode(['SP' => $mangsp]);
	class SanPham 
	{
		 public $id;
		public $tensp;
		public $price;
		public $image;
	}
	
?>