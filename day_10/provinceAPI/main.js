const province = document.getElementById("province");
const district = document.getElementById("district");

const getProvince = async () => {
    try {
        let res = await axios.get("https://provinces.open-api.vn/api/p/");
        renderProvice(res.data);

    } catch (error) {
        console.log(error);
    }
}

const renderProvice = arr => {
    html = "";

    for (let i = 0; i < arr.length; i++) {
        const element = arr[i];
        html += `<option value="${element.code}">${element.name}</option>`;
    }
    province.innerHTML = html;
}

province.addEventListener("change", function() {
    let provinceCode = province.value;
    console.log(provinceCode);
})


getProvince();