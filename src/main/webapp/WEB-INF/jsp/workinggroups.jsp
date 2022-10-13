<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<html>

<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.css"/>
    <link rel="stylesheet" href="css/sort.css" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.js"></script>

    <title>Сотрудники</title>
    <style>
        .table-row {
            cursor: pointer;
        }
    </style>
</head>

<body>
<%@ include file="formadmin.jsp" %>


<div class="d-flex justify-content-center">
    <div class="pt-5 pb-2">
        <!-- Button trigger modal -->
        <button type="button" class="btn btn-warning .col-md-3 .ml-auto" data-toggle="modal"
                data-target="#exampleModal">
            Добавить пользователя в проект
        </button>
    </div>
    <!-- Modal -->
    <form action="/addUserToProj" method="POST">
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Добавление пользователя к проекту</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="input-group mb-3">
                            <select class="form-select" aria-label="Default select example" name="selectUser">
                                <option selected>Пользователь</option>
                                <c:forEach items="${allUsers}" var="users">
                                    <option value="${users.id}">${users.username}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="input-group mb-3">
                            <select class="form-select" aria-label="Default select example" name="selectProj">
                                <option selected>Проект</option>
                                <c:forEach items="${allProjects}" var="projects">
                                    <option value="${projects.id}">${projects.projectname} </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-warning" data-dismiss="modal">Закрыть</button>
                        <button type="submit" class="btn btn-warning">Сохранить</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>



<form>
    <div class="form-group row justify-content-center">
        <div class="w-25 p-3">
            <input type="text" class="form-control" placeholder="Имя сотрудника" id="filterName"
                   name="filterName" onkeyup="filterName()">
        </div>

        <div class="w-25 p-3">
            <input type="text" class="form-control" placeholder="Название проекта" id="filterProjectName"
                   name="filterDepartmentName" onkeyup="filterProject()">
        </div>
    </div>
    <div class="row pt-2">
        <div class="container">
            <table id="allproj" class="table table-striped table_sort align-middle table-responsive-md btn-table mt-7">
                <thead class="thead-warning">
                <tr>
                    <th scope="col">Имя сотрудника</th>
                    <th scope="col">Проекты</th>
                    <th scope="col">Дата окончания проекта</th>
                    <th scope="col">Позиция</th>
                    <th scope="col">Ставка</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${allUsers}" var="user">
                    <tr>
                        <td>${user.username}</td>
                        <td>
                            <c:forEach items="${user.projects}"
                                       var="project">${project.projectname};<br></br> </c:forEach>
                        </td>
                        <td>
                            <c:forEach items="${user.projects}" var="project">${project.endDate};<br></br> </c:forEach>
                        </td>
                        <td>${user.position}</td>
                        <td>${user.rate}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    </div>
</form>
</form>


<script>
    function filterName() {
        var input, filter, table, tr, td, i, txtValue;
        input = document.getElementById("filterName");
        filter = input.value.toUpperCase();
        table = document.getElementById("allproj");
        tr = table.getElementsByTagName("tr");
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[0];
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
</script>

<script>
    function filterProject() {
        var input, filter, table, tr, td, i, txtValue;
        input = document.getElementById("filterProjectName");
        filter = input.value.toUpperCase();
        table = document.getElementById("allproj");
        tr = table.getElementsByTagName("tr");
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[1];
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
</script>

<script>
    document.addEventListener('DOMContentLoaded', () => {

        const getSort = ({target}) => {
            const order = (target.dataset.order = -(target.dataset.order || -1));
            const index = [...target.parentNode.cells].indexOf(target);
            const collator = new Intl.Collator(['en', 'ru'], {numeric: true});
            const comparator = (index, order) => (a, b) => order * collator.compare(
                a.children[index].innerHTML,
                b.children[index].innerHTML
            );

            for (const tBody of target.closest('table').tBodies)
                tBody.append(...[...tBody.rows].sort(comparator(index, order)));

            for (const cell of target.parentNode.cells)
                cell.classList.toggle('sorted', cell === target);
        };

        document.querySelectorAll('.table_sort thead').forEach(tableTH => tableTH.addEventListener('click', () => getSort(event)));

    });
</script>


<script src="js/bootstrap.min.js" type="text/javascript"></script>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>


</body>
</html>