const testimonials = [
    {
        name : "Dr. Seuss",
        quote : "Don't cry because it's over, smile because it happened",
        color : "#F15412"
    },
    {
        name : "Mae West",
        quote : "You only live once, but if you do it right, once is enough.",
        color : "#2E0249"
    },
    {
        name : "Robert Frost",
        quote : "In three words I can sum up everything I've learned about life: it goes on.",
        color : "#066163"
    },
    {
        name : "Oscar Wilde",
        quote : "To live is the rarest thing in the world. Most people exist, that is all.",
        color : "#764AF1"
    },
    {
        name : "Narcotics Anonymous",
        quote : "Insanity is doing the same thing, over and over again, but expecting different results.",
        color : "#F32424"
    }
]

// Truy cập vào các thành phần
const testimonialsContainerEl = document.querySelector(".testimonials-container");
const textEl = document.querySelector(".text");
const nameEl = document.querySelector(".name");
const authors = document.querySelectorAll(".author");

// Lắng nghe sự kiện
authors.forEach((author, index) => {
    author.addEventListener("click", function() {
        // Trước khi thêm vào --> xóa hết class "selected" đi
        Array.from(authors).map(e => e.classList.remove("selected"));

        // Highlight tác giả (thêm class "selected" vào author đang được click)
        author.classList.add("selected");

        // Render ra thông tin của author đang được chọn
        renderTestimonial(index);
    })
})

const renderTestimonial = index => {
    // B1 : Lấy ra object tương ứng với index
    let testimonial = testimonials[index];

    // B2 : Cập nhật thông tin dựa trên object
    textEl.innerText = testimonial.quote;
    nameEl.innerText = testimonial.name;
    testimonialsContainerEl.style.backgroundColor = testimonial.color;
}

renderTestimonial(0)
