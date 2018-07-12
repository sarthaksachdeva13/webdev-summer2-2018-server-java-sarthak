
(function () {
    $(main);
    var tbody;
    var template;
    var currentUserId;
    var userServiceClient = new UserServiceClient();

    function main() {
        tbody = $("tbody");
        template = $(".wbdv-template");
        findAllUsers();
        $(".addIcon").click(createUser);
        $(".updateIcon").click(updateUser);
    }

    function findAllUsers() {
        userServiceClient
            .findAllUsers()
            .then(renderUsers);
    }

    function updateUser() {
        var username = $("#UsernameFld").val();
        var password = $("#PasswordFld").val();
        var firstName =$("#FirstNameFld").val();
        var lastName = $("#LastNameFld").val();
        var role = $("#roleFld").val();

        var user ={
            username:username,
            password:password,
            firstName:firstName,
            lastName:lastName,
            role:role,
            userId:currentUserId
        };

        userServiceClient
            .updateUser(user)
            .then(findAllUsers);

    }


    function renderUsers(users) {
        tbody.empty();
        for (let i=0; i<users.length; i++)
        {
            var user = users[i];
            var clone = template.clone(true,true);
            clone.attr('id', user.id);
            clone.find("#templateUsername").html(user.username);
            clone.find("#templateFirstName").html(user.firstName);
            clone.find("#templateLastName").html(user.lastName);
            clone.find("#templateRole").html(user.role);
            clone.find('#deleteIcon').click(deleteUser);
            clone.find('#editIcon').click(findUserById);
            tbody.append(clone);
        }
    }

    function createUser() {
        var username = $("#UsernameFld").val();
        var password = $("#PasswordFld").val();
        var firstName =$("#FirstNameFld").val();
        var lastName = $("#LastNameFld").val();
        var role = $("#roleFld").val();
        var user ={
            username:username,
            password:password,
            firstName:firstName,
            lastName:lastName,
            role:role
        };

        userServiceClient
            .createUser(user)
            .then(findAllUsers);

    }

    function findUserById(event) {
        var editBtn = $(event.currentTarget);
        var userId = editBtn
            .parent()
            .parent()
            .parent()
            .attr('id');

        userServiceClient
            .findUserById(userId)
            .then(renderUser);
        currentUserId = userId;
        //$("#userIdFld").val(userId);



    }

    function renderUser(user) {
        console.log(user.username);
        $('#UsernameFld').val(user.username);
        $('#PasswordFld').val(user.password);
        $("#FirstNameFld").val(user.firstName);
        $("#LastNameFld").val(user.lastName);
        $("#roleFld").val(user.role);

    }
    function deleteUser(event) {
        //console.log("inside delete user");
        var deleteBtn = $(event.currentTarget);
        var userId = deleteBtn
            .parent()
            .parent()
            .parent()
            .attr('id');

        userServiceClient
            .deleteUser(userId)
            .then(findAllUsers);


    }



})();