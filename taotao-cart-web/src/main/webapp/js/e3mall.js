var E3MALL = {
    checkLogin : function(){
        var _ticket = $.cookie("token");
        if(!_ticket){
            return ;
        }
        $.ajax({
            url : "http://localhost:7088/user/token/" + _ticket,
            dataType : "jsonp",
            type : "GET",
            success : function(data){
                if(data.status == 200){
                    var username = data.data.username;
                    var html = username + "，欢迎来到宜立方购物网！<a href=\"javascript:void(0)\" class=\"link-logout\" onclick='E3MALL.logOut()'>[退出]</a>";
                    $("#loginbar").html(html);
                }
            }
        });
    },
    logOut : function(){
        var token = $.cookie("token");
        if(!token){
            return ;
        }
        $.ajax({
            url : "http://localhost:7088/user/logout/" + token,
            dataType : "jsonp",
            type : "GET",
            success : function(data){
                console.log(data);
                if(data.status == 200){
                    window.location.reload();
                }
            }
        });
    }
}

$(function(){
    // 查看是否已经登录，如果已经登录查询登录信息
    E3MALL.checkLogin();
});