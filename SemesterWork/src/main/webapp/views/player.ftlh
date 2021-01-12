<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="views/style.css">
    <title>${player.name}</title>
    <style>
        body {
            background: url(../img/1x.jpg) no-repeat;
            background-size: 100%;
        }
    </style>
</head>
<body>
<#include "cap.html">
<p>
    <img src="img/players/${player.name}.png" alt="no img for ${player.name}">
</p>
<table>
    <tr>
        <th>Name</th>
        <th>Age</th>
        <th>Position</th>
    </tr>
        <tr>
            <td>${player.name}</td>
            <td>${player.age}</td>
            <td>${player.pos}</td>
        </tr>
</table>
</body>
</html>