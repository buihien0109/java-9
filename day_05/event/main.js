// Sử dụng HTML-attribute
// Regular function
// function sayHello() {
//     alert("Xin chào các bạn 1");
// }

// Function expression
// const sayHello = function() {
//     alert("Xin chào các bạn 1");
// }

// Arrow function
const sayHello = () => {
    alert("Xin chào các bạn 1");
}

// Sử dụng DOM property
const btn2 = document.getElementById("btn-2");

// Function k có tên : Anonymous function
// btn2.onclick = function() {
//     alert("Xin chào các bạn 2");
// }

const sayHello2 = () => {
    alert("Xin chào các bạn 2");
}

btn2.onclick = sayHello2;

// Sử dụng addEventListener
const btn3 = document.getElementById("btn-3");

// btn3.addEventListener("click", function() {
//     alert("Xin chào các bạn 3");
// })

const sayHello3 = () => {
    alert("Xin chào các bạn 3");
}

btn3.addEventListener("click", sayHello3);