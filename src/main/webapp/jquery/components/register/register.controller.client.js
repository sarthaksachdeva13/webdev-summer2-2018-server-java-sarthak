(function () {

    $(init);

    let userServiceClient = new UserServiceClient();

    function init() {
        $("#registerBtn").click(register);
    }

    function register() {
        let username = $("#username").val();
        let password = $("#password").val();
        let password2 = $("#password2").val();
        if (password !== password2) {
            alert("Enter the same password");
        }
        let user =
            {
                "username": username,
                "password": password,
                "password2": password2
            };
        userServiceClient.register(user)
            .then(function () {
                window.location.href = "../profile/profile.template.client.html";
            });
    }

})();