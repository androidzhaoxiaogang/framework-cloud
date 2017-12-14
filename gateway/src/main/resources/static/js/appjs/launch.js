/**
 * Created by Administrator on 2017/11/10.
 */


function requestOauthToken(username, password) {

    var success = false;
    //var value = 'username=' + username + '&password=' + password + '&grant_type=password' + '&scope=ui';
    $.ajax({
        url: 'uaa/oauth/token',
        dataType: 'json',
        type: 'POST',
        // xhrFields: {
        //     withCredentials: true
        // },
        // crossDomain: true,
        headers: {'Authorization': 'Basic YnJvd3NlcjpzZWNyZXQ=',
            "Content-type": "application/x-www-form-urlencoded; charset=utf-8"
        },
        // data: value,
        async: false,
        data: {
            scope: 'ui',
            username: username,
            password: password,
            grant_type: 'password'
        },
        success: function (data) {
            localStorage.setItem('token', data.access_token);
            success = true;
        },
        error: function () {
            removeOauthTokenFromStorage();
        }
    });

    return success;
}

function getOauthTokenFromStorage() {
    return localStorage.getItem('token');
}

function removeOauthTokenFromStorage() {
    return localStorage.removeItem('token');
}

$(document).ready(function () {
    validateRule();
    //$("#signupForm").validate();
});

$.validator.setDefaults({
    submitHandler: function () {
        login();
    }
});

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i>";
    $("#signupForm").validate({
        rules: {
            username: {
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
            username: {
                required: icon + "请输入您的用户名",
            },
            password: {
                required: icon + "请输入您的密码",
            }
        }
    })
}