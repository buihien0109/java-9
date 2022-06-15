const breedListEl = document.getElementById("breed-list");

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

const renderBreedList = obj => {
    // Lấy ra mảng các key
    let keys = Object.keys(obj);

    let html = "";
    for (let i = 0; i < keys.length; i++) {
        html += `<option value="${keys[i]}">${keys[i]}</option>`;
    }
    breedListEl.innerHTML = html;
}



getBreedList();