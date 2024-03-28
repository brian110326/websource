// 검색 폼
// criteria, keyword 비어있는지 확인
const criteria = document.querySelector('[name="criteria"]');
const keyword = document.querySelector('[name="keyword"]');

document.querySelector('[name="search"]').addEventListener("submit", (e) => {
  e.preventDefault();

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
