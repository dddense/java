<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Отправка файла на сервер</title>
</head>
<body>
<form enctype="multipart/form-data" method="post" action="/cats/add">
    <p><input type="file" name="image">
        <input type="text" name="cat">
        <input type="submit" value="Добавить">
    </p>
</form>
</body>
</html>