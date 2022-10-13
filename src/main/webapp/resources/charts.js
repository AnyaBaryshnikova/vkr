$(document).ready(function () {

        var transaction_x = projects.map(x => x["projectname"])
        var transaction_y1 = expenses.map(x => x["timefact"])
        var transaction_y2 = expenses.map(x => x["timeplan"])

        var barChartData = {
            labels: ["Jan", "Feb", "march"],
            // labels: transaction_x,
            datasets: [
                {
                    fillColor: "rgba(210,37,37,0.5)",
                    strokeColor: "rgba(220, 220, 220, 1)",
                    data: [65, 70, 78]
                    // data: transaction_y1
                },

                {
                    fillColor: "rgba(17,80,236,0.5)",
                    strokeColor: "rgb(70,105,184)",
                    data: [90, 100, 178]
                    // data: transaction_y2
                }
            ]

        };


        new Chart(document.getElementById("bar").getContext("2d")).Bar(barChartData);
    }
)