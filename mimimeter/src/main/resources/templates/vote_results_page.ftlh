<!DOCTYPE html>
<html lang="en">
<#import "spring.ftl" as spring>
<head>
    <meta charset="UTF-8">
    <title>Results</title>
    <style>
        ol, li {
            font-size: 125%;
            font-family: "Comic Sans MS", cursive;
        }

        h1 {
            font-family: "Comic Sans MS", cursive;
        }

        .cat {
            width: 400px;
            height: 325px;
        }

        .center {
            width: 400px;
            margin: auto;
        }
    </style>
    <link rel="stylesheet" href="../css/style.css" , type="text/css">
    <base href="/">
</head>
<body>
<div class="center" style="text-align: center">
    <h1 style="font-size: 300%">Топ 10</h1>
    <ol><#list cats as cat>
            <h1>
                <li>
                    <div class="cat">
                        <img src="${cat.path}" style="width:250px;height:250px" alt="image">
                        <b style="padding-top: 200px;">${cat.name}</b>
                    </div>
                </li>
            </h1>
        </#list>
    </ol>
</div>
</body>
</html>