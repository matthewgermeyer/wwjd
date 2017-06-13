$( document ).ready(function() {
  $('#playId').click(function() {

    var fileName = $(this).attr("key");
    var buttonText = $(this).text().toLowerCase()
    if (buttonText.indexOf("blues")!= -1){
        fileName += "blues.wav"
    } else if (buttonText.indexOf("pop")!= -1) {
        fileName += "popRock.wav"
    } else if (buttonText.indexOf("soulful")!= -1) {
        fileName += "soulful.wav"
    } else {
        fileName += "simpleRock.wav"
    }
    var href = window.location.href
    href = href.split("/")
    var url = href[0] + "//" + href[2] + "/wav/" + fileName

    var audio = new Audio(url);
    audio.play();
  });
});




