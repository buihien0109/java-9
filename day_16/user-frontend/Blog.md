1. Lấy thông tin của user bất kỳ theo id bao gồm các thông tin sau : **id, name, email, avatar**

```json
{
    "id": 1,
    "name": "Nguyễn Văn A",
    "email": "a@gmail.com",
    "avatar": "https://techmaster.vn/resources/image/codegame.jpg"
}
```

2. Lấy thông tin của user bất kỳ theo id bao gồm các thông tin sau : **id, name, email, avatar, issued, expried**. Trong đó **issued, expried** format theo định dạng **dd/MM/yyyy**

```json
{
    "id": 1,
    "name": "Nguyễn Văn A",
    "email": "a@gmail.com",
    "avatar": "https://techmaster.vn/resources/image/codegame.jpg",
    "issued": "20/01/2012",
    "expried": "20/01/2030"
}
```

3. Lấy danh sách blog được sắp xếp theo ngày tạo (**created_at**) giảm dần

Thông tin lấy ra bao gồm :

-   **id, title, slug, description, content, thumbnail**
-   **created_at, updated_at, pulished_at** format theo định dạng **dd/MM/yyyy**
-   status = 1 -> "Công khai", status = 0 -> "Nháp"

```json
[
    {
        "id" : 1,
        "title" : "tiêu đề 1",
        "slug" : "tieu-de-1",
        "content" : "nội dung 1",
        "description" : "mô tả 1",
        "thumbnail" : "https://techmaster.vn/resources/image/codegame.jpg",
        "created_at" : "20/06/2022",
        "updated_at" : "20/06/2022",
        "pulished_at" : "20/07/2022",
        "status" : "Công khai"
    },
    ...
]
```

4. Lấy 5 blog được có **pulished_at** gần đây nhất (dữ liệu trả về thì tương tự câu 3)

5. Liệt kê category và số lượng bài viết áp dụng category đó

```json
[
    {
        "id" : 1 ,
        "name" : "Java",
        "count_blog" : 2
    },
    {
        "id" : 2 ,
        "name" : "Golang",
        "count_blog" : 5
    },
    ...
]
```

5. Liệt kê category và chi tiết các bài viết áp dụng category đó

```json
[
    {
        "id" : 1,
        "name" : "Java",
        "blogs" : [
            {
                "id" : 1,
                "title" : "tiêu đề 1",
                "slug" : "tieu-de-1",
                "content" : "nội dung 1",
                "description" : "mô tả 1",
                "thumbnail" : "https://techmaster.vn/resources/image/codegame.jpg",
                "created_at" : "20/06/2022",
                "updated_at" : "20/06/2022",
                "pulished_at" : "20/07/2022",
                "status" : "Công khai"
            },
            ...
        ]
    },
    ...
]
```

6. Lấy ra thông tin của 5 category được áp dụng nhiều nhất

```json
[
    {
        "id" : 1,
        "name" : "Java"
    },
    ...
]
```

7. Lấy ra danh sách comment của 1 bài blog bất kỳ --> trả về danh sách comment

```json
[
    {
        "id" : 1,
        "content" : "Bình luận 1",
        "created_at" : "22/06/2022",
        "updated_at" : "23/06/2022"
    },
    ...
]
```

8. Lấy ra danh sách comment của 1 bài blog bất kỳ --> Trả về danh sách comment và thông tin của bài viết

```json
{
    "id" : 1,
    "title" : "tiêu đề 1",
    "slug" : "tieu-de-1",
    "content" : "nội dung 1",
    "description" : "mô tả 1",
    "thumbnail" : "https://techmaster.vn/resources/image/codegame.jpg",
    "created_at" : "20/06/2022",
    "updated_at" : "20/06/2022",
    "pulished_at" : "20/07/2022",
    "status" : "Công khai",
    "comments" : [
        {
            "id" : 1,
            "content" : "Bình luận 1",
            "created_at" : "22/06/2022",
            "updated_at" : "23/06/2022"
        },
        ...
    ]
}
```

9. Lấy ra danh sách 5 bài blog có số lượng comment nhiều nhất

```json
[
    {
        "id" : 1,
        "title" : "tiêu đề 1",
        "slug" : "tieu-de-1",
        "content" : "nội dung 1",
        "description" : "mô tả 1",
        "thumbnail" : "https://techmaster.vn/resources/image/codegame.jpg",
        "created_at" : "20/06/2022",
        "updated_at" : "20/06/2022",
        "pulished_at" : "20/07/2022",
        "status" : "Công khai",
        "count_comment" : 6
    },
    ...
]
```

10. Lấy ra danh sách 5 user comment nhiều nhất

```json
[
    {
        "id" : 1,
        "name" : "Nguyễn Văn A",
        "email" : "a@gmail.com",
        "avatar" : "https://techmaster.vn/resources/image/codegame.jpg",
        "count_comment" : 5
    },
    ...
]
```

11. Lấy danh sách image của user bất kỳ được sắp xếp theo ngày tạo mới nhất

```json
[
    {
        "id" : "1",
        "url" : "https://techmaster.vn/resources/image/codegame.jpg",
        "created_at" : "22/12/2022"
    },
    ...
]
```

12. Lấy danh sách image từ thứ 5 -> 10 của user bất kỳ (nếu có)

```json
[
    {
        "id" : "1",
        "url" : "https://techmaster.vn/resources/image/codegame.jpg",
        "created_at" : "22/12/2022"
    },
    ...
]
```

13. Liệt kê danh sách xem mỗi user upload bao nhiêu ảnh

```json
[
    {
        "id" : 1,
        "name" : "Nguyễn Văn A",
        "email" : "a@gmail.com",
        "count_image" : 10
    },
    ...
]
```

14. Liệt kê danh sách xem mỗi user đã upload những ảnh nào

```json
[
    {
        "id" : 1,
        "name" : "Nguyễn Văn A",
        "email" : "a@gmail.com",
        "images" : [
            {
                "id" : "1",
                "url" : "https://techmaster.vn/resources/image/codegame.jpg",
                "created_at" : "22/12/2022"
            },
            ...
        ]
    },
    ...
]
```

15. Lấy ra danh sách 3 user upload nhiều ảnh nhất

```json
[
    {
        "id" : 1,
        "name" : "Nguyễn Văn A",
        "email" : "a@gmail.com",
        "count_image" : 10
    },
    ...
]
```

16. Lấy ra danh sách 3 user upload nhiều ảnh nhất và những ảnh của user đó

```json
[
    {
        "id" : 1,
        "name" : "Nguyễn Văn A",
        "email" : "a@gmail.com",
        "images" : [
            {
                "id" : "1",
                "url" : "https://techmaster.vn/resources/image/codegame.jpg",
                "created_at" : "22/12/2022"
            },
            ...
        ]
    },
    ...
]
```

17. Đếm số lượng ảnh đang có trong hệ thống
