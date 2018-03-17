<?php
	$user="root";
	$host="localhost";
	$pass="";
	//$db="andi";

	$conn=mysqli_connect($host,$user,$pass);
	if($conn){
		//echo "Connection is Success!";
		$q1="use andi";
		if(mysqli_query($conn,$q1)){
			//echo "database is changed!!";

			createtable();
		}else{
			//echo "database is not exisit!!";
			$q2="create database andi";
			if(mysqli_query($conn,$q2)){
				//echo "database is created!!";
				if(mysqli_query($conn,$q1)){
					//echo "database is changed!!!";
				}
				createtable();
			}else{
				//echo "Cannot create database!!".mysqli_error($conn);
			}
		}

	}else{
		//echo "Connection failed!!!".mysqli_error($conn);
	}

	 function createtable ()
	{

		$q3="select * from emp";
		if(mysqli_query($GLOBALS['conn'],$q3)){
				//echo "Table is exisit!!";
		}else{
			//echo "table does not exist";
			$q4="create table emp(id int(100) primary key auto_increment,name char(50),surname char(60),age int(20),username char(60),password char(20))";
			if(mysqli_query($GLOBALS['conn'],$q4)){
				//echo "table is created!!";
				$q5="insert into emp(name,surname,age,username,password) values('charith','liyanage',23,'charith','liyanage')";
				$q6="insert into emp(name,surname,age,username,password) values('udara','maalage',22,'udara','123')";
				mysqli_query($GLOBALS['conn'],$q5);
				mysqli_query($GLOBALS['conn'],$q6);

			}else{
				//echo "Cannot create table!!".mysqli_error($GLOBALS['conn']);
			}
		}
	}

?>