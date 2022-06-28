$(document).ready(function () {
    loadHolidayTable();
    loadUsers();

    $("#new-member-button").click(function (event) {
        postMember(event);
    });

    $("#load-holidays").click(function (event) {
        loadHolidayTable();
    });

    $("#submit-holiday").click(function (event) {
        postHoliday(event);
    });

    $("#add-priority").click(function (event) {
        addPriority(event);
    });

    $("#add-vacation-table").click(function (event) {
        addVacationTable(event);
    });

    $("#delete-user-button").click(function (event) {
        deleteUser(event);
    });

});

function loadHolidayTable() {
    var table = $('#holiday-table').DataTable({
        destroy: true,

        "ajax": {
            "url": "http://localhost:8080/api/holiday",
            "dataSrc": ""
        },

        "columns": [
            {
                className: 'dt-control',
                orderable: false,
                data: null,
                defaultContent: '',
            },
            {"data": "title"},
            {"data": "startDate"},
            {"data": "endDate"},
            {"data": "id"},
        ],
        "fnInitComplete": () => {
            $('#holiday-table-body > tr').click(function() {
                var id = table.row($(this)).data().id;
                var title = table.row($(this)).data().title;
                var startDate = table.row($(this)).data().startDate;
                var endDate = table.row($(this)).data().endDate;

                loadVacationTable(id,title,startDate,endDate );
            });
        }
        
    });

}

var loadedHolidayId = -1;
var vacationTable;

function loadVacationTable(id, title, startDate, endDate) {
    vacationTable = $('#vacation-table').DataTable({
        destroy: true,
        "ajax": {
            "url": "http://localhost:8080/api/holiday/ " + id + "/vacations",
            "dataSrc": ""
        },

        "columns": [

            {"data": "id"},
            {"data": "title"},
            {"data": "destination"},
            {"data": "description"},
            {"data": "priority"},
        ],
        "fnInitComplete": () => {

            console.log("vac loaded");
            $("#holidayTitle").val(title);
            $("#holidayStartDate").val(startDate);
            $("#holidayEndDate").val(endDate);
            $("#holidayId").val(id);
            $("#holidayModal").modal('show');
            $('#vacation-table-body > tr').click(function () {
                var data = vacationTable.row($(this)).data();
                console.log(title);
                $("#vacationTitle").val(data.title);
                $("#vacationDescription").val(data.description);
                $("#vacationDestination").val(data.destination);
                $("#vacationId").val(data.id);
                $("#vacationModal").modal('show');
            });
            loadedHolidayId = id;
        }
    });
}

function postMember(event) {
    var formData = {
        'username': $('input[name=username]').val(),
        'dateOfBirth': $('input[name=dateOfBirth]').val(),
        'firstname': $('input[name=firstname]').val(),
        'lastname': $('input[name=lastname]').val(),
    };

    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: 'http://localhost:8080/api/member',
        data: JSON.stringify(formData),

        success: function (data, textSatus, jQxhr) {
        },

        error: function (jqXhr, textStatus, errorThrown) {
            console.log(errorThrown);
        },
    });
    event.preventDefault();
}

function postVacation(event) {
    var holidayId = $("#holidayId").val();

    var url = 'http://localhost:8080/api/holiday/' + holidayId + '/vacations';

    var data = {
        'title': $('#vacationTitle').val(),
        'destination': $("#vacationDestination").val(),
        'description': $("#vacationDescription").val(),
        'priority': '0',
    };

    console.log(data);

    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: url,
        data: JSON.stringify(data),

        success: function (data, textSatus, jQxhr) {
        },

        error: function (jqXhr, textStatus, errorThrown) {
            console.log(errorThrown);
        },
    });
}

function postHoliday(event) {
    
    var data = {
        title: $('input[name=title]').val(),
        startDate: "2020-12-23",
        endDate: "2021-01-09",
        vacations: []
    };
    var vacations = document.getElementsByClassName("vacation");
    for (var i = 0; i < vacations.length; i++) {
        data.vacations[i] = {
            "title": $('input[name=vacation-title]').val(),
            "destination": $('input[name=vacation-destination]').val(),
            "description": $('input[name=vacation-description]').val(),
            "priority": 0
        }
    }

    console.log(data);

    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: 'http://localhost:8080/api/holiday',
        data: JSON.stringify(data),

        success: function (data, textSatus, jQxhr) {   
                     loadHolidayTable();


        },

        error: function (jqXhr, textStatus, errorThrown) {
            console.log(errorThrown);
        },
    });
    event.preventDefault();
}

function deleteHoliday(event) {
    var id = $("#holidayId").val();
    console.log(id);
    var url = 'http://localhost:8080/api/holiday/' + id;
    console.log(url);
        $.ajax({
            type: 'DELETE',
            contentType: 'application/json',
            url: url,

            success: function (data, textSatus, jQxhr) {
                loadHolidayTable();
            },

            error: function (jqXhr, textStatus, errorThrown) {
                console.log(errorThrown);
            },
        });
    
}


function loadUsers() {
    let dropdown = $('#username-dropdown');

    dropdown.empty();

    dropdown.append('<option selected="true" disabled>Username</option>');
    dropdown.prop('selectedIndex', 0);

    const url = 'http://localhost:8080/api/member';

    $.getJSON(url, function (data) {
        $.each(data, function (key, entry) {
            dropdown.append($('<option></option>').attr('value', entry.id).text(entry.username));
        })
    });
}


function addVacationTable() {
    let dropdown = $('#new-holiday');
    $("#vacation1").clone().insertAfter("div.vacation:last");
}

function addVacation() {
        $("#vacationModal").modal('show');
}


function addPriority() {
 

    var username = document.getElementById('username-dropdown');
    var memberId = username.options[username.selectedIndex].value;

    if(memberId == "Username"){
        alert("No user was selected!");
        return; 
    }

    var priority=$("#vacationPriority").val();
    var vacationId=$("#vacationId").val();
    
    var url = 'http://localhost:8080/api/member/' + memberId + '/priorities?vacationId=' + vacationId + '&priority=' + priority;
    console.log(url);
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: url,

        success: function (data, textStatus, jQxhr) {
            console.log(textStatus);
                },

        error: function (jqXhr, textStatus, errorThrown) {
            if(jqXhr.status == 303){
                alert("User already voted");

            }
            console.log(textStatus);

        },
    });
    event.preventDefault();
}

$(function () {
    var $select = $(".priority-select");
    for (i = 1; i <= 10; i++) {
        $select.append($('<option></option>').val(i).html(i))
    }
});

function deleteUser()
{
    var username = document.getElementById('username-dropdown');
    var memberId = username.options[username.selectedIndex].value;
    var url = 'http://localhost:8080/api/member/' + memberId;
    console.log(url);
        $.ajax({
            type: 'DELETE',
            contentType: 'application/json',
            url: url,

            success: function (data, textSatus, jQxhr) {
                loadHolidayTable();
            },

            error: function (jqXhr, textStatus, errorThrown) {
                console.log(errorThrown);
            },
        });
}