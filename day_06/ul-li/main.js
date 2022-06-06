// 6. Thêm 1 nút “add” + 1 ô input để nhập text. Mỗi khi bấm nút thêm 1 thẻ <li> có nội dung là nội dung trong ô input vào cuối danh sách
const ulEl = document.querySelector("#list");
const inputEl = document.querySelector("input");
const btnAdd = document.querySelector(".btn-add");
const btnRemove = document.querySelector(".btn-remove");
const btnHide = document.querySelector(".btn-hide");

btnAdd.addEventListener("click", function() {
    // B1 : Lấy nội dung trong ô input
    let value = inputEl.value;

    // B2 : Kiểm tra nội dung
    if(value == "") {
        alert("Nội dung không được để trống");
        return;
    }

    // B3 : Insert vào ul
    ulEl.insertAdjacentHTML("beforeend", `<li>${value}</li>`);

    // Clear dữ liệu trong ô input
    inputEl.value = "";
})

// 7. Thêm 1 nút “remove”. Chức năng để xóa thẻ <li> cuối cùng của danh sách
btnRemove.addEventListener("click", function() {
    // B1 : Lấy ra thẻ li cuối cùng
    let liLast = document.querySelector("ul li:last-child");

    // B2 : Xóa thẻ li
    if(liLast) {
        ulEl.removeChild(liLast);
    }
})

// 8. Thêm 1 nút “Hide” trên đầu của danh sách <ul>.
// Khi bấm vào “Hide” thì <ul> sẽ ẩn. Đồng thời label của nút “Hide” => “Show”
// Và ngược lại Khi bấm vào “Show” thì <ul> sẽ hiện. Đồng thời label của nút “Show” => “Hide”
btnHide.addEventListener("click", function() {
    // Ẩn hiện phần tử
    ulEl.classList.toggle("hide");

    // Kiểm tra class để thay đổi nội dung của button
    if(ulEl.classList.contains("hide")) {
        // Trạng thái của ul đang là ẩn -> thay đổi "hide" -> "show"
        btnHide.innerText = "Show";
    } else {
        // Trạng thái của ul đang là hiện -> thay đổi "show" -> "hide"
        btnHide.innerText = "Hide";
    }
})