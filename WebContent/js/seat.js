/**
 * 
 */

 /*인원수 체크 */
const buttonUp = [...document.querySelectorAll(".up")];
const buttonDown = [...document.querySelectorAll(".down")];

buttonUp.forEach((button) => {
  button.addEventListener("click", () => {});
});

/* 좌석 뽑아오기 */
let selectedSeats = new Array();
const seatWrapper = document.querySelector(".seat-wrapper");
let clicked = "";
let div = "";
for (let i = 0; i < 7; i += 1) {
  div = document.createElement("div");
  seatWrapper.append(div);
  for (let j = 0; j <= 7; j += 1) {
    const button = document.createElement("button");
    button.name = "seats";
    button.classList = "seat";
    div.append(button);
    mapping(button, i, j);
    if (j == 0) {
      sortMapping(button, i, j);
      button.style = "margin-right: 30px";
    }
    if (j == 3) {
      button.style = "margin-left: 30px";
    }

    if (j == 6) {
      button.style = "margin-left: 30px";
    }
  }
}
function mapping(button, i, j) {
  if (i == 0) {
    button.value = "A";
    button.innerHTML = j;
  } else if (i == 1) {
    button.value = "B";
    button.innerHTML = j;
  } else if (i == 2) {
    button.value = "C";
    button.innerHTML = j;
  } else if (i == 3) {
    button.value = "D";
    button.innerHTML = j;
  } else if (i == 4) {
    button.value = "E";
    button.innerHTML = j;
  } else if (i == 5) {
    button.value = "F";
    button.innerHTML = j;
  } else if (i == 6) {
    button.value = "G";
    button.innerHTML = j;
  }
}
function sortMapping(button, i, j) {
  if (i == 0) {
    button.value = "A";
    button.innerHTML = "A";
  } else if (i == 1) {
    button.value = "B";
    button.innerHTML = "B";
  } else if (i == 2) {
    button.value = "C";
    button.innerHTML = "C";
  } else if (i == 3) {
    button.value = "D";
    button.innerHTML = "D";
  } else if (i == 4) {
    button.value = "E";
    button.innerHTML = "E";
  } else if (i == 5) {
    button.value = "F";
    button.innerHTML = "F";
  } else if (i == 6) {
    button.value = "G";
    button.innerHTML = "G";
  }
}
