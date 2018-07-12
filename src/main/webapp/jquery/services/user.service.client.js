function UserServiceClient() {
    this.findAllUsers = findAllUsers;
    this.createUser = createUser;
    this.deleteUser = deleteUser;
    this.findUserById = findUserById;
    this.updateUser = updateUser;
    this.register = register;
    this.login = login;
    this.getProfile = getProfile;
    this.updateProfile = updateProfile;
    this.logout = logout;
    this.url = 'https://sarthakwebdev.herokuapp.com/api';
    var self = this;

    function findAllUsers() {
        return fetch(self.url + "/user")
            .then(function (response) {
                return response.json();
            });
    }

    function updateUser(user) {
        return fetch(self.url + "/user"+ "/"+ user.userId, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function createUser(user) {
        return fetch(self.url + "/user", {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function deleteUser(userId) {
        return fetch(self.url + "/user"+"/"+ userId,{
            method:'delete'
        });

    }

    function findUserById(userId) {
        return fetch(self.url + "/user"+"/"+ userId)
            .then(function(response){
                return response.json();
            });
    }

    function register(user) {
        return fetch(self.url + "/register", {
            method: 'post',
            credentials:'include',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) {
            return response.json();
        });
    }

    function login(user) {
        return fetch(self.url + "/login",{
            method :'post',
            credentials:'include',
            body:JSON.stringify(user),
            headers:{
                'content-type':'application/json'
            }
        }).then(function (response) {
            return response.json();
        });
    }

    function getProfile() {
        return fetch(self.url + "/profile",{
            credentials:'include'
        }).then(function (response) {
            return response.json();
        });

    }

    function updateProfile(user) {
        return fetch(self.url + "/profile",{
            method :'put',
            credentials:'include',
            body:JSON.stringify(user),
            headers:{
                'content-type':'application/json'
            }
        }).then(function (response) {
            return response.json();
        });

    }

    function logout() {
        return fetch(self.url + "/logout",{
            method :'post',
            credentials:'include'
        });
    }
}

