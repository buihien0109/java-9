let userId = 1;

// Truy cập
const avatarEl = document.getElementById("avatar");
const avatarPreviewEl = document.getElementById("avatar-preview");

const btnModalImage = document.getElementById("btn-modal-image");
const imageContainerEl = document.querySelector(".image-container");

const btnChoseImage = document.getElementById("btn-chose-image");
const btnDeleteImage = document.getElementById("btn-delete-image");

const modelImageEl = document.getElementById("modal-image");

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
        let res = await axios.post(`http://localhost:8080/api/v1/users/${userId}/upload-avatar`, formData)

        // Thêm vào mảng ban đầu, mới nhất thì cho lên đầu
        listImage.unshift(res.data);

        // Hiển thị lại trên giao diện
        renderImg(listImage)
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
        renderImg(listImage);
    } catch (error) {
        console.log(error);
    }
});

// Hiển thị danh sách hình ảnh ra ngoài giao diện
function renderImg(arr) {
    imageContainerEl.innerHTML = '';

    let html = "";
    arr.forEach(i => {
        html += `
            <div class="image-item" onclick="choseImage(this)">
                <img src="http://localhost:8080${i}" alt="">
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