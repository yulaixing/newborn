<!DOCTYPE html>
<html>
<head>
    <title>socket.html</title>

    <meta name="keywords" content="keyword1,keyword2,keyword3">
    <meta name="description" content="this is my page">
    <meta name="content-type" content="text/html" charset="UTF-8">
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->

</head>

<body>

Welcome<br/>
<input id="text" type="text" /><button onclick="send()">Send</button>    <button onclick="closeWebSocket()">Close</button>
<div id="message">
</div>
<!-- 公共JS -->
<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>


<script type="text/javascript">
    var userID="888";
    var websocket=null;
    $(function() {

        //创建WebSocket
        connectWebSocket();
    })

    //强制关闭浏览器  调用websocket.close（）,进行正常关闭
    window.onunload = function() {

        //关闭连接
        closeWebSocket();
    }

    //建立WebSocket连接
    function connectWebSocket(){

        console.log("开始...");

        //建立webSocket连接
        websocket = new WebSocket("ws://127.0.0.1:8080/myHandler/ID="+userID);

        //打开webSokcet连接时，回调该函数
        websocket.onopen = function () {
            console.log("onpen");
        }

        //关闭webSocket连接时，回调该函数
        websocket.onclose = function () {
            //关闭连接
            console.log("onclose");
        }

        //接收信息
        websocket.onmessage = function (msg) {
            console.log(msg.data);
        }
    }

    //发送消息
    function send(){
        var postValue={};
        postValue.id=userID;
        postValue.message=$("#text").val();
        websocket.send(JSON.stringify(postValue));
    }
    //关闭连接
    function closeWebSocket(){
        if(websocket != null) {
            websocket.close();
        }
    }

</script>
</body>
</html>