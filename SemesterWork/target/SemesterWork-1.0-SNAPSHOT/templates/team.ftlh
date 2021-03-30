<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../static/css/style.css">
    <title>${team_name}</title>
</head>
<body>
<!--#include "../static/html/cap.html"-->
<div class="content">
    <p>
        <img src="img/teams/${team_name}.png" alt="no img for ${team_name}">
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
</div>
</body>
</html>