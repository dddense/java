<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="views/style.css">
    <title>${team_name}</title>
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
    <img src="img/teams/elche.png" alt="no img for ${team_name}">
</p>
<table>
    <tr>
        <th>Player</th>
        <th>Age</th>
        <th>Position</th>
    </tr>
    <#list players as player>
        <tr>
            <td>${player.name}</td>
            <td>${player.age}</td>
            <td>${player.pos}</td>
        </tr>
    </#list>
</table>
</body>
</html>