<?php
require("config.inc.php");
// pulls user information from database based on email stored in current session
    $tempprofile = "test@uconn.edu";
    $query = "SELECT id,email,username,bio,karma,posted_jobs,accepted_jobs FROM users WHERE email = :email";
    $query_params = array(':email' => $_GET["email"]);
    
    try {
        // These two statements run the query against your database table. 
        $stmt   = $db->prepare($query);
        $result = $stmt->execute($query_params);
    }
    catch (PDOException $ex) {
        // For testing, you could use a die and message. 
        //die("Failed to run query: " . $ex->getMessage());
        
        //or just use this use this one to product JSON data:
        $response["success"] = 0;
        $response["message"] = "Database Error1. Please Try Again!";
        die(json_encode($response));
    }
    
    $row = $stmt->fetch();
    
    echo json_encode($row);

?>