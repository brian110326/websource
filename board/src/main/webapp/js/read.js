document.querySelector("#list").addEventListener("click", (e) => {
  location.href = "/qList.do";
});

document.querySelector("#modify").addEventListener("click", (e) => {
  location.href = "/qModify.do?bno=" + bno;
  // bno는 view.jsp에서 const로 설정했기 때문에 쓸 수 있음
});

document.querySelector("#delete").addEventListener("click", (e) => {
  location.href = "/view/qna_board_pwdCheck.jsp?bno=" + bno;
});

document.querySelector("#reply").addEventListener("click", (e) => {
  location.href = "/qReplyView.do?bno=" + bno;
});
