// session 저장 버튼 클릭 시 sessionSet.jsp이동
const btn1 = document.querySelector("button:first-child");
btn1.addEventListener("click", () => {
  location.href = "sessionSet.jsp";
});

const btn2 = document.querySelector("button:nth-child(2)");
btn2.addEventListener("click", () => {
  location.href = "sessionDel.jsp";
});

const btn3 = document.querySelector("button:nth-child(3)");
btn3.addEventListener("click", () => {
  location.href = "sessionInv.jsp";
});
