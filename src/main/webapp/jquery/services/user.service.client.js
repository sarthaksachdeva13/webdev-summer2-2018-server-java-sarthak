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
    this.url = '/api';
    let self = this;


    //Registration
    function register(user) {
        return fetch(self.url + "/register", {
            method: 'post',
            credentials: 'include',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) {
            return response.json();
        });
    }


    // Login and logout
    function login(user) {
        return fetch(self.url + "/login", {
            method: 'post',
            credentials: 'include',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) {
            return response.json();
        });
    }

    function logout() {
        return fetch(self.url + "/logout", {
            method: 'post',
            credentials: 'include'
        });
    }


    // Update, create and delete
    function updateUser(user) {
        return fetch(self.url + "/user" + "/" + user.userId, {
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
        return fetch(self.url + "/user" + "/" + userId, {
            method: 'delete'
        });

    }


    //Find all users
    function findAllUsers() {
        return fetch(self.url + "/user")
            .then(function (response) {
                return response.json();
            });
    }


    //Find a user by ID
    function findUserById(userId) {
        return fetch(self.url + "/user" + "/" + userId)
            .then(function (response) {
                return response.json();
            });
    }


    //Get profile
    function getProfile() {
        return fetch(self.url + "/profile", {
            credentials: 'include'
        }).then(function (response) {
            return response.json();
        });

    }


    //Update profile
    function updateProfile(user) {
        return fetch(self.url + "/profile", {
            method: 'put',
            credentials: 'include',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) {
            return response.json();
        });

    }
}

