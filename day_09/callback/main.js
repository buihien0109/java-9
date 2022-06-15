// Mô tả về hoạt đồng hằng ngày
// 1. Làm bài tập (3s)
// 2. Đá bóng (4s)
// 3. Nấu cơm (5s)

function doTask1(name, callback) {
    console.log("Bắt đầu công việc");
    console.log(`Thực hiện công việc ${name}`);
    setTimeout(function () {
        callback();
    }, 3000);
}

function doTask2(name, callback) {
    console.log(`Thực hiện công việc ${name}`);
    setTimeout(function () {
        callback();
    }, 4000);
}

function doTask3(name, callback) {
    console.log(`Thực hiện công việc ${name}`);
    setTimeout(function () {
        callback();
    }, 5000);
}

function finish() {
    console.log("Kết thúc công việc");
}

// Các công việc liên quan đến nhau
// Cv sau cần kết quả của công việc trước để làm tham số thực hiện
// doTask1("Làm bài tập", function() {
//     doTask2("Đá bóng", function() {
//         doTask3("Nấu cơm", finish);
//     })
// })

// Các công việc không liên quan đến nhau
doTask1("Làm bài tập", finish);
doTask2("Đá bóng", finish);
doTask3("Nấu cơm", finish);