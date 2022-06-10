// Lọc khóa học theo hình thưc (type)
// window.location.search = name=abc&id=123
let params = new URLSearchParams(window.location.search);
let type = params.get("type");

// index.html
// index.html?type=online
// index.html?type=onlab

let courseList = [...courses];

if(type) {
    // Lọc theo type
    if(type == "onlab") {
        document.title = "Khóa học phòng lab";
        courseList = courses.filter(c => c.type == "onlab");
    } else if(type == "online") {
        document.title = "Khóa học trực tuyến";
        courseList = courses.filter(c => c.type == "online");
    } else {
        window.location.href = "./404.html";
    }
} else {
    // Danh sách tất cả
    document.title = "Danh sách khóa học";
}


// Truy cập
const courseListEl = document.querySelector('.course-list');
const searchEl = document.querySelector(".seach-form-input");
const btnSearch = document.querySelector(".seach-form-button");
const topicsEl = document.querySelectorAll(".topic-item input");

// Chức năng tìm kiếm khóa học theo chủ đề (topic)
topicsEl.forEach(input => {
    input.addEventListener("change", function() {
        let topic = input.value;
        filterCourseByTopic(topic);
    })
})

const filterCourseByTopic = topic => {
    let courseFilter = courseList.filter(c => c.topics.includes(topic));
    renderCourse(courseFilter);
}


// Chức năng tìm kiếm khóa học theo tên (input)
searchEl.addEventListener("keydown", function(e) {
    if(e.keyCode == 13) {
        let term = e.target.value;
        if(term == "") {
            alert("Nội dung tìm kiếm không được để trống");
            return;
        }

        // Lọc khóa học và hiển thị
        filterCourseByTitle(term);
    }
})

// Chức năng tìm kiếm khóa học theo tên (button)
btnSearch.addEventListener("click", function() {
    let term = searchEl.value;
    if(term == "") {
        alert("Nội dung tìm kiếm không được để trống");
        return;
    }

    // Lọc khóa học và hiển thị
    filterCourseByTitle(term);
})

// Lọc khóa học và hiển thị
const filterCourseByTitle = term => {
    let courseFilter = courseList.filter(c => c.title.toLowerCase().includes(term.toLowerCase()));
    renderCourse(courseFilter);
}

// Hiển thị danh sách khóa học
const renderCourse = arr => {
    courseListEl.innerHTML = "";

    let html = "";
    for (let i = 0; i < arr.length; i++) {
        const c = arr[i];
        html += `
            <div class="col-md-4">
                <a href="./detail.html?id=${c.id}">
                    <div class="course-item shadow-sm rounded mb-4">
                        <div class="course-item-image">
                            <img src="${c.image}" alt="${c.title}">
                        </div>
                        <div class="course-item-info p-3">
                            <h2 class="fs-5 mb-4 text-dark">${c.title}</h2>
                            <div class="d-flex justify-content-between align-items-center fw-light text-black-50">
                                <p class="type">${c.type}</p>
                                <p class="rating">
                                    ${c.rating} <span class="text-warning"><i class="fa-solid fa-star"></i></span>
                                </p>
                            </div>
                        </div>
                    </div>
                </a>
            </div>
        `;
    }
    courseListEl.innerHTML = html;
}

renderCourse(courseList);