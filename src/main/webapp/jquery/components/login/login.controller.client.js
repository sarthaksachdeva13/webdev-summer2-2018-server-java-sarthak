(function () {

    $(main);
    let userServiceClient = new UserServiceClient();

    function main() {
        $("#loginBtn").click(login);
    }

    function login() {
        let username = $("#username").val(),
            password = $("#password").val();

        let user = {
            "username": username,
            "password": password
        };

        userServiceClient
            .login(user)
            .then(function(){
                window.location.href = "../profile/profile.template.client.html";
            });
    }
})();