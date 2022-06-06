const heading = document.getElementById("heading")

console.log(heading.classList);

// Thêm class
heading.classList.add("text-big", "text-center");

// Xóa class
heading.classList.remove("text-big", "text-center");

// Kiểm tra 1 class có tồn tại trong phần tử hay không
console.log(heading.classList.contains("text-red"));
console.log(heading.classList.contains("text-big"));

// Toggle
// setInterval(function() {
//     heading.classList.toggle("text-red");
// }, 100) // 1000ms = 1s


// Đếm ngược thời gian
const timeEl = document.querySelector(".time");
const textEl = document.querySelector(".text");

let time = 10;

let interval = setInterval(function() {
    time--;
    timeEl.innerText = `${time}s`;

    if(time == 0) {
        // Dừng quá trình đếm ngược
        clearInterval(interval);

        textEl.classList.remove("hide");
        timeEl.classList.add("hide");
    }
    
}, 1000) // 1000ms = 1s

// SetTimeOut : Delay 1 khoảng thời gian, sau đó mới thực hiện cv
setTimeout(function() {
    heading.classList.remove("text-red");
}, 3000)