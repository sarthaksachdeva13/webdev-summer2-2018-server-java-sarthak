(function () {

    $(main);

    let userServiceClient = new UserServiceClient();
    let flag = false;

    function main() {
        if(flag){
            $('.alert').toggleClass('hide');
        }
        userServiceClient.getProfile().then(renderProfile);
        $("#updateBtn").click(updateUser);
        $("#logoutBtn").click(logout);
    }


    function updateUser() {
        let username = $('#username').val(),
            password = $('#password').val(),
            firstName = $('#firstName').val(),
            lastName = $('#lastName').val(),
            phoneNo = $('#phone').val(),
            email = $('#email').val(),
            dateOfBirth = $('#dateOfBirth').val(),
            role = $('#role').val();

        let currentUser =
            {
                "username": username,
                "password": password,
                "firstName": firstName,
                "lastName": lastName,
                "phoneNo": phoneNo,
                "email": email,
                "dateOfBirth": dateOfBirth,
                "role": role
            };
        userServiceClient.updateProfile(currentUser)
            .then(function (response) {
                if (currentUser !== undefined) {
                    $('.alert').removeClass('hide').addClass('show');
                }
                renderProfile(response)
            });
    }

    function renderProfile(user) {
        $('#username').val(user.username);
        $('#password').val(user.password);
        $('#firstName').val(user.firstName);
        $('#lastName').val(user.lastName);
        $('#phone').val(user.phoneNo);
        $('#email').val(user.email);
        $('#dateOfBirth').val(user.dateOfBirth);
        $("#role").val(user.role);
    }

    function logout() {
        userServiceClient.logout().then(function () {
            window.location.href = "../login/login.template.client.html";
        });
    }

})();