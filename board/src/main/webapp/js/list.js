// page 영역 가져오기
const pagination = document.querySelector(".pagination");
const actionForm = document.querySelector("#actionForm");
console.log(pagination);

// 제목 클릭시 a태그 기능중지
// href값 가져오기
// actionForm 안에 bno hidden 태그 추가
// actionForm.action = "/qCount.do"

const moves = document.querySelectorAll(".move");
moves.forEach((move) => {
  move.addEventListener("click", (e) => {
    e.preventDefault();

    const href = e.target.getAttribute("href");
    console.log(href);

    const element = `<input type="hidden" name="bno" value="${href}">`;
    actionForm.insertAdjacentHTML("beforeend", element);

    actionForm.action = "/qCount.do";

    console.log(actionForm);
    // actionForm.submit();
  });
});

// 하단의 번호를 클릭하면 a태그의 기능을 중지
// a 태그의 href 속성값을 가져오기
// 가져온 값을 actionForm의 page value값으로 변경
pagination.addEventListener("click", (e) => {
  e.preventDefault();

  // 속성값 가져오기
  console.log(e.target.href);
  console.log(e.target.getAttribute("href"));

  // ex) img.src or img.getAttribute("src")

  const href = e.target.getAttribute("href");

  actionForm.querySelector("[name='page']").value = href;

  console.log(actionForm);
  actionForm.submit();
});

// 사용자가 목록 개수 변경 시 목록값을 가져온 후 actionForm의 amount 값 변경 후 actionForm 전송
const select = document.querySelector("select[name='amount']");
select.addEventListener("change", (e) => {
  e.preventDefault();

  actionForm.querySelector("[name='amount']").value = e.target.value;

  actionForm.submit();
});

// 검색 폼
// criteria, keyword 비어있는지 확인

document.querySelector('[name="search"]').addEventListener("submit", (e) => {
  e.preventDefault();

  // 검색하는 경우네는 첫 페이지로 보여주기
  // ex) 현재 10page에 있는데 검색을 하면 1페이지로 보여줘야함
  const criteria = document.querySelector('[name="criteria"]');
  const keyword = document.querySelector('[name="keyword"]');
  document.querySelector('[name="page"]').value = 1;

  if (criteria.value == "n") {
    alert("검색 조건을 선택해주세요");
    criteria.focus();
    return;
  } else if (!keyword.value) {
    alert("검색어를 입력하세요");
    keyword.focus();
    return;
  }

  e.target.submit();
});
