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

    <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.js"></script

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
                data-target="#modal">
            Редактировать сотрудника
        </button>
    </div>
    <!-- Modal -->
    <form action="/insertUserToDepartment" method="POST">
        <div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="newModalLabel1">Редактировать сотрудника</h5>
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
                            <select class="form-select" aria-label="Default select example" name="selectDep">
                                <option selected>Отдел</option>
                                <c:forEach items="${alldeps}" var="deps">
                                    <option value="${deps.depname}">${deps.depname} </option>
                                </c:forEach>
                            </select>
                        </div>
                        <h6 class="secondary-heading mt-3">Позиция</h6>
                        <section class="section-preview">
                            <div class="custom-control custom-radio custom-control-inline">
                                <input type="radio" class="custom-control-input" id="positionJun" name="position"
                                       value="junior">
                                <label class="custom-control-label" for="positionJun">Junior</label>
                            </div>
                            <div class="custom-control custom-radio custom-control-inline">
                                <input type="radio" class="custom-control-input" id="positionMid" name="position"
                                       value="senoir">
                                <label class="custom-control-label" for="positionMid">Senior</label>
                            </div>
                            <div class="custom-control custom-radio custom-control-inline">
                                <input type="radio" class="custom-control-input" id="positionLead" name="position"
                                       value="leader">
                                <label class="custom-control-label" for="positionLead">Leader</label>
                            </div>
                            <div class="custom-control custom-radio custom-control-inline">
                                <input type="radio" class="custom-control-input" id="nochanges" name="position"
                                       value="Не изменять">
                                <label class="custom-control-label" for="nochanges">Не изменять</label>
                            </div>
                        </section>
                        <h6 class="secondary-heading mt-3">Ставка</h6>
                        <section class="section-preview">
                            <div class="custom-control custom-radio custom-control-inline">
                                <input type="radio" class="custom-control-input" id="rate25" name="rate" value="0.25">
                                <label class="custom-control-label" for="rate25">0.25</label>
                            </div>
                            <div class="custom-control custom-radio custom-control-inline">
                                <input type="radio" class="custom-control-input" id="rate5" name="rate" value="0.5">
                                <label class="custom-control-label" for="rate5">0.50</label>
                            </div>
                            <div class="custom-control custom-radio custom-control-inline">
                                <input type="radio" class="custom-control-input" id="rate75" name="rate" value="0.75">
                                <label class="custom-control-label" for="rate75">0.75</label>
                            </div>
                            <div class="custom-control custom-radio custom-control-inline">
                                <input type="radio" class="custom-control-input" id="rate1" name="rate" value="1">
                                <label class="custom-control-label" for="rate1">1</label>
                            </div>
                            <div class="custom-control custom-radio custom-control-inline">
                                <input type="radio" class="custom-control-input" id="nochange" name="rate"
                                       value="Не изменять">
                                <label class="custom-control-label" for="nochange">Не изменять</label>
                            </div>
                        </section>
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


<%--modal------------------------------------------------------------------------------------------%>

<%--table------------------------------------------------------------------------------------------%>
<div class="form-group row justify-content-center">
    <div class="w-25 p-3">
        <input type="text" class="form-control" placeholder="Имя сотрудника" id="filterName"
               name="filterName" onkeyup="filterName()">
    </div>

    <div class="w-25 p-3">
        <input type="text" class="form-control" placeholder="Отдел" id="filterDepartmentName"
               name="filterDepartmentName" onkeyup="filterDepartment()">
    </div>
</div>
<form>
    <div class="row pt-2">
        <div class="container">
            <table id="allproj" class="table table-striped table_sort align-middle table-responsive-md btn-table mt-7">
                <thead class="thead-warning">
                <tr>
                    <th scope="col">Имя сотрудника</th>
                    <th scope="col">Проекты</th>
                    <th scope="col">Отдел</th>
                    <th scope="col">Позиция</th>
                    <th scope="col">Ставка</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${allUserst}" var="user">
                    <tr>
                        <td>${user.username}</td>
                        <td>
                            <c:forEach items="${user.projects}"
                                       var="project">${project.projectname};<br></br> </c:forEach>
                        </td>
                        <td>${user.departments.toArray()[0].depname}</td>
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
    function filterDepartment() {
        var input, filter, table, tr, td, i, txtValue;
        input = document.getElementById("filterDepartmentName");
        filter = input.value.toUpperCase();
        table = document.getElementById("allproj");
        tr = table.getElementsByTagName("tr");
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[2];
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

<!-- Bootstrap -->
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