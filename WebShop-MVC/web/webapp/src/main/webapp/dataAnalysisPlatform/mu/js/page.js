//获取地址栏参数
$(function(){
    function getUrlParms(name){
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if(r!=null)
            return unescape(r[2]);
        return null;
    }
    var id = getUrlParms("id");
    if(id == 1){
        $('.center_text').css('display', 'none');
        $('.t_cos1').css('display', 'block');
        initCreateBiao1('chart_1');
    }
    if(id == 2){
        $('.center_text').css('display', 'none');
        $('.t_cos1').css('display', 'block');
        initCreateBiao2('chart_1');
    }
    if(id == 3){
        $('.center_text').css('display', 'none');
        $('.t_cos1').css('display', 'block');
        initCreateBiao3('chart_1');
    }
    if(id == 4){
        $('.center_text').css('display', 'none');
        $('.t_cos1').css('display', 'block');
        initCreateBiao4('chart_1');
    }if(id == 5){
        $('.center_text').css('display', 'none');
        $('.t_cos1').css('display', 'block');
        initCreateBiao5('chart_1');
    }if(id == 6){
        $('.center_text').css('display', 'none');
        $('.t_cos1').css('display', 'block');
        initCreateBiao6('chart_1');
    }if(id == 7){
        $('.center_text').css('display', 'none');
        $('.t_cos1').css('display', 'block');
        initCreateBiao7('chart_1');
    }
    if(id == 8){
        $('.center_text').css('display', 'none');
        $('.t_cos1').css('display', 'block');
        initCreateBiao8('chart_1');
    }
    if(id == 9){
        $('.center_text').css('display', 'none');
        $('.t_cos1').css('display', 'block');
        initCreateBiao9('chart_1');
    }
    if(id == 10){
        $('.center_text').css('display', 'none');
        $('.t_cos1').css('display', 'block');
        initCreateBiao10('chart_1');
    }
    if(id == 11){
        $('.center_text').css('display', 'none');
        $('.t_cos1').css('display', 'block');
        initCreateBiao11('chart_1');
    }
    if(id == 12){
        $('.center_text').css('display', 'none');
        $('.t_cos1').css('display', 'block');
        initCreateBiao12('chart_1');
    }
    if(id == 13){
        $('.center_text').css('display', 'none');
        $('.t_cos1').css('display', 'block');
        initCreateBiao13('chart_1');
    }
    if(id == 14){
        $('.center_text').css('display', 'none');
        $('.t_cos1').css('display', 'block');
        initCreateBiao14('chart_1');
    }
    if(id == 15){
        $('.center_text').css('display', 'none');
        $('.t_cos1').css('display', 'block');
        initCreateBiao15('chart_1');
    }
    if(id == 16){
        $('.center_text').css('display', 'none');
        $('.t_cos1').css('display', 'block');
        initCreateBiao16('chart_1');
    }
    if(id == 17){
        $('.center_text').css('display', 'none');
        $('.t_cos1').css('display', 'block');
        initCreateBiao17('chart_1');
    }
    if(id == 18){
        $('.center_text').css('display', 'none');
        $('.t_cos1').css('display', 'block');
        initCreateBiao18('chart_1');
    }
    if(id == 19){
        $('.center_text').css('display', 'none');
        $('.t_cos1').css('display', 'block');
        initCreateBiao19('chart_1');
    }
    if(id == 20){
        $('.center_text').css('display', 'none');
        $('.t_cos1').css('display', 'block');
        initCreateBiao20('chart_1');
    }
    if(id == 21){
        $('.center_text').css('display', 'none');
        $('.t_cos1').css('display', 'block');
        initCreateBiao21('chart_1');
    }
    if(id == 22){
        $('.center_text').css('display', 'none');
        $('.t_cos1').css('display', 'block');
        initCreateBiao22('chart_1');
    }
    if(id == 23){
        $('.center_text').css('display', 'none');
        $('.t_cos1').css('display', 'block');
        initCreateBiao23('chart_1');
    }
});



