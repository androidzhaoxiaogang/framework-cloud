$(window).load(function(){
    getMenuTree();
});


function getMenuTree() {

    var token = getOauthTokenFromStorage();
    var menus = null;

    if (token) {
        $.ajax({
            url: 'accounts/menus',
            datatype: 'json',
            type: 'get',
            headers: {'Authorization': 'Bearer ' + token},
            async: false,
            success: function (data) {
                menus = data;
            },
            error: function (res) {
                alert(res);
            } // removeOauthTokenFromStorage();
        });
    }

    return menus;

}