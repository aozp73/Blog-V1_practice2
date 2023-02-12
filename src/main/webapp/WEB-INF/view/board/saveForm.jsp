<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>

        <div class="container my-3">
            <form class="mb-1">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Enter title" name="title" id="title">
                </div>

                <div class="form-group">
                    <textarea class="form-control summernote" rows="5" id="content" name="content"></textarea>
                </div>
                <button onclick="postBoard()" type="button" class="btn btn-primary">글쓰기완료</button>
            </form>


        </div>

        <script>
            $('.summernote').summernote({
                tabsize: 2,
                height: 400
            });
        </script>

        <script>
            function postBoard() {
                let title = $("#title").val();
                let content = $("#content").val();

                let board = {
                    title: title,
                    content: content
                }
                $.ajax({
                    type: "post",
                    url: "/board",
                    data: JSON.stringify(board),
                    contentType: "application/json; charset=utf-8",
                    dataType: "json"
                }).done((res) => {
                    alert(res.msg);
                    location.href = "/";
                }).fail((err) => {
                    alert(err.responseJSON.msg);
                });
            }
        </script>

        <%@ include file="../layout/footer.jsp" %>