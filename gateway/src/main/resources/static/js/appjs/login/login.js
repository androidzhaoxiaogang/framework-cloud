/**
 * Created by Administrator on 2017/11/10.
 */


function login() {
    var username = $("#username").val();
    var password = $("#password").val();

    if (requestOauthToken(username, password)) {
        parent.location.href = '/index.html';
    } else {
        //alert("Something went wrong. Please, check your credentials");
        layer.msg("用户名或者登录密码错误");
    }
}

