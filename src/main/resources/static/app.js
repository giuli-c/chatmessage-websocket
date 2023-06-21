var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#output").html("");
}

function connect() {
    var socket = new SockJS('/chatmessage/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/output', function (message) {
            showMessage(JSON.parse(message.body).content);
        });      
    }, function (err) {
       alert('error' + err);           
    });
}

function disconnect() {
    if (stompClient !== null) {
		
        stompClient.disconnect( function (){
			console.log("Disconnected");
			setConnected(false);
		});
    }
    
}

function sendMessage() {
	//'textMex' fa riferimento alla variabile definita nella classe "MessageText"
	// utilizzata poi dal controllo e restituito come nuova istanza 
	// alla risposta del server
	var userName = $("#name").val()
	var message = $("#message").val()
	
	stompClient.send("/app/input", {}, JSON.stringify({'name' : userName, 'textMex' : message}));
}

function showMessage(name) {
    $("#output").append("<tr><td>" + name + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMessage(); });
});