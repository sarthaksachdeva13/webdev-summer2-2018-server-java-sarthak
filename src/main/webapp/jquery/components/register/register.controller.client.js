(function () {

    $(init);

    let userServiceClient = new UserServiceClient();
    let flag = false;

    function init() {
        if(flag){
            $('.alert').toggleClass('hide');
        }
        $("#registerBtn").click(register);
    }

    function register() {
        let username = $("#username").val(),
            password = $("#password").val(),
            password2 = $("#password2").val();
        let user =
            {
                "username": username,
                "password": password,
                "password2": password2
            };
        userServiceClient.register(user)
            .then(function () {
                if (user.password !== user.password2) {
                    $('.alert').removeClass('hide').addClass('show');
                }
                else{
                    window.location.href = "../profile/profile.template.client.html";
                }
            });
    }

})();