const usersURL = 'http://localhost:8080/users'

getAllUsers()

function getRequest(url) {

    return fetch(url).then(response => {
        if (response.ok) {
            return response.json()
        } else {
            return response.json().then(error => {
                const e = new Error('Что то пошло не так...')
                e.data = error
                throw e
            })
        }
    })
}

function sendRequest(method, url, body = null) {

    return fetch(url, {
        method: method,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(body),
        contentType: 'application/json'

    }).then(response => {
        console.log(response);
        if (response.ok) {
            return response.json()
        } else {
            return response.json().then(error => {
                const e = new Error('Что то пошло не так...')
                e.data = error
                throw e
            })
        }
    })
}

function getAllUsers() {
    getRequest(usersURL)
        // .then(data => console.log(data))
        .then(users => {
            console.log(users)
            for (let user of users) {
                $('#usersTable').append(createRow(user))
            }
        })
        .catch(err => alert('не удалось получить список юзверей' + err))
    // alert('@            DONE          @')
}

function createRow(user) {
    // console.log('try to create and append rows')
    let userRoles = ''
    switch (user.rolesIndex) {
        case 1 :
            userRoles = "[ADMIN]"
            break;
        case 2 :
            userRoles = "[USER]"
            break;
        case 3 :
            userRoles = "[ADMIN, USER]"
            break;

    }

    let usersTableRow = `
    <tr id="${user.id}">
        <td>${user.id}</td>
        <td>${user.name}</td>
        <td>${user.surname}</td>
        <td>${user.age}</td>
        <td>${user.email}</td>
        <td>${userRoles}</td>
        <td>    
            <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                <button type="submit" class="btn btn-info"
                    
                    data-ed-id="${user.id}"
                    data-ed-name="${user.name}"
                    data-ed-surname="${user.surname}"
                    data-ed-email="${user.email}"
                    data-ed-password="${user.password}"
                    data-ed-age="${user.age}"
                    data-ed-roles="${user.rolesIndex}">Edit
                </button>
            </div>
        </td>
        <td>
            <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                <button class="btn btn-danger" data-toggle="modal"
                    
                    data-del-id="${user.id}"
                    data-del-name="${user.name}"
                    data-del-surname="${user.surname}"
                    data-del-email="${user.email}"
                    data-del-password="${user.password}"
                    data-del-age="${user.age}"
                    data-del-roles="${user.rolesIndex}">Delete
                </button>
            </div>
        </td>
    </tr>
    `
    //console.log('row created')

    return usersTableRow
}

//EDIT MODAL
$(document).on('click', '.btn-group .btn-info', function (event) {

    event.preventDefault();

    //alert('try to open modal')

    let button = $(this)
    let id = button.data('edId')
    let name = button.data('edName')
    let surname = button.data('edSurname')
    let age = button.data('edAge')
    let email = button.data('edEmail')
    let password = button.data('edPassword')
    let roles = button.data('edRoles')

    let myModal = $('#editModal')

    $('#editId').val(id)
    $('#editName').val(name)
    $('#editSurname').val(surname)
    $('#editAge').val(age)
    $('#editEmail').val(email)
    $('#editPassword').val(password)

    $('#editRoles').val(roles)


    myModal.modal('show')
});


// DELETE MODAL

$(document).on('click', '.btn-group .btn-danger', function (event) {

    event.preventDefault();

    let delbutton = $(this)
    let id = delbutton.data('delId')
    let name = delbutton.data('delName')
    let surname = delbutton.data('delSurname')
    let age = delbutton.data('delAge')
    let email = delbutton.data('delEmail')
    let password = delbutton.data('delPassword')
    let roles = delbutton.data('delRoles')

    let delModal = $('#deleteModal')

    $('#deleteId').val("" + id)
    $('#deleteName').val(name)
    $('#deleteSurname').val(surname)
    $('#deleteAge').val(age)
    $('#deleteEmail').val(email)
    $('#deletePassword').val(password)
    $('#deleteRoles').val(roles)


    delModal.modal('show')
})

//submit buttons

$(document).on('click', '#editSubmit', async (event) => {
    event.preventDefault();
    let userEditId = $('#editId').val();
    let editUser = {
        id: $('#editId').val(),
        name: $('#editName').val(),
        surname: $('#editSurname').val(),
        age: $('#editAge').val(),
        email: $('#editEmail').val(),
        password: $('#editPassword').val(),
        rolesIndex: $('#editRoles').val()
    }
    console.log(editUser)

    await sendRequest('PUT', usersURL, editUser).then(data => {
        let newRow = createRow(data)
        console.log(data)
        //console.log("newRow: " + newRow)
        $('#usersTable').find('#' + userEditId).replaceWith(newRow);
        $('#editModal').modal('hide');
        $('#v-pills-home-tab').tab('show');
    })
})

$(document).on('click', '#deleteSubmit', function (event) {
    event.preventDefault()
    let delUserId = $('#deleteId').val()
    const delURL = usersURL + '/' + delUserId
    console.log('DELETE URL: ' + delURL)
    fetch(delURL, {
        method: 'DELETE'
    }).then(() => {

        $('#' + delUserId).remove()
        $('#deleteModal').modal("hide")
        $("#v-pills-home-tab").tab('show')

    }).catch(error => {
        alert('Ошибка при удалении юзверя    ' + error)
    })
})

// new User

$(document).on('click', '#saveButton', function (event) {

    event.preventDefault()

    let name = $('#inputName')
    let surname = $('#inputSurname')
    let age = $('#inputAge')
    let email = $('#inputEmail')
    let pass = $('#inputPass')

    let roles = $('#selectRoles')

    let newUser = {
        name: name.val(),
        surname: surname.val(),
        age: age.val(),
        email: email.val(),
        password: pass.val(),
        rolesIndex: roles.val()
    }

    console.log(newUser);


    sendRequest('POST', usersURL, newUser).then(user => {
        $('#usersTable').append(createRow(user))
    })

    name.empty().val('')
    surname.empty().val('')
    age.empty().val('')
    email.empty().val('')
    pass.empty().val('')
    roles.val(1)

    $('#nav-home-tab').tab('show');


})