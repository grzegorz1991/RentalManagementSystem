function getRandomEndDate() {
    var currentDate = new Date();
    var randomOffset = Math.floor(Math.random() * (14 - 7 + 1)) + 7; // Random number between 7 and 14
    var endDate = new Date(currentDate.getTime() + randomOffset * 24 * 60 * 60 * 1000);
    return endDate;
}

function updateTimer() {
    var now = new Date();
    var endDate = new Date(localStorage.getItem('endDate'));
    var timeDiff = endDate - now;

    if (timeDiff <= 0) {
        // Timer has ended, you can handle this case if needed
        clearInterval(timerInterval);
        return;
    }

    var days = Math.floor(timeDiff / (1000 * 60 * 60 * 24));
    var hours = Math.floor((timeDiff % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    var minutes = Math.floor((timeDiff % (1000 * 60 * 60)) / (1000 * 60));
    var seconds = Math.floor((timeDiff % (1000 * 60)) / 1000);

    // Update the HTML elements with the new values
    document.getElementById("days").textContent = days < 10 ? '0' + days : days;
    document.getElementById("hours").textContent = hours < 10 ? '0' + hours : hours;
    document.getElementById("minutes").textContent = minutes < 10 ? '0' + minutes : minutes;
    document.getElementById("seconds").textContent = seconds < 10 ? '0' + seconds : seconds;
}

// Generate random end date and store it in local storage
var endDate = getRandomEndDate();
localStorage.setItem('endDate', endDate);

// Update the timer every second
var timerInterval = setInterval(updateTimer, 1000);

// Initialize the timer immediately
updateTimer();