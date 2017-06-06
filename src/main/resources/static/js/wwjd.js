$( document ).ready(function() {
  $('#playId').click(function() {

    var fileName;
    var buttonText = $(this).text().toLowerCase()
    if (buttonText.indexOf("blues")!= -1){
        fileName = "blues.wav"
    } else if (buttonText.indexOf("pop")!= -1) {
        fileName = "popRock.wav"
    } else if (buttonText.indexOf("soulful")!= -1) {
        fileName = "soulful.wav"
    } else {
        fileName = "simpleRock.wav"
    }


      var audio = new Audio('http://localhost:8080/' + fileName);
        audio.play();
  });
});




