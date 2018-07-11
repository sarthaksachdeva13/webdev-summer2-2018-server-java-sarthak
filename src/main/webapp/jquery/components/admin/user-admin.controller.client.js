(function () {
    jQuery(main);
    var tbody = $('tbody');
    var tableTemplate = $('.wbdv-template');
    var inputFormGroup = $('.wbdv-form');
    var userServiceClient = new UserServiceClient();

    function main() {
        userServiceClient
            .findAllUsers()
            .then(renderUsers);
    }

    function renderUsers(users) {
        tbody.empty();
        for (var i = 0; i < users.length; i++) {
            var user = users[i];
            // https://api.jquery.com/clone/
            // // Clone creates a deep copy of the set of matched elements. https://api.jquery.com/clone/
            // var clonedTableTemplate = tableTemplate.clone();
            // clonedTableTemplate.attr('id', user.id)
            // clonedTableTemplate.find('.wbdv-remove').click(deleteUser);
            // clonedTableTemplate.find('.wbdv-edit').click(findUserById)
            // clonedTableTemplate.find('.wbdv-username')
            //     .html(user.username);
            // clonedTableTemplate.find('.wbdv-first-name').html(user.firstName);
            // clonedTableTemplate.find('.wbdv-last-name').html(user.lastName);
            // clonedTableTemplate.find('.wbdv-role').html(user.role);
            // tbody.append(clone);


            var tr = $('<tr>');
            var td = $('<td>');
            td.append(user.username);
            tr.append(td);

            td = $('<td>');
            td.append('*******');
            tr.append(td);

            td = $('<td>');
            td.append(user.firstName);
            tr.append(td);

            td = $('<td>');
            td.append(user.lastName);
            tr.append(td);

            td = $('<td>');
            td.append(user.email);
            tr.append(td);

            td = $('<td>');
            td.append(user.phone);
            tr.append(td);


            td = $('<td>');
            td.append(user.dateOfBirth);
            tr.append(td);

            td = $('<td>');
            td.append(user.role);
            tr.append(td);

            td = $('<td>');
            var deleteBtn = $('<i class="fa-2x fas fa-trash-alt"></i>');
            var editBtn = $('<i class="fa-2x fas fa-edit"></i>');
            deleteBtn.click(deleteUser);
            editBtn.click(editUser);
            deleteBtn.attr('id', user.id);
            editBtn.attr('id', user.id);
            td.append(deleteBtn);
            td.append(editBtn);
            tr.append(td);
            tr.appendTo(tbody);
        }
    }

    function deleteUser(event) {
        console.log(event);
        var $button = $(event.currentTarget);
        var id = $button.attr('id');

        userServiceClient
            .deleteUser(id)
            .then(function () {
                userServiceClient
                    .findAllUsers()
                    .then(renderUsers);
            });
    }

})();