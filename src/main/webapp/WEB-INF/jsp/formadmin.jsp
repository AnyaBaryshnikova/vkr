<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<div class="row pb-5">
    <nav class="navbar navbar-expand-lg fixed-top navbar-light bg-warning">
        <a class="navbar-brand" href="#"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link text-white" href="/projectsadmin">Проекты <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white" href="/tasksadmin">Задачи </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white" href="/employees">Сотрудники </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white" href="/workinggroups">Рабочие группы </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white" href="/dashboardadmin">Отчет </a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a class="nav-link text-white my-2 my-sm-0"
                       href="/logout">Выйти</a>
                </li>
            </ul>
        </div>
    </nav>
</div>
</body>
</html>
