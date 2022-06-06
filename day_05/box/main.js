let colors = ["#3498db", "#9b59b6", "#e74c3c", "#2c3e50", "#d35400"];
let colorsCopy = [...colors];

// Truy cập vào các thành phần
const btn = document.querySelector("#btn");
const totalBoxEl = document.querySelector(".points");
const boxsEl = document.querySelector(".boxes");

// Render box ra ngoài giao diện
const renderBox = arr => {
    boxsEl.innerHTML = "";

    let html = "";
    for (let i = 0; i < arr.length; i++) {
        const c = arr[i];
        html += `
            <div 
                class="box" 
                style="background-color: ${c}"
                onclick="removeBox(${i})"
            ></div>
        `
    }
    boxsEl.innerHTML = html;

    // Cập nhật tổng số box
    updateTotalBox(arr);
}

// Cập nhật tổng số box
const updateTotalBox = arr => {
    totalBoxEl.innerHTML = arr.length;
}

// Xóa box
const removeBox = index => {
    colors.splice(index, 1);
    renderBox(colors);
}

// Thêm box
btn.addEventListener("click", function() {
    colors = [...colors, ...colorsCopy];
    renderBox(colors);
})

renderBox(colors);

// // Regular function
// function sum(a, b) {
//     return a + b;
// }

// // Function expression
// const sum1 = function(a, b) {
//     return a + b;
// }

// // Arrow function
// const sum2 = () => a + b;
