// submit이 일어나면
// submit 기능 중지
// code, title, writer, price 비어있는지 확인
// code가 4자리인지 확인
// 값이 존재한다면 submit

const code = document.querySelector("#code");
const title = document.querySelector("#title");
const writer = document.querySelector("#writer");
const price = document.querySelector("#price");

const form = document.querySelector("form");

form.addEventListener("submit", (e) => {
  e.preventDefault();

  if (!code.value || code.value.length != 4 || isNaN(code.value)) {
    alert("code값이 공란 혹은 숫자 4자리가 아닙니다");
    code.focus();
    return;
  } else if (!title.value) {
    alert("title값이 공란");
    title.focus();
    return;
  } else if (!writer.value) {
    alert("writer값이 공란");
    writer.focus();
    return;
  } else if (!price.value || isNaN(price.value)) {
    alert("price값이 공란 혹은 숫자가 아닙니다");
    price.focus();
    return;
  }

  form.submit();
});
