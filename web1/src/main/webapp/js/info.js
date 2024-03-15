// submit 클릭시
// 값이 비어있지 않도록 처리

const form = document.querySelector("form");
const id = document.querySelector("#id");
const name = document.querySelector("#name");
const age = document.querySelector("#age");

form.addEventListener("submit", (e) => {
  e.preventDefault();

  if (!id.value) {
    alert("아이디를 확인해주세요");
    id.focus();
    return;
  } else if (!name.value) {
    alert("이름을 확인해주세요");
    name.focus();
    return;
  }

  if (!age.value) {
    alert("나이를 확인해주세요");
    age.focus();
    return;
  } else if (isNaN(age.value)) {
    alert("정수를 입력해주세요");
    age.focus();
    return;
  }

  form.submit();
});
