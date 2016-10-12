/**
 * Created by Administrator on 2016/10/12.
 */
function selectNav(){
    var href = window.location.href;
    var n = href.split("/").pop();
    if (n == "command"){
        $('a#command').parent().addClass("active");
    } else if (n == "manager"){
        $('a#manager').parent().addClass("active");
    } else{
        $('a#home').parent().addClass("active");
    }
}
selectNav();