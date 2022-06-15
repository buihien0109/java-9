const btnRandom = document.getElementById("btn");
const imageEl = document.getElementById("image");

// fetch API
// axios
// ajax + jquery

btnRandom.addEventListener("click", async function() {
    try {
        // B1 : Gọi API
        let res = await axios.get("https://dog.ceo/api/breeds/image/random");
        console.log(res);

        // B2 : Gán kết quả trả về từ API -> imageEl
        imageEl.src = res.data.message;
    } catch (error) {
        console.log(error);
    }
})