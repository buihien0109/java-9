const btn = document.querySelector(".btn-top");

window.addEventListener("scroll", function() {
    if(document.documentElement.scrollTop > 300) {
        btn.style.display = "inline-block";
    } else {
        btn.style.display = "none";
    }
});

btn.addEventListener("click", function() {
    window.scrollTo({
        top : 0,
        behavior : "smooth"
    })
})