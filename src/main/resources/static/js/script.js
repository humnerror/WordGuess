window.onload = function GameOver() {
    if ([[${remainingTry}]] <= 0) {
        alert("Game Over!")
        document.getElementById("guessChar").disabled = true;
        document.getElementById("try").disabled = true;
    }
}

function GameWin() {
    if ([[${winOrLose}]] === true) {
        alert("GameWon")
    }
}

function playAgain() {
    window.location.href = "http://localhost:8080/playAgain"
}