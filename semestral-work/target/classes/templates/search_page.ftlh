<html>
<head>
    <title>Users</title>
    <script src="https://code.jquery.com/jquery-3.6.0.js"
            integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
            crossorigin="anonymous">
    </script>
    <link rel="stylesheet" , href="../css/style.css">
</head>
<body>
<h1>Users</h1>
<script>
    function renderTable(users, table) {
        let innerHtml = '<tr>\n' +
            '    <th>Username</th>\n' +
            '</tr>';
        for (let i = 0; i < users.length; i++) {
            innerHtml += '<tr>';
            innerHtml += '<td><p><a href=/profile/' + users[i]['id'] + '>' + users[i]['username'] + '</a></p></td>';
            innerHtml += '</tr>'
        }

        table.html(innerHtml);
    }

    function search(username) {
            $.ajax({
                type: "GET",
                url: "/users/search",
                success: function (response) {
                    renderTable(response.users, $('#table'))
                },
                error: function (response) {
                    console.log(response)
                    console.log("error!")
                },
                dataType: "json",
                data: {
                    page: 0,
                    size: 100,
                    q: username
                }
            }
        )
    }
</script>
<div>
    <input type="text" id="username">
    <button onclick="search($('#username').val())">Search</button>
</div>
<div>
    <table id="table">
    </table>
</div>
</body>
</html>
