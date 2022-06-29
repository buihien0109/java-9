// Truy cập vào các thành phần
const todoListEl = document.querySelector(".todo-list");
const todoOptionEls = document.querySelectorAll(".todo-option-item input");
const btnAdd = document.getElementById("btn-add");
const btnUpdate = document.getElementById("btn-update");
const inputEl = document.querySelector("#todo-input");

// Khai báo biến
const API_URL = "http://localhost:8080/api/v1/todos";


let todos;
let updateId; // Lưu id công việc để cập nhật

// Định nghĩa function để gọi API
const getTodosAPI = status => {
    switch (status) {
        case "all": {
            return axios.get(API_URL);
        }
        case "unactive": {
            return axios.get(`${API_URL}?status=false`);
        }
        case "active": {
            return axios.get(`${API_URL}?status=true`);
        }
        default: {
            return axios.get(API_URL);
        }
    }
}

// Hàm xử lý

// Lấy danh sách tất cả công việc
const getTodos = async (status = "") => {
    try {
        // Gọi API lấy dữ liệu
        let res = await getTodosAPI(status);

        // Lưu lại dữ liệu trả về từ server
        todos = res.data;

        // Hiển thị ra ngoài giao diện
        renderTodo(todos);
    } catch (error) {
        console.log(error);
    }
}

// Lọc công việc theo trạng thái
todoOptionEls.forEach(input => {
    input.addEventListener("change", () => {
        // Lấy value tương ứng với từng ô input được chọn
        let status = input.value;

        // Gọi API --> Hiển thị dữ liệu tương ứng
        getTodos(status);
    })
})

// Xóa công việc
const deleteTodo = async (id) => {
    try {
        let isConfirm = confirm("Bạn có muốn xóa không");
        if (isConfirm) {
            // Gọi API xóa
            axios.delete(`${API_URL}/${id}`);

            // Xóa trong mảng todos
            todos.forEach((t, index) => {
                if (t.id == id) {
                    todos.splice(index, 1);
                }
            });

            // Render lại giao diện
            renderTodo(todos);
        }
    } catch (error) {
        console.log(error);
    }
}

// Thay đổi trạng thái công việc
const toggleStatus = id => {
    // Tìm kiếm công việc có id = id truyền vào
    let todo = todos.find(t => t.id == id);
    todo.status = !todo.status;

    // Gọi API để thay đổi (title -> giữ nguyễn, thay đổi status)


    // Render lại giao diện
}

// Thêm công việc
btnAdd.addEventListener('click', async () => {
    try {
        // Lấy ra dữ liệu trong ô input
        let title = inputEl.value;

        // Kiểm tra dữ liệu có trống hay không
        if(title == "") {
            alert("Tiêu đề công việc không được để trống");
            return;
        }

        // Gọi API để tạo mới
        let res = await axios.post(API_URL, {
            title: title
        });

        // Push data từ API trả về vào mảng ban đầu
        todos.push(res.data);

        // Render lại giao diện
        renderTodo(todos);
        inputEl.value = "";
    } catch (error) {
        console.log(error);
    }
})

// Cập nhật
const updateTodo = id => {
    // Cập nhật nút thêm -> cập nhật
    btnAdd.classList.add("hide");
    btnUpdate.classList.remove("hide");

    // Hiển thị tiêu đề
    let todo = todos.find((t) => t.id == id);
    inputEl.value = todo.title;

    // Lưu lại id
    updateId = id;
};

btnUpdate.addEventListener('click', async () => {
    try {
        // Tìm kiếm công việc
        let todo = todos.find((t) => t.id == updateId);

        // Cập nhật todos;
        todo.title = inputEl.value;

        // Gọi API cập nhật
        await axios.put(`${API_URL}/${updateId}`, {
            title: todo.title,
            status: todo.status,
        });

        // Hiển thị lại sau khi cập nhật
        renderTodo(todos);

        // Trả lại ban đầu.
        btnAdd.classList.remove("hide");
        btnUpdate.classList.add("hide");
        inputEl.value = "";
    } catch (error) {
        console.log(error);
    }
})

// Hiển thị todo ra ngoài giao diện
const renderTodo = arr => {
    todoListEl.innerHTML = "";

    if (arr.length == 0) {
        todoListEl.innerHTML = "Danh sách công việc trống";
        return;
    }

    let html = "";
    arr.forEach(t => {
        html += `
            <div class="todo-item ${t.status ? "active-todo" : ""}">
                <div class="todo-item-title">
                    <input 
                        type="checkbox" 
                        ${t.status ? "checked" : ""} 
                        onclick="toggleStatus(${t.id})"
                    />
                    <p>${t.title}</p>
                </div>
                <div class="option">
                    <button class="btn btn-update" onclick="updateTodo(${t.id})">
                        <img src="./img/pencil.svg" alt="icon" />
                    </button>
                    <button class="btn btn-delete" onclick="deleteTodo(${t.id})">
                        <img src="./img/remove.svg" alt="icon" />
                    </button>
                </div>
            </div>
        `
    });

    todoListEl.innerHTML = html;
}


getTodos();
