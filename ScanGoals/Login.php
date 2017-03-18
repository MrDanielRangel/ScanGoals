<?php
    //storing connection to a variable
    $con = mysqli_connect("localhost", "camelbac_goals", "santos13", "camelbac_scangoals");

    $username = $_POST["username"];
    $password = $_POST["password"];

    //selects username and password from database
    $statement = mysqli_prepare($con, "SELECT * FROM users WHERE username = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $username, $password); //binds parameters
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userID, $username, $password);

    $response = array();
    $response["success"] = false;

    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;
        $response["username"] = $username;
        $response["password"] = $password;
    }

    //json formatt
    echo json_encode($response);
?>
