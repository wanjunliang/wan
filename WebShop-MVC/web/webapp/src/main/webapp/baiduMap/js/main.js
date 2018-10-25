/**
 * Created by Administrator on 2018/5/11.
 */
// 百度地图API功能
$(document).ready(function(){
    forbid();
    selectTdzyfSifaPaimaiByLngLat(null,null,null,null);
    create_listener();
});


var mp = new BMap.Map("allmap");
mp.centerAndZoom(new BMap.Point(104.072116,30.663465), 15);
mp.enableScrollWheelZoom();
//加控件
// 添加带有定位的导航控件
var navigationControl = new BMap.NavigationControl({
    // 右下角
    anchor: BMAP_ANCHOR_BOTTOM_RIGHT,
    // LARGE类型
    type: BMAP_NAVIGATION_CONTROL_SMALL,
    // 启用显示定位
    // enableGeolocation: true
});

mp.addControl(navigationControl);


mp.addControl(new BMap.ScaleControl());
mp.addControl(new BMap.OverviewMapControl());
// mp.addControl(new BMap.MapTypeControl());
mp.addControl(new BMap.MapTypeControl({
    mapTypes:[
        BMAP_NORMAL_MAP,
        BMAP_HYBRID_MAP
    ]}));
mp.setCurrentCity("成都"); // 仅当设置城市信息时，MapTypeControl的切换功能才能可用
//全景
var opts2 = {offset: new BMap.Size(150, 5)};
var stCtrl = new BMap.PanoramaControl(opts2);
mp.addControl(stCtrl);

//搜索框
// 定义一个控件类,即function
function SearchControl(){
    // 默认停靠位置和偏移量
    this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
    this.defaultOffset = new BMap.Size(10, 10);
}

// 通过JavaScript的prototype属性继承于BMap.Control
SearchControl.prototype = new BMap.Control();

// 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
// 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
SearchControl.prototype.initialize = function(map){
    // 创建一个DOM元素
    var div = document.createElement("div");
    div.id="searchbox-container";
    var div2 = document.createElement("div");
    div2.className="searchbox-content";
        //搜索框
        var input=document.createElement("input")
        input.id="search_input";


    // input.style.height="38px";
        // input.style.width="328px";
        //搜索自动智能提示的列表
        var div_searchResultPanel=document.createElement("div");
        div_searchResultPanel.id="searchResultPanel";
        div_searchResultPanel.style.border="1px solid #C0C0C0";
        div_searchResultPanel.style.width="150px";
        div_searchResultPanel.style.height="auto";
        div_searchResultPanel.style.display="none";


        //清楚搜索输入框内的叉叉按钮
        var div_clear=document.createElement("div");
        div_clear.id="div_clear";
        div_clear.className="div_clear";
        div_clear.style.visibility="hidden";
    var search_button=document.createElement("button");
    search_button.className="search_button";
    search_button.onclick=function () {
        //点击后跳转到指定位置搜索的位置
        myValue=G("search_input").value;
        if(myValue!=null&&myValue!=""){
            setPlace();
        }

    }

    div2.appendChild(input);
    div2.appendChild(div_searchResultPanel);
    div2.appendChild(div_clear);


    div.appendChild(div2);
    div.appendChild(search_button);
    // 添加DOM元素到地图中
    map.getContainer().appendChild(div);
    // 将DOM元素返回
    return div;
}
// 创建控件
var searchControl = new SearchControl();
// 添加到地图当中
mp.addControl(searchControl);



//搜索框
// 百度地图API功能
function G(id) {
    return document.getElementById(id);
}

var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
    {"input" : "search_input"
        ,"location" : mp
    });

ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
    var str = "";
    var _value = e.fromitem.value;
    var value = "";
    if (e.fromitem.index > -1) {
        value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
    }
    str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;

    value = "";
    if (e.toitem.index > -1) {
        _value = e.toitem.value;
        value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
    }
    str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
    G("searchResultPanel").innerHTML = str;
});

var myValue;
ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
    var _value = e.item.value;
    myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
    G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;

    setPlace();
});

function setPlace(){
    // mp.clearOverlays();    //清除地图上所有覆盖物
    function myFun(){
        var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
        mp.centerAndZoom(pp, 18);
        // mp.addOverlay(new BMap.Marker(pp));    //添加标注
    }
    var local = new BMap.LocalSearch(mp, { //智能搜索
        onSearchComplete: myFun
    });
    local.search(myValue);
}










// 复杂的自定义覆盖物
function ComplexCustomOverlay(point, text, mouseoverText,url,house_type,auction_end_time,auction_stage,house_unit_price,house_area,fang_tian_xia_projcodeid){
    this._point = point;
    this._text=text;//小区名字
    this._overText = mouseoverText;
    this._url=url;//淘宝司法拍卖的地址
    this._house_type=house_type;//房子的类型
    this._auction_end_time=auction_end_time;
    this._auction_stage=auction_stage;
    this._house_unit_price=house_unit_price;//起拍价,单位万,保留1位小数
    this._house_area=house_area;//面积
    this._lianjia_http_url="https://cd.lianjia.com/xiaoqu/rs"+text;//链家网址
    var fangtianxia_http_url="";
    if(fang_tian_xia_projcodeid){
        if(fang_tian_xia_projcodeid.indexOf("http")>=0){
            fangtianxia_http_url=fang_tian_xia_projcodeid;
        }
        else{
            fangtianxia_http_url="http://fangjia.fang.com/process/cd/"+fang_tian_xia_projcodeid+".htm";//房天下网址
        }
    }
    this._fangtianxia_http_url=fangtianxia_http_url;
    this._anjuke_http_url="https://chengdu.anjuke.com/community/?currentcityid=&rd=1&t=3&type=8&sugg=0&kw="+text;
}
ComplexCustomOverlay.prototype = new BMap.Overlay();
ComplexCustomOverlay.prototype.initialize = function(map){
    this._map = map;
    var div = this._div = document.createElement("div");
    div.style.position="absolute";
    div.style.zIndex = BMap.Overlay.getZIndex(this._point.lat);
    var i_title=document.createElement("i");
    i_title.className="num";
    if(this._house_type=="办公"){
        i_title.style.backgroundColor = "#828282";
    }
    else if(this._house_type=="商业"){
        i_title.style.backgroundColor = "#EE5D5B";
    }
    else if(this._house_type=="其他"){
        i_title.style.backgroundColor = "#FF8C00";
    }
    else if(this._house_type=="住宅"){
        // div.style.backgroundColor = "#EE5D5B";
    }
    i_title.setAttribute("http_url",this._url);
    i_title.innerHTML=this._house_type+" "+this._house_unit_price+" "+this._house_area+" "+this._text+" ";
    i_title.onclick = function () {
        window.open(that._url);
    }
    div.appendChild(i_title);



    // div.style.border = "1px solid #BC3B3A";
    // div.style.color = "white";
    // div.style.height = "18px";
    // div.style.padding = "2px";
    // div.style.lineHeight = "18px";
    // div.style.whiteSpace = "nowrap";
    // div.style.MozUserSelect = "none";
    // div.style.fontSize = "12px"
    // var span = this._span = document.createElement("span");
    // div.appendChild(span);
    // span.appendChild(document.createTextNode(this._text));
    var that = this;

    var arrow = this._arrow = document.createElement("div");

    // arrow.style.background = "url(http://map.baidu.com/fwmap/upload/r/map/fwmap/static/house/images/label.png) no-repeat";
    // arrow.style.position = "absolute";
    // arrow.style.width = "11px";
    // arrow.style.height = "10px";
    arrow.style.top = "24px";//决定箭头下标的位置
    arrow.style.left = "90px";//决定箭头下标的位置,不可以省,否则会出错
    // arrow.style.right = "0px";
    // arrow.style.margin="auto";
    // arrow.style.bottom="0px";

    // arrow.style.overflow = "hidden";


    // var i_arrow=document.createElement("i");
    // i_arrow.className="arrow_up";
    // arrow.appendChild(i_arrow);
    if(this._house_type=="办公"){
        arrow.className="arrow_up_bangong"
    }
    else if(this._house_type=="商业"){
        arrow.className="arrow_up_shangye"
    }
    else if(this._house_type=="其他"){
        arrow.className="arrow_up_other"
    }
    else if(this._house_type=="住宅"){
        // arrow.style.border_top_color = "#EE5D5B";
        arrow.className="arrow_up";
    }
    div.appendChild(arrow);
    //
    // var house_type = this._span = document.createElement("div");
    // house_type.appendChild(this._span = document.createElement("div"));
    // house_type.appendChild(this._span = document.createElement("div"));
    // div.appendChild(house_type);

    //链家
    var div_lianjia = document.createElement("div");
    div_lianjia.className="div_lianjia";
    div_lianjia.style.display="none";
    div_lianjia.onclick = function () {
        window.open(that._lianjia_http_url);
    }
    var div_lianjia_img = document.createElement("div");
    div_lianjia_img.className="div_lianjia_img";
    div_lianjia.appendChild(div_lianjia_img);
    div.appendChild(div_lianjia);

    //房天下
    var div_fangtianxia =document.createElement("div");
    div_fangtianxia.className="div_fangtianxia";
    div_fangtianxia.style.display="none";
    div_fangtianxia.onclick = function () {
        window.open(that._fangtianxia_http_url);
    }
    var div_fangtianxia_img=document.createElement("div");
    div_fangtianxia_img.className="div_fangtianxia_img";
    div_fangtianxia.appendChild(div_fangtianxia_img);
    div.appendChild(div_fangtianxia);

    //安居客
    var div_anjuke =document.createElement("div");
    div_anjuke.className="div_anjuke";
    div_anjuke.style.display="none";
    div_anjuke.onclick = function () {
        window.open(that._anjuke_http_url);
    }
    var div_anjuke_img=document.createElement("div");
    div_anjuke_img.className="div_anjuke_img";
    div_anjuke.appendChild(div_anjuke_img);
    div.appendChild(div_anjuke);


    //鼠标悬停时候
    div.onmouseover = function(){
        // this.style.backgroundColor = "#6BADCA";
        // this.style.borderColor = "#0000ff";
        // this.getElementsByTagName("span")[0].innerHTML = that._overText;
        // arrow.style.backgroundPosition = "0px -20px";
        // arrow.style.display = "none";
        //
        // this.style.height="60px";
        //
        // this.getElementsByTagName("div")[1].style.display="";
        // this.getElementsByTagName("div")[1].getElementsByTagName("div")[0].innerHTML=that._house_type+" "+that._auction_stage;
        // this.getElementsByTagName("div")[1].getElementsByTagName("div")[1].innerHTML="结束时间:"+formatDateTime(that._auction_end_time);
        div_lianjia.style.display="";
        div_fangtianxia.style.display="";
        div_anjuke.style.display="";


    }
    //鼠标移出的时候
    div.onmouseout = function(){
        // if(that._house_type=="办公"){
        //     div.style.backgroundColor = "#828282";
        // }
        // else if(that._house_type=="商业"){
        //     div.style.backgroundColor = "#EE5D5B";
        // }
        // else if(that._house_type=="其他"){
        //     div.style.backgroundColor = "#FF8C00";
        // }
        // else if(that._house_type=="住宅"){
        //     // div.style.backgroundColor = "#EE5D5B";
        // }
        //
        // this.style.borderColor = "#BC3B3A";
        // this.getElementsByTagName("span")[0].innerHTML = that._text;
        // arrow.style.backgroundPosition = "0px 0px";
        // arrow.style.display = "";
        //
        // this.style.height="18px";
        //
        // this.getElementsByTagName("div")[1].style.display="none";
        setTimeout(function() {
            div_lianjia.style.display="none";
            div_fangtianxia.style.display="none";
            div_anjuke.style.display="none";
        }, 2000);

    }
    // div.onclick = function () {
    //     window.open(that._url);
    // }
    mp.getPanes().labelPane.appendChild(div);

    return div;
}
ComplexCustomOverlay.prototype.draw = function(){
    var map = this._map;
    var pixel = map.pointToOverlayPixel(this._point);
    this._div.style.left = pixel.x - parseInt(this._arrow.style.left) + "px";
    this._div.style.top  = pixel.y - 30 + "px";
}
//    var txt = "银湖海岸城", mouseoverTxt = txt + " " + parseInt(Math.random() * 1000,10) + "套" ;
//
//    var myCompOverlay = new ComplexCustomOverlay(new BMap.Point(104.072116,30.663465), "银湖海岸城",mouseoverTxt);
//    mp.addOverlay(myCompOverlay);

//    var center_lng=104.072116;//地图中心的经度
//    var center_lat=30.663465;//地图中心的纬度
//    mp.addEventListener("dragend", function(){
//            var center = mp.getCenter();
////           alert("地图中心点变更为：" + center.lng + ", " + center.lat);
//            center_lng=center.lng;
//            center_lat=center.lat;
//            var lng_bianjie_you=center_lng;//右边界
//            var lng_bianjie_zuo=center_lng;//左边界
//            var lat_bianjie_shang=center_lat;//上边界
//            var lat_bianjie_xia=center_lat;//下边界
//
//            var lng_bianjie_you=center_lng+0.027884;
//            var lng_bianjie_zuo=center_lng-0.027884;
//            var lat_bianjie_shang=center_lat+0.011524;
//            var lat_bianjie_xia=center_lat-0.011524;
//            selectTdzyfSifaPaimaiByLngLat(lng_bianjie_you,lng_bianjie_zuo,lat_bianjie_shang,lat_bianjie_xia);
//        }
//    );
//    mp.addEventListener("zoomend", function(){
////        alert("地图缩放至：" + this.getZoom() + "级");
////        二分之一屏幕距离
////        16级 X距离0.027884  Y距离0.011524
////        17级 X距离0.013816  Y距离0.005825
////        18级 X距离0.007132  Y距离0.003192
////        19级 X距离0.00358   Y距离0.001569
//
//        if(this.getZoom()>=16){
//            var lng_bianjie_you=center_lng;//右边界
//            var lng_bianjie_zuo=center_lng;//左边界
//            var lat_bianjie_shang=center_lat;//上边界
//            var lat_bianjie_xia=center_lat;//下边界
//            if(this.getZoom()>=16){
//                lng_bianjie_you=center_lng+0.027884;
//                lng_bianjie_zuo=center_lng-0.027884;
//                lat_bianjie_shang=center_lat+0.011524;
//                lat_bianjie_xia=center_lat-0.011524;
//            }
//            selectTdzyfSifaPaimaiByLngLat(lng_bianjie_you,lng_bianjie_zuo,lat_bianjie_shang,lat_bianjie_xia);
//        }
//        else{
////            alert("点聚合显示区域的数量多少和均价")
//            selectTdzyfSifaPaimaiByLngLat(lng_bianjie_you,lng_bianjie_zuo,lat_bianjie_shang,lat_bianjie_xia);
//        }
//
//    });



function getContextPath(){           //得到项目的路径
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}
//请求后台数据
//参数百度经纬度右边界,左边界,上边界,下边界
function selectTdzyfSifaPaimaiByLngLat(lng_bianjie_you,lng_bianjie_zuo,lat_bianjie_shang,lat_bianjie_xia){
    $.ajax({
        url:getContextPath()+"/tdzyfSifaPaimaiController/selectTdzyfSifaPaimaiByLngLat",
        async: true,
        type:"post",
        dataType:"json",
        data:{
            lng_bianjie_you:lng_bianjie_you,
            lng_bianjie_zuo:lng_bianjie_zuo,
            lat_bianjie_shang:lat_bianjie_shang,
            lat_bianjie_xia:lat_bianjie_xia,
        },
        success:function(data){
            removeAllPoints();
            //在地图中添加标的物的信息
            addPoints(data);

        },
        error:function () {
            alert("服务器异常,请求数据失败");
        }
    });
}
//移除所有的标点
function removeAllPoints() {
    var allOverlay = mp.getOverlays();
    for (var i = 0; i < allOverlay.length -1; i++){
        mp.removeOverlay(allOverlay[i]);
    }
}
var markers = [];
//增加标点
function addPoints(data){

    $.each(data['tdzyfSifaPaimaiList'],function(i,tdzyfsipai){

        var txt = Math.round(parseInt(tdzyfsipai['auction_price'])/10000), mouseoverTxt = txt + "万 " + tdzyfsipai['house_area'] + "平米 "+tdzyfsipai['house_unit_price']+"元/平米" ;
        //要传入要显示的参数
        var house_unit_price=(parseFloat(tdzyfsipai['house_unit_price'])/10000).toFixed(1)+"万";
        var house_area=parseInt(tdzyfsipai['house_area'])+"㎡";
        var text="";
        if(tdzyfsipai['community_name']!=null){
            text=tdzyfsipai['community_name'];
        }
        else{
            text=cut_out_house_name(tdzyfsipai['house_name']);
        }

        //点坐标
        var pt=new BMap.Point(tdzyfsipai['bd_lng'],tdzyfsipai['bd_lat']);
        var myCompOverlay = new ComplexCustomOverlay(pt, text,mouseoverTxt,tdzyfsipai['url'],tdzyfsipai['house_type'],tdzyfsipai['auction_end_time'],tdzyfsipai['auction_stage'],house_unit_price,house_area,tdzyfsipai['fang_tian_xia_projcodeid']);
        mp.addOverlay(myCompOverlay);
        var myIcon = new BMap.Icon("img/marker_null.png", new BMap.Size(1,1));
        var marker2 = new BMap.Marker(pt,{icon:myIcon});  // 创建标注
        markers.push(marker2);
    });
    //最简单的用法，生成一个marker数组，然后调用markerClusterer类即可。
    var markerClusterer = new BMapLib.MarkerClusterer(mp,{markers:markers});
}
//XXX市XX区截断,只要后面7个字符
function cut_out_house_name(house_name){
    var index1=house_name.indexOf("区");
    var res=house_name.substring(index1+1,index1+8);
    return res;
}

function formatDateTime(inputTime) {
    var date = new Date(inputTime);
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    var second = date.getSeconds();
    minute = minute < 10 ? ('0' + minute) : minute;
    second = second < 10 ? ('0' + second) : second;
    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
};
/**
 * 监听输入框时时输入内容
 * @author HOUZHENYU
 * @param input dom对象
 * @param onFocusCallback 获取焦点时回调 （在这里发请求，回调中的this就是input对象）
 * @param onBlurCallback  失去焦点时回调 （回调中的this就是input对象）
 */
function onInputTimer(input, onFocusCallback, onBlurCallback){
    // var input = document.getElementById('search_input');
    var inputPrevValue = input.value;
    input.onfocus=function(){
        if(input._searchInputTimer){
            clearInterval(input._searchInputTimer);
            input._searchInputTimer = null;
        }else{
            input._searchInputTimer = setInterval(function(){
                // console.log(i++);
                var currentValue = input.value;
                if(inputPrevValue != currentValue){
                    inputPrevValue =  currentValue;
                    // console.log('发送请求：'+input.value);
                    onFocusCallback && onFocusCallback.call(input);
                }
            },100);
        }

    }
    input.onblur=function(){
        if(input._searchInputTimer){
            clearInterval(input._searchInputTimer);
            input._searchInputTimer = null;
            onBlurCallback && onBlurCallback.call(input);
        }
    }
}
//创建监听事件
function create_listener() {
    //搜索输入框的监听事件
    create_search_input_listener();
    //搜索输入框的叉叉按钮的监听事件
    create_div_clear_listener();

}
// 搜索输入框的监听事件
function create_search_input_listener() {
    //使用方法
    onInputTimer(G("search_input"), function(){
        //在这里处理时时变化的value
        // console.log(this.value)
        if(this.value.length>0){
            G("div_clear").style.visibility="visible";
        }
        else{
            G("div_clear").style.visibility="hidden";
        }

    });
    $('#search_input').bind('keyup', function(event) {
        //监听到回车,触发地图查询
        if (event.keyCode == "13") {
            if(this.value!=""&&this.value.length>0){
                //回车后跳转到指定位置搜索的位置
                myValue=this.value;
                if(myValue!=null&&myValue!=""){
                    setPlace();
                }
            }
        }

    });
}
//搜索输入框的叉叉按钮的监听事件
function create_div_clear_listener() {
    G("div_clear").addEventListener("click",function () {
        G("search_input").value="";
        G("div_clear").style.visibility="hidden";
    });
}
//禁止浏览器后退到另一个标签页
function forbid(){
    if (window.history && window.history.pushState) {
        $(window).on('popstate', function () {
            window.history.pushState('forward', null, '#');
            window.history.forward(1);
        });
    }
    window.history.pushState('forward', null, '#'); //在IE中必须得有这两行
    window.history.forward(1);
}