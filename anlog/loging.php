<?php
	require ('db.php');
	$user=$_POST['username'];
	$pass=$_POST['password'];

	//$user="charith";
	//$pass="liyanagec";
	$q1="select * from emp";
	$r1=mysqli_query($conn,$q1);
	$check=0;
	while ($row1=mysqli_fetch_assoc($r1)){
		if(($row1['username']==$user)&&($row1['password']==$pass)){
			$check=1;
			break;
		}
		
	}
	if($check==1){
		//echo "Loging Success!!!";
		$arr=array("log_status"=>"Loging Success!!");
		$job=json_encode($arr);
		echo $job;
	}else{
		//echo "Invalid username and Password!!";
		$arr=array("log_status_2"=>"Loging failed!!");
		$job=json_encode($arr);
		echo $job;
	}
?>