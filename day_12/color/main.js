const btnRandomColorName = document.getElementById("random-color-name");

btnRandomColorName.addEventListener("click", async function() {
    try {
        // Gọi API
        let res = await axios.get("http://localhost:8080/random-color?type=4");
        
        document.body.style.backgroundColor = res.data;
    } catch (error) {
        alert(error.response.data.message);
    }
})
