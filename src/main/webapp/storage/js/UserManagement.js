var userList = [];

// load data body of table
function loadUserList() {
    $.ajax({
        url: "/FlightTicketManagement/api/users",
        method: "GET",
        contentType: "application/json; charset=utf-8",
        async: false,
        dataType: "json",
        success: function(response) {
            let htmlStr = ``;
            $.each(response,function(index,value){
                userList.push(value);
                // Role List
                let roleNameList = "";
                value.roles.forEach(role => roleNameList += role.name + " ");
                roleNameList = roleNameList.replaceAll("ROLE_","").trim().replaceAll(" ", ", ");

                htmlStr += 
                `<tr class=${value.user_Id}>
                    <td>${value.user_Id}</td>
                    <td>${value.username}</td>
                    <td>${value.email}</td>
                    <td>${roleNameList}</td>
                    <td>
                        <button id="btnEdit" data-id=${value.user_Id} type="button" class="btn btn-info" data-toggle="modal" data-target="#updateUserModal"><i class="fas fa-edit"></i></button>&nbsp
                        <button id="btnDelete" data-id=${value.user_Id} type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
                    </td>
                </tr>`;
            });
            $("#tbodyData").html(htmlStr);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
}

loadUserList();


// edit user
$('#tbodyData').on('click', '#btnEdit', function() {
    // make empty input
    $("#inpUser_Id").val("")
    $("#inpFirstName").val("")
    $("#inpLastName").val("")
    $("#inpBirthday").val("")
    $("#inpAddress").val("")
    $("#inpPhone").val("")
    $("#inpUsername").val("")
    $("#inpPassword").val("")
    $("#inpEmail").val("")
    $("input[type=checkbox]#inpRole").each(function () {
       this.checked = false;
    });
    $("#inpEnable").prop('checked',false); 

    // fill input with value
    userList.forEach(user => {
        if (user.user_Id == $(this).data("id")){
            $("#inpUser_Id").val(user.user_Id)
            $("#inpFirstName").val(user.firstName)
            $("#inpLastName").val(user.lastName)
            $("#inpBirthday").val(user.birthDay)
            $("#inpAddress").val(user.address)
            $("#inpPhone").val(user.phone)
            $("#inpUsername").val(user.username)
            $("#inpPassword").val(user.password)
            $("#inpEmail").val(user.email)
            user.roles.forEach(role => {
                $("input[type=checkbox]#inpRole").each(function () {
                    if (this.value == role.role_Id) this.checked = true;
                });
            })
            if (user.enable == 1){
                $("#inpEnable").prop('checked',true); 
            }
        }
    })
});

// update user
$('body').on('click', '#btnUpdate', function() {
    // set role list 
    let roleIdList = [];
    $("input[type=checkbox]#inpRole").each(function () {
        if (this.checked == true) roleIdList.push(this.value)
    });

    // set enable
    let enable = 0;
    $("input[type=checkbox]#inpEnable").each(function () {
        if (this.checked == true) enable = 1;
    });

    let user = {
        user_Id : $("#inpUser_Id").val(),
        firstName : $("#inpFirstName").val(),
        lastName : $("#inpLastName").val(),
        birthDay : $("#inpBirthday").val(),
        address : $("#inpAddress").val(),
        phone : $("#inpPhone").val(),
        username : $("#inpUsername").val(),
        password : $("#inpPassword").val(), 
        email : $("#inpEmail").val(),
        roleIdList : roleIdList,
        enable : enable
    };

    console.log(user);

    $.ajax({
        method: "PUT",
        url: "/FlightTicketManagement/api/users/" + $("#inpUser_Id").val(),
        contentType: "application/json",
        async: false,
        data: JSON.stringify(user),
        dataType: "json",
        success: function(response) {
            $('.close').click();
            $('.successToast').toast('show');
            userList = []; // empty the old list to add a new one
            loadUserList();
        },
        error: function(jqXHR, textStatus, errorThrown) {
            $('.failedToast').children('.toast-body').html('Unsuccessful')
            console.log(textStatus, errorThrown);
        }
    });
});

// delete user
$('#tbodyData').on('click', '#btnDelete', function() {
    if (confirm(`You want to delete user with id = ${$(this).data('id')}?`)) {
        let tr = $(this).closest('tr');
        $.ajax({
            method: "DELETE",
            url: "/FlightTicketManagement/api/users/" + $(this).data('id'),
            contentType: "application/json",
            async: false,
            success: function(response) {
                tr.remove();
                $('.successToast').toast('show');
            },
            error: function(jqXHR, textStatus, errorThrown) {
                $('.failedToast').children('.toast-body').html('Unsuccessful')
                console.log(textStatus, errorThrown);
            }
        });
    } else {}


});

$('#updateUserModal').on("keyup", function(event) {
    if (event.keyCode === 13) {
        event.preventDefault();
        $('#btnUpdate').click();
    }
});