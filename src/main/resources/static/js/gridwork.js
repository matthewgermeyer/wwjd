document.querySelector('.add').addEventListener('click', function () {
  let node = document.querySelector('aside');
  node.innerHTML = node.innerHTML + "<div class='c'>X</div>";
  node.scrollTop = node.scrollHeight;
})
