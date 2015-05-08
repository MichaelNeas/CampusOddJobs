<?php
$servername = "localhost";
$username = "ejh12002_joey";
$password = "+2g#-]e{CWdZ";
$dbname = "ejh12002_oddjobs";


if (!empty($_POST)) {
    if (empty($_POST['id']) || empty($_POST['bio']) || empty($_POST['username']) || empty($_POST['email'])){
        
        $response["success"] = 0;
        $response["message"] = "Please Enter Stuff!";
        die(json_encode($response));
    }
    // Create connection
    $conn = new mysqli($servername, $username, $password, $dbname);
    
    // Check connection
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }
     
    try{
      
    $int = filter_input(INPUT_POST, 'id', FILTER_VALIDATE_INT);
    $newemail = $_POST['email'];
    $newname = $_POST['username'];
    $newbio = $_POST['bio'];
    
    //echo $int;
    //echo $newemail;
    //echo $newname;
    //echo $newbio;
    $sql = "UPDATE users SET bio='".$newbio."', email='".$newemail."', username='".$newname."' WHERE id=$int";
    
    if ($conn->query($sql) === TRUE) {
         $response["success"] = 1;
    	 $response["message"] = "Record updated successfully";
    	 echo json_encode($response);
    	
    	 
    } else {
         echo "Error updating record: " . $conn->error;
    }
    }
    catch(PDOException $ex) {
        $response["success"] = 0;
        $response["message"] = "Database Error2. Please Try Again!";
        die(json_encode($response));
    }


     $conn->close();
} 
else {
?>
	<h1>Change Info</h1> 
	<form action="infochange.php" method="post"> 
	    ID:<br />
	  <input type="text" name="id" value="" /> 
	    <br /><br /> 
           
            Email:<br /> 
	    <input type="text" name="email" value="" /> 
	    <br /><br /> 
	    
	    Username:<br /> 
	    <input type="text" name="username" value="" /> 
	    <br /><br /> 
	    
	    Bio:<br /> 
	    <input type="text" name="bio" value="" /> 
	    <br /><br /> 
	    
	    <input type="submit" value="submit" /> 
	</form>
	<?php
}

?>