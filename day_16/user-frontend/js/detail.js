let userId = 1;

// Truy cập
const avatarEl = document.getElementById("avatar");
const avatarPreviewEl = document.getElementById("avatar-preview");

const btnModalImage = document.getElementById("btn-modal-image");
const imageContainerEl = document.querySelector(".image-container");

const btnChoseImage = document.getElementById("btn-chose-image");
const btnDeleteImage = document.getElementById("btn-delete-image");

const modelImageEl = document.getElementById("modal-image");

const modelImageConfig = new bootstrap.Modal(modelImageEl, {
    keyboard: false
})

// Khai báo biến
let listImage = [];

avatarEl.addEventListener("change", async function (event) {
    try {
        // Lấy ra file cần upload dựa vào event
        let file = event.target.files[0];

        // Tạo formData và append file vào
        let formData = new FormData();
        formData.append("file", file);

        // Gọi API
        let res = await axios.post(`http://localhost:8080/api/v1/users/${userId}/files`, formData)

        // Thêm vào mảng ban đầu, mới nhất thì cho lên đầu
        listImage.unshift(res.data);

        // Hiển thị lại trên giao diện
        renderImageAndPagination(listImage);
    } catch (error) {
        console.log(error);
    }
})

// Hiển thị danh sách hình ảnh
btnModalImage.addEventListener("click", async () => {
    try {
        // Gọi API
        let res = await axios.get(`http://localhost:8080/api/v1/users/${userId}/files`);

        // Lưu lại ảnh của user
        listImage = res.data;

        // Hiển thị ra ngoài giao diện
        renderImageAndPagination(listImage);
    } catch (error) {
        console.log(error);
    }
});

// Hiển thị hình ảnh và phân trang
const renderImageAndPagination = arr => {
    $('.pagination-container').pagination({
        dataSource: arr,
        pageSize: 4,
        className: 'paginationjs-theme-blue paginationjs-big',
        callback: function (data) {
            renderImg(data);
        }
    })
}

// Hiển thị danh sách hình ảnh ra ngoài giao diện
function renderImg(arr) {
    imageContainerEl.innerHTML = '';

    let html = "";
    arr.forEach(i => {
        html += `
            <div class="image-item" onclick="choseImage(this)">
                <img src="http://localhost:8080${i}" alt="" data-src=${i}>
            </div>
        `
    });
    imageContainerEl.innerHTML = html;
}

// CHọn ảnh
const choseImage = (ele) => {
    // Tìm kiếm image đang được active
    let imageSelected = document.querySelector(".selected");

    // Nếu tồn tại thì xóa class "border-primary", "selected" trong image đó đi
    if (imageSelected) {
        imageSelected.classList.remove("border-primary", "selected");
    }

    // Thêm class "border-primary", "selected" vào image đang được click
    ele.classList.add("border-primary", "selected");

    // Bỏ "disabled" ở 2 nút "Chọn ảnh" và "Xóa ảnh" để có thể thực hiện chức năng
    btnChoseImage.disabled = false;
    btnDeleteImage.disabled = false;
}

// Lắng nghe sự hiện đóng modal để disabled 2 nút "Chọn ảnh" và "Xóa ảnh"
modelImageEl.addEventListener('hidden.bs.modal', function () {
    btnChoseImage.disabled = true;
    btnDeleteImage.disabled = true;
})

// Cập nhật avatar
btnChoseImage.addEventListener("click", async function () {
    try {
        // Lấy ra ảnh đang được chọn
        let imageSelected = document.querySelector(".selected img");

        // Lấy URL của ảnh đang chọn
        let src = imageSelected.dataset.src;

        // Gọi API
        let res = await axios.put(`http://localhost:8080/api/v1/users/${userId}/update-avatar`, {
            avatar: src
        });

        // Đóng modal
        modelImageConfig.hide();

        // Hiển thị ảnh trên giao diện
        avatarPreviewEl.src = "http://localhost:8080" + res.data;
    } catch (error) {
        console.log(error);
    }
});

// Xóa ảnh
btnDeleteImage.addEventListener("click", async () => {
    try {
        // Lấy ra ảnh đang được chọn
        let imageSelected = document.querySelector(".selected img");

        // Gửi API xóa ảnh
        await axios.delete(imageSelected.src);

        // Xóa ảnh trong mảng ban đầu
        let src = imageSelected.dataset.src;
        listImage = listImage.filter(i => i != src);

        // Render lại giao diện
        renderImageAndPagination(listImage);

        // disabled 2 nút "Chọn ảnh" và "Xóa ảnh"
        btnChoseImage.disabled = true;
        btnDeleteImage.disabled = true;
    } catch (error) {
        console.log(error);
    }
});