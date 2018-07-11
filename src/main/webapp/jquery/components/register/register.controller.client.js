(function () {
    var registerBtn = $('#registerBtn');
    registerBtn.click(registerHandler);

    var usernameFld = $('#username');
    var passwordFld = $('#password');
    var password2Fld = $('#password2');


    function registerHandler() {
        var usernameStr = usernameFld.val();
        var passwordStr = passwordFld.val();
        var password2Str = password2Fld.val();


        var userObj = {
            username: usernameStr,
            password: passwordStr,
            password2: password2Str
        }

        var userObjStr = JSON.stringify(userObj);

        fetch('/register', {
            method: 'post',
            body: userObjStr,
            headers:
                {
                    'Content-type': 'application/json'
                },
            'credentials': 'include'
        }).then(registrationSuccessful, registrationFailed);

        function registrationSuccessful() {
            window.location.href = ('/jquery/components/profile/profile.template.client.html');
        }

        function registrationFailed() {
            alert('oops');
        }
    }
})();