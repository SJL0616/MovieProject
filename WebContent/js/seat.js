/*인원수 체크 */
const buttonUp = document.querySelector(".up");
const buttonDown = document.querySelector(".down");
// 초기화 버튼
const buttons = document.querySelector(".buttons");
const btnPay = document.querySelector(".btn-group > .button");
buttons.addEventListener("click", () => {
  alert("모두 초기화하였습니다.");
  seatReset();
});
// 초기화버튼 및 인원수 0까지 내릴때
function seatReset() {
  let number = document.querySelector(".number > p");
  let money = document.querySelector(".money > em");
  let count = document.querySelector(".count > span > em");

  number.textContent = "0";
  money.textContent = "0";
  count.textContent = "0";
  //모든좌석 초기화
  let seatList = [...document.querySelectorAll(".on")];
  seatList.forEach((s) => {
    s.style.backgroundColor = "#747474";
    s.classList.remove("on");
  });
  //선택좌석 초기화
  let choiceList = [...document.querySelectorAll(".seats")];
  choiceList.forEach((ch) => {
    ch.classList.remove("choice");
    ch.classList.add("all");
    ch.innerHTML = "-";
  });

  btnPay.classList.remove("active");
  btnPay.classList.add("disabled");
  btnPay.style.cursor = "none";
}
//button UP 했을때
buttonUp.addEventListener("click", () => {
  let number = document.querySelector(".number > p");
  if (number.textContent == "8") {
    return;
  }
  let num = parseInt(number.textContent);
  num += 1;
  number.innerHTML = num;
  let seatList = [...document.querySelectorAll(".seat")];
  seatList.forEach((s) => {
    if (s.getAttribute("data-true") == "true") {
      s.innerHTML = "X";
    }
    s.classList.add("on");
  });
});
// button Down 했을때
buttonDown.addEventListener("click", () => {
  let number = document.querySelector(".number > p");
  if (number.textContent == "0") {
    return;
  }
  let num = parseInt(number.textContent);
  num -= 1;
  number.textContent = num;
  if (number.textContent == "0") {
    seatReset();
  }
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
    //알파벳
    if (j == 0) {
      sortMapping(button, i, j);
      button.classList = "seatline";
      button.style = "margin-right: 30px";
      continue;
    }

    if (j == 3) {
      button.style = "margin-left: 30px";
    }

    if (j == 6) {
      button.style = "margin-left: 30px";
    }
    seatChoice(button);
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
// button 이벤트 seat
function seatChoice(button) {
  button.addEventListener("click", () => {
    // 총인원수, 돈, 구매할 인원수
    let number = document.querySelector(".number > p");
    let money = document.querySelector(".money > em");
    let count = document.querySelector(".count > span > em");

    if (number.textContent == "0") {
      return;
    }
    if (number.textContent == count.textContent) {
      return;
    }
    console.log(button);

    let seats = document.querySelector(".all");
    seats.classList.remove("all");
    seats.classList.add("choice");
    // 돈증가
    if (money.textContent != "") {
      let num = parseInt(money.textContent);
      num += 7000;
      money.textContent = num;
    } else {
      money.textContent = 7000;
    }
    //인원수 증가
    if (count.textContent != "") {
      let num = parseInt(count.textContent);
      num += 1;
      count.textContent = num;
    } else {
      count.textContent = 1;
    }
    seats.innerHTML = button.value + button.innerHTML;
    button.style.backgroundColor = "#503396";

    btnPay.classList.remove("disabled");
    btnPay.classList.add("active");
    btnPay.style.cursor = "pointer";
  });
}
