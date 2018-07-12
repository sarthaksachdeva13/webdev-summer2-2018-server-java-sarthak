(function () {
    $(main);
    let tbody = $("tbody");
    let template = $(".tableTemplate");
    let currentUserId;
    let userService = new UserServiceClient();

    function main() {
        findAllUsers();
        $("#addIcon").click(createUser);
        $("#updateIcon").click(updateUser);

    }

    function findAllUsers() {
        userService
            .findAllUsers()
            .then(renderUsers);
    }

    function updateUser() {
        let username = $("#usernameInput").val();
        let password = $("#passwordInput").val();
        let firstName = $("#firstNameInput").val();
        let lastName = $("#lastNameInput").val();
        let role = $("#roleInput").val();
        let user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role: role,
            userId: currentUserId
        };

        userService
            .updateUser(user)
            .then(findAllUsers);

    }


    function renderUsers(users) {
        tbody.empty();
        for (let i = 0; i < users.length; i++) {
            let user = users[i];
            let clone = template.clone();
            clone.attr('id', user.id);
            clone.find(".templateUsername").html(user.username);
            clone.find(".templateFirstName").html(user.firstName);
            clone.find(".templateLastName").html(user.lastName);
            clone.find(".templateEmail").html(user.email);
            clone.find(".templaterole").html(user.role);
            clone.find('#deleteIcon').click(deleteUser);
            clone.find('#editIcon').click(findUserById);
            tbody.append(clone);
        }
    }

    function createUser() {
        console.log("user created");
        let username = $("#usernameInput").val();
        let password = $("#passwordInput").val();
        let firstName = $("#firstNameInput").val();
        let lastName = $("#lastNameInput").val();
        let email = $("#emailInput").val();
        let role = $("#roleInput").val();

        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            email:email,
            role: role
        };

        userService
            .createUser(user)
            .then(findAllUsers);

    }

    function findUserById(event) {
        let editBtn = $(event.currentTarget);
        let userId = editBtn.parent().parent().parent().attr('id');
        userService
            .findUserById(userId)
            .then(renderUser);
        currentUserId = userId;
    }

    function renderUser(user) {
        $('#usernameInput').val(user.username);
        $('#passwordInput').val(user.password);
        $("#firstNameInput").val(user.firstName);
        $("#lastNameInput").val(user.lastName);
        $("#emailInput").val(user.email);
        $("#roleInput").val(user.role);
    }

    function deleteUser(event) {
        let deleteBtn = $(event.currentTarget);
        let userId = deleteBtn.parent().parent().parent().attr('id');
        userService
            .deleteUser(userId)
            .then(findAllUsers);

    }


})();