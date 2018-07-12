(function () {

    $(init)

    var userServiceClient = new UserServiceClient();

    function init() {
        $("#registerBtn").click(register);
    }

    function register() {
        var username = $("#username").val();
        var password = $("#password").val();
        var password2 = $("#password2").val();

        var user =
            {
                "username": username,
                "password": password,
                "password2": password2
            };

        userServiceClient.register(user)
            .then(function(response) {
                window.location.href = "jquery/components/profile/profile.template.client.html";
            });
    }

})();