(function () {

    $(main)

    var userServiceClient = new UserServiceClient();
    var currentUser;

    function main() {
        userServiceClient.getProfile().then(renderProfile);
        $("#updateBtn").click(updateUser);
        $("#logoutBtn").click(logout);
    }

    function updateUser() {
        let username = $('#username').val();
        let firstName = $('#firstName').val();
        let lastName = $('#lastName').val();
        let phone = $('#phone').val();
        let email = $('#email').val();
        let dateOfBirth = $('#dateOfBirth').val();
        let role = $('#role').val();
        let dob = new Date(dateOfBirth);

        currentUser =
            {
                "username": username,
                "firstName": firstName,
                "lastName": lastName,
                "phone": phone,
                "email": email,
                "dateOfBirth": dob,
                "role": role
            };


        userServiceClient.updateProfile(currentUser)
            .then(function (response) {
                alert("Profile Updated Successful");
                renderProfile(response)
            });
    }

    function logout() {
        userServiceClient.logout().then(function (response) {
            window.location.href = "login.template.client.html";
        });
    }

    function renderProfile(user) {
        $('#username').val(user.username);
        $('#firstName').val(user.firstName);
        $('#lastName').val(user.lastName);
        $('#phone').val(user.phone);
        $('#email').val(user.email);
        $('#dateOfBirth').val(user.dateOfBirth);
        $("#role").val(user.role);
    }

})();