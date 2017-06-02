$( document ).ready(function() {
  $('#addJimiId').click(function(){
    $('#formId').attr('action', "/jimi").submit();
  });
});