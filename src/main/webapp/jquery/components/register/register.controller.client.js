(function () {

    $(init)

    var userServiceClient = new UserServiceClient();

    function init() {
        $("#registerBtn").click(register);
    }

    function register(event) {
        event.preventDefault();
        var username = $("#usernameFld").val();
        var password = $("#passwordFld").val();
        var password2 = $("#password2").val();

        var user =
            {
                "username": username,
                "password": password,
                "password2": password2
            };

        userServiceClient.register(user)
            .then(function(response) {
                window.location.href = "../profile/profile.template.client.html";
            });
    }

})();