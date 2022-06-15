const breedListEl = document.getElementById("breed-list");
const btnGetSubBreed = document.getElementById("btn-get-sub-breed");
const subBreedListEl = document.querySelector(".sub-breed ul");
const imageEl = document.getElementById("image");

// Gọi API lấy danh sách giống loài chính và hiển thị
const getBreedList = async () => {
    try {
        // B1 : Gọi API lấy danh sách giống loài chính
        let res = await axios.get("https://dog.ceo/api/breeds/list/all");

        // B2 : Hiển thị ra ngoài giao diện
        renderBreedList(res.data.message);
    } catch (error) {
        console.log(error);
    }
}

// Hiển thị thông tin giống loài chính
const renderBreedList = obj => {
    // Lấy ra mảng các key
    let keys = Object.keys(obj);

    let html = "";
    for (let i = 0; i < keys.length; i++) {
        html += `<option value="${keys[i]}">${keys[i]}</option>`;
    }
    breedListEl.innerHTML = html;
}

// Xử lý để lấy thông tin giống loài phụ
btnGetSubBreed.addEventListener("click", async function() {
    try {
        // Lấy tên giống loài chính
        let breedName = breedListEl.value;

        // Gọi API
        let res = await axios.get(`https://dog.ceo/api/breed/${breedName}/list`)

        // Hiển thị danh sách giống loài phụ
        renderSubBreedList(res.data.message, breedName);
        imageEl.src = "";
    } catch (error) {
        console.log(error);
    }
})

// Hiển thị giống loài phụ
const renderSubBreedList = (arr, breedName) => {
    // Kiểm tra nếu mảng trống
    if(arr.length == 0) {
        subBreedListEl.parentElement.classList.add("hide");
        return;
    }

    // Nếu có giống loài phụ thì hiển thị
    subBreedListEl.parentElement.classList.remove("hide");

    subBreedListEl.innerHTML = "";
    let html = "";
    for (let i = 0; i < arr.length; i++) {
        html += `<li onclick="randomImageSubBreed('${breedName}', '${arr[i]}')">${arr[i]}</li>`;
    }
    subBreedListEl.innerHTML = html;
}

// Random ảnh cho giống loài phụ
// https://dog.ceo/api/breed/hound/afghan/images/random
const randomImageSubBreed = async (breedName, subBreedName) => {
    try {
        let res = await axios.get(`https://dog.ceo/api/breed/${breedName}/${subBreedName}/images/random`);

        imageEl.src = res.data.message;
    } catch (error) {
        console.log(error);
    }
}

getBreedList();