function sendSms(phone) {

    let smsUrl = "https://sms.ru/sms/send";
    let apiId = "457A08D3-053A-85E3-8F57-F4560DD98DCE";
    let text = "На ваш аккаунт выполнен вход";
    let url = smsUrl + "?api_id=" + apiId + "to" + phone + "&msg=" + text + "&json=1"
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", url, false);
    xmlHttp.send(null);

    return xmlHttp.responseText;
}

function subscribe(id, url) {

    let xmlHttp = new XMLHttpRequest();
    xmlHttp.open("POST", url, false);
    xmlHttp.send(null);

    return xmlHttp.responseText;
}