var stompClient = null;
var $loading = $('#loadingDiv').hide();
$(document)
  .ajaxStart(function () {
    $loading.show();
  })
  .ajaxStop(function () {
    $loading.hide();
  });

function connect() {
    var socket = new SockJS('/architect-example');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {

        stompClient.subscribe('/topic/message', function (greeting) {
            console.log(greeting.body)
            $('.response').append('<br>'+greeting.body+'</br>');
        });
    });
}
connect();


$( "#form" ).submit(function( event ) {

  event.preventDefault();
    var data = { orderName: $("#Name").val(), orderMessage: $("#Order").val() };
  $.ajax({
    method: "POST",
    contentType:"application/json; charset=utf-8",
    dataType:"json",
    url: "/localOrderUp",
    data: JSON.stringify(data)
  })
    .done(function( msg ) {
    		$('.response').append('<br>'+msg+'</br>');
      
    }).fail(function( jqXHR, textStatus ) {
    	  $('.response').append('<p>'+jqXHR.responseText+'</p>');
    });
});
