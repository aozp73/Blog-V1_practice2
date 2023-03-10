<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>

        <div class="container my-3">
            <form>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Enter title" name="title" id="title"
                        value="${dto.title}">
                </div>

                <div class="form-group">
                    <textarea class="form-control summernote" rows="5" id="content" name="content">
                    ${dto.content}
                </textarea>
                </div>
            </form>
            <button onclick="putById(`${dto.id}`)" type="button" class="btn btn-primary">글수정완료</button>

        </div>

        <script>
            $('.summernote').summernote({
                tabsize: 2,
                height: 400
            });
        </script>

        <script>
            function putById(id) {
                let title = $("#title").val();
                let content = $("#content").val();

                let board = {
                    boardId: id,
                    title: title,
                    content: content
                }

                $.ajax({
                    type: "put",
                    url: "/board/" + id,
                    data: JSON.stringify(board),
                    contentType: "application/json; charset=urf-8",
                    dataType: "json"
                }).done((res) => {
                    alert(res.msg);
                    location.href = "/board/" + id;
                }).fail((err) => {
                    alert(err.responseJSON.msg);
                });
            }
        </script>

        <%@ include file="../layout/footer.jsp" %>