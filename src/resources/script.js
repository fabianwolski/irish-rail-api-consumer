document.addEventListener('DOMContentLoaded', function() {
  const counter = document.getElementById('counter');
  let seconds = 60;

  setInterval(function() {
    seconds--;
    if (seconds <= 0) {
      seconds = 60;
    }
    counter.textContent = seconds;
  }, 1000);
});