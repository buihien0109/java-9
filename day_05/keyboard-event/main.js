// // Lắng nghe sự kiện keyup
// document.addEventListener("keyup", function () {
//     console.log("keyup");
// });

// // Lắng nghe sự kiện keypress
// document.addEventListener("keypress", function () {
//     console.log("keypress");
// });

// Lắng nghe sự kiện keydown
// document.addEventListener("keydown", function (event) {
//     console.log(event);
// });

const inputEl = document.querySelector(".input");
const contentEl = document.querySelector(".content");

inputEl.addEventListener("keydown", function (event) {
    if (event.keyCode == 13) {
        // Lấy ra nội dung trong ô input
        // let value = inputEl.value;
        // let value = this.value;
        let value = event.target.value;

        // Hiển thị nội dung
        contentEl.innerText = value;

        // Clear data trong ô input
        inputEl.value = "";
    }
})

document.addEventListener("keydown", function (event) {
    if (event.keyCode == 13) {
        drawCircle();
    } else if (event.keyCode == 32) {
        drawSquare();
    } else {
        changeBackgroundColor();
    }
});

function randomPosition(min, max) {
    return Math.floor(Math.random() * (max - min + 1) + min);
}

// Vẽ hình tròn
function drawCircle() {
    // Xóa hình tròn cũ trước đi
    const boxEl = document.querySelector(".box");

    // Kiểm tra nếu có -> xóa
    if (boxEl) {
        boxEl.parentElement.removeChild(boxEl);
    }

    // Tạo hình tròn mới
    // B1 : Tạo hình tròn
    let box = document.createElement("div");
    box.classList.add("box");

    // B2 : Chèn vị trí
    box.style.left = `${randomPosition(100, 800)}px`;
    box.style.top = `${randomPosition(100, 400)}px`;

    // B3 : Gắn vào DOM
    document.body.appendChild(box);
}

// Vẽ hình vuông
function drawSquare() {
    // Xóa hình tròn cũ trước đi
    const boxEl = document.querySelector(".square");

    // Kiểm tra nếu có -> xóa
    if (boxEl) {
        boxEl.parentElement.removeChild(boxEl);
    }

    // Tạo hình tròn mới
    // B1 : Tạo hình tròn
    let box = document.createElement("div");
    box.classList.add("square");

    // B2 : Chèn vị trí
    box.style.left = `${randomPosition(100, 800)}px`;
    box.style.top = `${randomPosition(100, 400)}px`;

    // B3 : Gắn vào DOM
    document.body.appendChild(box);
}

// Thay đổi màu
function changeBackgroundColor() {
    document.body.style.backgroundColor = "#" + Math.floor(Math.random() * 16777215).toString(16);
}

