const para = document.querySelector("p");

// Lấy ra nội dung của thẻ p
let content = para.innerText;

// Convert sang mảng
let words = content.split(" ");

// 1. Highlight tất cả các từ có độ dài lớn hơn hoặc bằng 8 ký tự trong đoạn văn (background = “yellow”)

for (let i = 0; i < words.length; i++) {
    // words[i] : "restricted" -> <span style="background-color:yellow">restricted</span>
    if(words[i].length >= 8) {
        words[i] = `<span style="background-color:yellow">${words[i]}</span>`
    }
}

para.innerHTML = words.join(" "); // Convert arr -> string. Sau đó insert lại

// 2. Thêm link hiển thị text “facebook” link đến trang facebook.com ở sau thẻ p
para.insertAdjacentHTML("afterend", `<a href="https://facebook.com">Facebook</a>`);

// 3. Đếm số từ có trong đoạn văn. Tạo 1 thẻ div để hiển thị số từ

// Tạo thẻ div và chèn số từ (độ dài của mảng)
document.body.insertAdjacentHTML("afterbegin", `<div>Số từ : ${words.length}</div>`)

// 4. Thay thế ký hiệu ? => 🤔, ! => 😲
para.innerHTML = para.innerHTML.replaceAll("?", "🤔");
para.innerHTML = para.innerHTML.replaceAll("!", "😲");