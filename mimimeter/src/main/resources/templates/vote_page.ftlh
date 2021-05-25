<!DOCTYPE html>
<html lang="en">
<#import "spring.ftl" as spring>
<head>
    <meta charset="UTF-8">
    <title>Vote</title>
    <style>
        h1 {
            font-size: 125%;
            font-family: "Comic Sans MS", cursive;
        }
        .center {
            width: 600px;
            height: 600px;
            margin: auto;
        }
        .pair {
            display: inline;
            margin: auto;
        }
    </style>
    <link rel="stylesheet" href="../css/style.css" type="text/css">
    <base href="/">
</head>
<body>
<div class="center">
    <h1>Кто мимимишнее? Кликни по фотографии!</h1>
    <div class="pair">
        <form method="post" action="/vote/${cat1.id}">
            <p><input type="image" src="${cat1.path}" style="width:250px;height:250px"></p>
            <input type="submit" value="${cat1.name}">
        </form>
        <form method="post" action="/vote/${cat2.id}">
            <p><input type="image" src="${cat2.path}" style="width:250px;height:250px"></p>
            <input type="submit" value="${cat2.name}">
        </form>
    </div>
</div>
</body>
</html>