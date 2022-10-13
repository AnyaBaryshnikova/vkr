<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<html>

<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%--    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>--%>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>


    <title>Отчет по сотруднику</title>
</head>

<body>
<%@ include file="formadmin.jsp" %>

<input type="hidden" id="userTasks" type="hidden" value="${userTasks}"/>
<input type="hidden" id="factUser" type="hidden" value="${factUser}"/>
<input type="hidden" id="planUser" type="hidden" value="${planUser}"/>
<input type="hidden" id="projectTimes" type="hidden" value="${projectTimes}"/>
<input type="hidden" id="projNames" type="hidden" value="${projNames}"/>

<form th:action="@{/showAllUsers}" method="post"></form>
<form>
    <div>
        <h6>Пользователь: ${username}</h6>
    </div>

    <br>

    <div>
        <h7>Статистика по задачам в проектах</h7>
    </div>

    <div class="w-25">
        <canvas id="chDonut1"></canvas>
    </div>

    <script>
        var colors = ['#0275d8', '#28a745', '#f0ad4e', '#59287a', '#926040', '#05dc83', '#ea1c23', '#e9bc40', '#b04e88', '#b54961', '#4c1f29'];

        /* 3 donut charts */
        var donutOptions = {
            cutoutPercentage: 40,
            legend: {position: 'bottom', padding: 5, labels: {pointStyle: 'circle', usePointStyle: true}}
        };

        var valtimes = document.getElementById("projectTimes").value;
        valtimes = valtimes.replace('[', '');
        valtimes = valtimes.replace(']', '');
        var valt = valtimes.split(',')


        var vallab = document.getElementById("projNames").value;
        vallab = vallab.replace('[', '');
        vallab = vallab.replace(']', '');
        var vall = vallab.split(',')


        // donut 1
        var chDonutData1 = {
            labels: vall,
            datasets: [
                {
                    backgroundColor: colors.slice(0, 10),
                    borderWidth: 0,
                    data: valt
                }
            ]
        };

        var chDonut1 = document.getElementById("chDonut1");
        if (chDonut1) {
            new Chart(chDonut1, {
                type: 'pie',
                data: chDonutData1,
                options: donutOptions
            });
        }
    </script>

    <br>
    <div>
        <h6>Проект: ${pname}</h6>
    </div>
    <label>Плановое время &nbsp</label>
    <button type="button" class="btn btn-success btn-sm"></button>
    <br><label>Фактическое время &nbsp</label>
    <button type="button" class="btn btn-warning btn-sm"></button>
    </br>


    <canvas id="usersChart" style="width:100%;max-width:900px"></canvas>
    <script>
        var valx = document.getElementById("userTasks").value;
        valx = valx.replace('[', '');
        valx = valx.replace(']', '');
        var nameArr = valx.split(',')
        var valy = document.getElementById("factUser").value;
        valy = valy.replace('[', '');
        valy = valy.replace(']', '');
        var timeFact = valy.split(',')
        var valt = document.getElementById("planUser").value;
        valt = valt.replace('[', '');
        valt = valt.replace(']', '');
        var timePlan = valt.split(',')


        var xValues = nameArr;
        var yValues = timeFact;
        var tValues = timePlan;

        console.log(nameArr);
        console.log(xValues)
        new Chart("usersChart", {
            type: "bar",
            data: {
                labels: xValues,
                datasets: [{

                    backgroundColor: "#5cb85c",
                    data: tValues

                },
                    {
                        backgroundColor: "#f0ad4e",
                        data: yValues
                    }
                ]
            },
            options: {
                legend: {display: false},
                title: {
                    display: true,
                    text: "Все задачи"
                }
            }
        });
    </script>

</form>

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

