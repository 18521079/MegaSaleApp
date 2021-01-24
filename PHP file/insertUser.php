<?php
	include "connect.php";
	mysql_select_db($db,$con) or die("db selection failed");

	// getting id and name from the client
	 if(isset($_REQUEST))
	 {
		$username=$_REQUEST['TenDangNhap'];
		$password= $_REQUEST['MatKhau'];
		$name=$_REQUEST['HoTen'];
		$diachi=$_REQUEST['DiaChi'];
		$sdt=$_REQUEST['SDT'];
		}
   // variable used to tell the client whether data is stored in database or not

	$flag['code']=0;

	// for insertion
	if($r=mysql_query("insert into khachhang values('$username','$password','$name','$diachi','$sdt') ",$con))
	{
		//if insertion succeed set code to 1
		$flag['code']=1;
		echo"hi";
	}
	// send result to client that will be 1 or 0
	print(json_encode($flag));

	//close
	mysql_close($con);
?>