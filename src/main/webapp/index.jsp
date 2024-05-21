<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>Welcome Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f2f5;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background-color: #fff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h1 {
            margin-bottom: 20px;
        }
        p {
            margin: 20px 0;
        }
        .button-container {
            display: flex;
            justify-content: center;
            gap: 20px;
        }
        button {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            background-color: #007bff;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
        a {
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Welcome to Our Site!</h1>
    <p class="button-container">
        <a href="login.html"><button>Login</button></a>
        <a href="registerDo.jsp"><button>Register</button></a>
    </p>
</div>
</body>
</html>
