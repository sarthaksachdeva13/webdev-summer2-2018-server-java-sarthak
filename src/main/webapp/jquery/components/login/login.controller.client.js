(function () {

    $(main)

    var userServiceClient = new UserServiceClient();

    function main() {

        $("#loginBtn").click(login);

    }

    function login() {
        var username = $("#username").val();
        var password = $("#password").val();

        var user = {
            "username":username,
            "password":password
        };

        userServiceClient
            .login(user)
            .then(function (response) {
                window.location.href = "profile.template.client.html";
            });
    }
})();