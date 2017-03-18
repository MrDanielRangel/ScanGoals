<?php
    $con = mysqli_connect("localhost", "camelbac_goals", "santos13", "camelbac_scangoals");
    
    $username = $_POST["username"];
    $password = $_POST["password"];

    //insert username and password into database
    $statement = mysqli_prepare($con, "INSERT INTO users (username, password) VALUES (?, ?, ?)");
    mysqli_stmt_bind_param($statement, "ss", $username, $password);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>
