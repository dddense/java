<#ftl encoding='UTF-8'>
<html lang="en">
<body>
<form method="${method}" action="${action}">
    <#list entities as entity>
        <input type="${entity.type}" name="${entity.name}" placeholder="${entity.placeholder}">
    </#list>
</form>
</body>
</html>
