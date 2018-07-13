(function () {
    $(main);
    let tbody = $("tbody"),
        template = $(".tableTemplate"),
        uID,
        userService = new UserServiceClient();

    function main() {
        findAllUsers();
        $("#updateIcon").click(updateUser);
        $("#addIcon").click(createUser);
    }

    function findAllUsers() {
        userService
            .findAllUsers()
            .then(renderUsers);
    }

    function createUser() {
        let username = $("#usernameInput").val(),
            password = $("#passwordInput").val(),
            firstName = $("#firstNameInput").val(),
            lastName = $("#lastNameInput").val(),
            email = $("#emailInput").val(),
            role = $("#roleInput").val();

        let user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            email: email,
            role: role
        };

        userService
            .createUser(user)
            .then(findAllUsers);

    }

    function findUserById(event) {
        let editBtn = $(event.currentTarget),
            userId = editBtn.parent().parent().parent().attr('id');
        userService
            .findUserById(userId)
            .then(renderUser);
        uID = userId;
    }


    function updateUser() {
        let username = $("#usernameInput").val(),
            password = $("#passwordInput").val(),
            firstName = $("#firstNameInput").val(),
            lastName = $("#lastNameInput").val(),
            email = $("#emailInput").val(),
            role = $("#roleInput").val();
        let user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            email: email,
            role: role,
            userId: uID
        };
        console.log(user);
        userService
            .updateUser(user)
            .then(findAllUsers);
    }

    function deleteUser(event) {
        let deleteBtn = $(event.currentTarget),
            userId = deleteBtn.parent().parent().parent().attr('id');
        userService
            .deleteUser(userId)
            .then(findAllUsers);
    }

    function renderUser(user) {
        $('#usernameInput').val(user.username);
        $('#passwordInput').val(user.password);
        $("#firstNameInput").val(user.firstName);
        $("#lastNameInput").val(user.lastName);
        $("#emailInput").val(user.email);
        $("#roleInput").val(user.role);
    }

    function renderUsers(users) {
        tbody.empty();
        for (let i = 0; i < users.length; i++) {
            let user = users[i],
                clone = template.clone();
            clone.attr('id', user.id);
            clone.find(".templateUsername").html(user.username);
            clone.find(".templateFirstName").html(user.firstName);
            clone.find(".templateLastName").html(user.lastName);
            clone.find(".templateEmail").html(user.email);
            clone.find(".templateRole").html(user.role);
            clone.find('#editIcon').click(findUserById);
            clone.find('#deleteIcon').click(deleteUser);
            tbody.append(clone);
        }
    }
})();