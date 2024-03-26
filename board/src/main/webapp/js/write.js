// form이 비어있는지 확인
document.querySelector("form").addEventListener("submit", (e) => {
  e.preventDefault();
  const name = document.querySelector("#name");
  const title = document.querySelector("#title");
  const content = document.querySelector("#content");
  const password = document.querySelector("#password");

  if (!name.value) {
    alert("이름을 입력해주세요");
    name.focus();
    return;
  } else if (!title.value) {
    alert("제목을 입력해주세요");
    title.focus();
    return;
  } else if (!content.value) {
    alert("내용을 입력해주세요");
    content.focus();
    return;
  } else if (!password.value) {
    alert("비밀번호를 입력해주세요");
    password.focus();
    return;
  }

  e.target.submit();
});

document.querySelector("#list").addEventListener("click", (e) => {
  location.href = "/qList.do";
});
