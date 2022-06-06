
// document.addEventListener('mousedown', function () {
//     console.log('mousedown');
// })

// document.addEventListener('mousemove', function () {
//     console.log('mousemove');
// })

// document.addEventListener('mouseup', function () {
//     console.log('mouseup');
// })

// document.addEventListener('click', function (event) {
//     console.log(event);
// })

document.addEventListener("mousemove", function(event) {
    // Xóa hình tròn cũ trước đi
    const boxEl = document.querySelector(".box");

    // Kiểm tra nếu có -> xóa
    if(boxEl) {
        boxEl.parentElement.removeChild(boxEl);
    }

    // Tạo hình tròn mới
    // B1 : Tạo hình tròn
    let box = document.createElement("div");
    box.classList.add("box");

    // B2 : Chèn vị trí
    console.log(event);
    box.style.left = `${event.offsetX - 50}px`;
    box.style.top = `${event.offsetY - 50}px`;

    // B3 : Gắn vào DOM
    document.body.appendChild(box);
})