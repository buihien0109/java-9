// Truy cập thông qua id
const heading = document.getElementById("heading");
console.log(heading);

// Truy cập thông qua tag name
const parasTagName = document.getElementsByTagName("p");
console.log(parasTagName);

// Duyệt HTMLCollection với for
for (let i = 0; i < parasTagName.length; i++) {
    console.log(parasTagName[i]);
}

// Duyệt HTMLCollection với map
Array.from(parasTagName).map(ele => console.log(ele));

// Truy cập thông qua class name
const parasClassName = document.getElementsByClassName("para");
console.log(parasClassName);

// Truy cập thông qua css selector
const para1 = document.querySelector("p");
console.log(para1);

const para2 = document.querySelector(".para");
console.log(para2);

// p.para:nth-child(4)
const para3 = document.querySelector(".para + .para");
console.log(para3);

// Truy cập vào ul số 2
// div + ul
const ul2 = document.querySelector("body > ul");
console.log(ul2);

const ul1 = document.querySelector(".box ul")
console.log(ul1)

const parasSelectorAll = document.querySelectorAll("p");
console.log(parasSelectorAll);

// Thay đổi CSS
heading.style.color = "red";
heading.style.backgroundColor = "black";

// Lấy ra nội dung của phần tử (getter)
console.log(heading.innerHTML);
console.log(heading.innerText);
console.log(heading.textContent);

console.log(ul1.innerHTML);
console.log(ul1.innerText);
console.log(ul1.textContent);

heading.innerHTML = "Xin chào các bạn";
heading.innerHTML = "<span>Hello...</span>";

para1.innerText = "Đây là nội dung của thẻ para 1";
para1.innerText = "<span>Đây là nội dung của thẻ para 1</span>";

// Truy cập gián tiếp
const box = document.querySelector(".box");
console.log(box.parentElement);
console.log(box.previousElementSibling);
console.log(box.nextElementSibling);
console.log(box.children);

// Thêm phần tử vào dom
// document.body.prepend(para1);
// document.body.appendChild(para1);
// document.body.insertBefore(para1, box);

// Tạo phần tử dom
const link = document.createElement("a");
link.innerText = "Link google";
link.href = "https://google.com";

console.log(link);

document.body.insertBefore(link, ul2);

const li3 = document.querySelector("body > ul li:nth-child(3)");
console.log(li3);

// Thêm phần tử
// box.insertAdjacentElement("beforeBegin", para1);
// para3.insertAdjacentElement("afterend", para1)

heading.insertAdjacentHTML("afterend", "<button>Click me</button>");

box.insertAdjacentHTML("afterbegin", "<p>Nội dung mới</p>");

// Xóa phần tử
// document.body.removeChild(para1);
para1.parentElement.removeChild(para1);

// Thay thế
const input = document.createElement("input");
input.placeholder = "Nhập nội dung";

document.body.replaceChild(input, para2)

// Copy
const boxCopyTrue = box.cloneNode(true);
const boxCopyFalse = box.cloneNode(false);

console.log(boxCopyTrue);
console.log(boxCopyFalse);

document.body.appendChild(boxCopyTrue);

