<?php

require("config.inc.php");

if (!empty($_POST)) {
    if (empty($_POST['message'])){
        
        $response["success"] = 0;
        $response["message"] = "Please Enter a message!";
        die(json_encode($response));
    }
    
    $query = "INSERT INTO contactmessages ( message, date) VALUES ( :message, :date) ";

    $query_params = array(
        ':message' => $_POST['message'],
        ':date' => date("Y-m-d H:i:s")
    );
    
    try {
        $stmt   = $db->prepare($query);
        $result = $stmt->execute($query_params);
    }
    catch (PDOException $ex) {
        $response["success"] = 0;
        $response["message"] = "Database Error2. Please Try Again!";
        die(json_encode($response));
    }
    
    $response["success"] = 1;
    $response["message"] = "Message Received!";
    echo json_encode($response);
    
} 
else {
?>
	<h1>Contact</h1> 
	<form action="contact.php" method="post"> 
	    contact:<br /> 
	    <input type="text" name="message" value="" /> 
	    <br /><br /> 
	    <input type="submit" value="submit" /> 
	</form>
	<?php
}

?>