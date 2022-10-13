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


    <title>Отчет</title>
</head>

<body>
<%@ include file="formadmin.jsp" %>
<input type="hidden" id="allProjects" type="hidden" value="${projects}"/>
<input type="hidden" id="allTimes" type="hidden" value="${times}"/>
<input type="hidden" id="timePlan" type="hidden" value="${timesPlan}"/>


<div class="align-items-center justify-content-center">
    <p class="text-center">
    <h4>Общая информация:</h4>
    <h5> Количество проектов: ${allProjamount}</h5>
    <h6> Количество новых проектов: ${newProjamount}</h6>
    <h6> Количество проектов в работе: ${openProjamount}</h6>
    <h6> Количество закрытых проектов: ${closedProjamount}</h6>
    <h5> Количество сотрудников: ${allUsersamount}</h5>
    <h6> Количество сотрудников junior: ${junUsersamount}</h6>
    <h6> Количество сотрудников senior: ${senUsersamount}</h6>
    <h6> Количество сотрудников leader: ${leadUsersamount}</h6>
    </p>

    <div class="form-group row justify-content-center">
        <div class="w-20 p-3">
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-warning .col-md-3 .ml-auto" data-toggle="modal"
                    data-target="#projectModal">
                Сформировать отчет по проекту
            </button>
        </div>
        <!-- Modal -->
        <form action="/showAllTasks" method="POST">
            <div class="modal fade" id="projectModal" tabindex="-1" role="dialog" aria-labelledby="projectModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="projectModalLabel">Выберите проект</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="input-group mb-3">
                                <select class="form-select" aria-label="Default select example" name="selectProject">
                                    <option selected>Проект</option>
                                    <c:forEach items="${allPro}" var="pr">
                                        <option value="${pr.id}">${pr.projectname} </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-outline-warning" data-dismiss="modal">Закрыть</button>
                            <button type="submit" class="btn btn-warning">Показать</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>


        <div class="w-20 p-3">
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-warning .col-md-3 .ml-auto" data-toggle="modal"
                    data-target="#allprojModal">
                Сформировать отчет по всем проектам
            </button>
        </div>
        <!-- Modal -->
        <form action="/allProjReport" method="POST">
            <div class="modal fade" id="allprojModal" tabindex="-1" role="dialog" aria-labelledby="allprojModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="allprojModalLabel">Выберите период</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">

                            <section class="section-preview">
                                <div class="custom-control custom-radio custom-control-inline">
                                    <input type="radio" class="custom-control-input" id="1term" name="period"
                                           value="1 квартал">
                                    <label class="custom-control-label" for="1term">1 квартал</label>
                                </div>
                                <br>
                                <div class="custom-control custom-radio custom-control-inline">
                                    <input type="radio" class="custom-control-input" id="2term" name="period"
                                           value="2 квартал">
                                    <label class="custom-control-label" for="2term">2 квартал</label>
                                </div>
                                <br>
                                <div class="custom-control custom-radio custom-control-inline">
                                    <input type="radio" class="custom-control-input" id="3term" name="period"
                                           value="3 картал">
                                    <label class="custom-control-label" for="3term">3 картал</label>
                                </div>
                                <br>
                                <div class="custom-control custom-radio custom-control-inline">
                                    <input type="radio" class="custom-control-input" id="4term" name="period"
                                           value="4 картал">
                                    <label class="custom-control-label" for="4term">4 картал</label>
                                </div>
                                <br><div class="custom-control custom-radio custom-control-inline">
                                    <input type="radio" class="custom-control-input" id="month" name="period"
                                           value="4 картал">
                                    <label class="custom-control-label" for="month" checked>Текущий месяц</label>
                                </div>
                                <br>
                                <div class="custom-control custom-radio custom-control-inline">
                                    <input type="radio" class="custom-control-input" id="dateofstart" name="period"
                                           value="Дата начала проекта не раньше">
                                    <label class="custom-control-label" for="dateofstart">Дата начала проекта не
                                        раньше:</label>
                                </div>
                            </section>
                            <div>
                                <input type="date" id="startDate" name="startDate"
                                       placeholder="Дата начала проета">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-outline-warning" data-dismiss="modal">Закрыть</button>
                            <button type="submit" class="btn btn-warning">Показать</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>

        <div class="w-20 p-3">
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-warning .col-md-3 .ml-auto" data-toggle="modal"
                    data-target="#exampleModal">
                Сформировать отчет по сотруднику
            </button>
        </div>
        <!-- Modal -->
        <form action="/showAllUsers" method="POST">
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Выберите сотрудника</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="input-group mb-3">
                                <select class="form-select" aria-label="Default select example" name="selectU">
                                    <option selected>Пользователь</option>
                                    <c:forEach items="${allU}" var="user">
                                        <option value="${user.id}">${user.username} </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="input-group mb-3">
                                <select class="form-select" aria-label="Default select example" name="selectP">
                                    <option selected>Проект</option>
                                    <c:forEach items="${allP}" var="pr">
                                        <option value="${pr.id}">${pr.projectname} </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-outline-warning" data-dismiss="modal">Закрыть</button>
                            <button type="submit" class="btn btn-warning">Показать</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

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

