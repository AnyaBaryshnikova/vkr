<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.css"/>
    <link rel="stylesheet" href="style/sort.css" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.js"></script>
    <title>Проекты</title>
    <style>
        .table-row {
            cursor: pointer;
        }
    </style>
</head>

<body>
<%@ include file="formuser.jsp" %>

<%--table------------------------------------------------------------------------------------------%>
<div class="justify-content-center form-group row">
    <div class="w-25 p-3">
        <input type="text" class="form-control" placeholder="Название проекта" id="filterProjectName"
               name="filterName" onkeyup="filterProject()">
    </div>
    <div class="w-25 p-3">
        <input type="text" class="form-control" placeholder="Статус" id="filterStatusName"
               name="filterStatusName" onkeyup="filterStatus()">
    </div>
    <div class="w-25 p-3">
        <input type="text" class="form-control" placeholder="Ответственный" id="filterManagerName"
               name="filterManagerName" onkeyup="filterManager()">
    </div>
</div>
<form>
    <div class="row pt-2">
        <div class="container">
            <table id="allproj" class="table table-striped table_sort align-middle table-responsive-md mt-7">
                <thead class="thead-warning">
                <tr>
                    <th>#</th>
                    <th>Название проекта</th>
                    <th>Дата начала</th>
                    <th>Дата окончания</th>
                    <th>Ответственный</th>
                    <th>Статус</th>
                    <th>Фактическое время на проекте</th>
                </tr>
                </thead>
                <tbody>
                <% int i = 1;%>
                <c:forEach items="${allProjects}" var="projects">
                    <tr>
                        <td><%= i%>
                        </td>
                        <td>${projects.projectname}</td>
                        <td>${projects.startDate}</td>
                        <td>${projects.endDate}</td>
                        <td>${manager}</td>
                        <td>${projects.status}</td>
                        <td></td>
                        <%++i;%>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</form>

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
    function filterManager() {
        var input, filter, table, tr, td, i, txtValue;
        input = document.getElementById("filterManagerName");
        filter = input.value.toUpperCase();
        table = document.getElementById("allproj");
        tr = table.getElementsByTagName("tr");
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[5];
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
    function filterStatus() {
        var input, filter, table, tr, td, i, txtValue;
        input = document.getElementById("filterStatusName");
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