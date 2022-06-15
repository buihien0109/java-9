// Promise : Lời hứa

// Ví dụ 1 : 1 tháng trước vay bạn 30 triệu
// Và hứa rằng sau 1 tháng thì trả
let vay = 30;

// 1 tháng sau lĩnh lương + thưởng có 50 triệu
let money = 35;

let promise = function() {
    return new Promise(function(resolve, reject){
        if(money >= vay) {
            resolve("Trả đủ tiền cho bạn");
        } else {
            reject("Đợi khi nào có tiền thì trả");
        }
    })
}

// Ví dụ 2 : Sau khi trả tiền của mình xong, thì thằng em lại mượn lại 10tr để đi chơi
let promise1 = function() {
    return new Promise(function(resolve, reject){
        if(money - vay - 10 >= 0) {
            resolve("Cho đứa em mượn tiền đi chơi");
        } else {
            reject("Anh em gì tầm này. Tiền k đủ trả nợ nữa là cho vay.");
        }
    })
}

promise()
    .then(res => {
        console.log(res);
        return promise1();
    })
    .then(res => console.log(res))
    .catch(error => console.log(error))
    .finally(() => console.log("Mình vẫn là bạn nhé."))