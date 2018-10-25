var markers = [];//标点集合
var cluster;//点聚合
function getContextPath(){           //得到项目的路径
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}

$(document).ready(function(){
    initMap();
    // forbid();
    selectTdzyfSifaPaimaiByLngLat(null,null,null,null);
    // create_listener();
});
//初始化地图
var map;
function initMap(){
    map = new AMap.Map('container', {
        zoom:13,//级别
        center: [104.072116,30.663465],//中心点坐标
        viewMode:'3D',// 地图模式
        layers: [
            new AMap.TileLayer(),//高德默认标准图层
            // new AMap.TileLayer.Satellite(),
            // new AMap.TileLayer.RoadNet()
        ],
    });
}
//卫星地图的点击事件
function clickWeiXinMap(){
    // 构造官方卫星、路网图层
    var layers =  [
        new AMap.TileLayer.Satellite(),
        new AMap.TileLayer.RoadNet()
    ]
// 地图上设置图层
    map.setLayers(layers);

}
//普通地图的点击事件
function clickPuTongMap(){
    // 构造官方卫星、路网图层
    var layers =  [
        new AMap.TileLayer(),//高德默认标准图层
    ]
// 地图上设置图层
    map.setLayers(layers);
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

//增加标点
function addPoints(data){
    var markers2=[];
    $.each(data['tdzyfSifaPaimaiList'],function(i,tdzyfsipai){
        var toLng="";
        var toLat="";
        AMap.convertFrom(tdzyfsipai['bd_lng']+","+tdzyfsipai['bd_lat'],"baidu",
            function(status,result){
                if(status=="complete"){
                    toLng=result.locations[0].P;
                    toLat=result.locations[0].O;
                    transform=true;
                    var lng_lat=new AMap.LngLat(toLng,toLat);
                    var div=setdiv(tdzyfsipai);
                    var m = new AMap.Marker({
                        map:map,
                        position:lng_lat,
                        content: div,
                        offset: new AMap.Pixel(-100,-20)
                    });
                    markers.push(m);


                    var m2 = new AMap.Marker({
                        // map:map,
                        position:lng_lat,
                        content: "<div></div>",
                        offset: new AMap.Pixel(0,0)
                    });
                    markers2.push(m2);
                }else{
                    console.log(status+"/"+result);
                    alert("获取位置失败,请重试");
                }
            });

    });
    setTimeout(function () {
        console.log(markers.length);
        var sts = [{
            url: "https://a.amap.com/jsapi_demos/static/images/blue.png",
            size: new AMap.Size(32, 32),
            offset: new AMap.Pixel(-16, -16)
        }, {
            url: "https://a.amap.com/jsapi_demos/static/images/green.png",
            size: new AMap.Size(32, 32),
            offset: new AMap.Pixel(-16, -16)
        }, {
            url: "https://a.amap.com/jsapi_demos/static/images/orange.png",
            size: new AMap.Size(36, 36),
            offset: new AMap.Pixel(-18, -18)
        },{
            url: "https://a.amap.com/jsapi_demos/static/images/red.png",
            size: new AMap.Size(48, 48),
            offset: new AMap.Pixel(-24, -24)
        },{
            url: "https://a.amap.com/jsapi_demos/static/images/darkRed.png",
            size: new AMap.Size(48, 48),
            offset: new AMap.Pixel(-24, -24)
        }];
        map.plugin(["AMap.MarkerClusterer"],function(){
            cluster = new AMap.MarkerClusterer(map, markers2, {
                styles: sts,
                gridSize:80
            });
        });
    }, 6000);

}

//移除所有标点
function removeAllPoints() {

}
//设置div每个房源的信息
function setdiv(tdzyfsipai){
    var div =  document.createElement("div");
    div.style.position="absolute";
    // div.style.zIndex = BMap.Overlay.getZIndex(this._point.lat);
    var i_title=document.createElement("i");
    i_title.className="num";
    if(tdzyfsipai['house_type']=="办公"){
        i_title.style.backgroundColor = "#828282";
    }
    else if(tdzyfsipai['house_type']=="商业"){
        i_title.style.backgroundColor = "#EE5D5B";
    }
    else if(tdzyfsipai['house_type']=="其他"){
        i_title.style.backgroundColor = "#FF8C00";
    }
    else if(tdzyfsipai['house_type']=="住宅"){
        // div.style.backgroundColor = "#EE5D5B";
    }
    i_title.setAttribute("http_url",tdzyfsipai['url']);
    var community_name="";
    if(tdzyfsipai['community_name']!=null){
        community_name=tdzyfsipai['community_name'];
    }
    else{
        community_name=cut_out_house_name(tdzyfsipai['house_name']);
    }

    i_title.innerHTML=tdzyfsipai['house_type']+" "+tdzyfsipai['house_unit_price']+" "+tdzyfsipai['house_area']+" "+community_name+" ";
    i_title.onclick = function () {
        window.open(tdzyfsipai['url']);
    }
    div.appendChild(i_title);
    var arrow = document.createElement("div");
    arrow.style.top = "24px";//决定箭头下标的位置
    arrow.style.left = "90px";//决定箭头下标的位置,不可以省,否则会出错
    if(tdzyfsipai['house_type']=="办公"){
        arrow.className="arrow_up_bangong"
    }
    else if(tdzyfsipai['house_type']=="商业"){
        arrow.className="arrow_up_shangye"
    }
    else if(tdzyfsipai['house_type']=="其他"){
        arrow.className="arrow_up_other"
    }
    else if(tdzyfsipai['house_type']=="住宅"){
        arrow.className="arrow_up";
    }
    div.appendChild(arrow);

    //链家
    var div_lianjia = document.createElement("div");
    div_lianjia.className="div_lianjia";
    div_lianjia.style.display="none";
    div_lianjia.onclick = function () {
        lianjia_http_url="https://cd.lianjia.com/xiaoqu/rs"+community_name;//链家网址
        window.open(lianjia_http_url);
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
        fangtianxia_http_url="http://cd.fang.com/";//房天下网址
        window.open(fangtianxia_http_url);
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
        anjuke_http_url="https://chengdu.anjuke.com/community/?currentcityid=&rd=1&t=3&type=8&sugg=0&kw="+community_name;
        window.open(anjuke_http_url);
    }
    var div_anjuke_img=document.createElement("div");
    div_anjuke_img.className="div_anjuke_img";
    div_anjuke.appendChild(div_anjuke_img);
    div.appendChild(div_anjuke);


    //鼠标悬停时候
    div.onmouseover = function(){
        div_lianjia.style.display="";
        div_fangtianxia.style.display="";
        div_anjuke.style.display="";
    }
    //鼠标移出的时候
    div.onmouseout = function(){
        setTimeout(function() {
            div_lianjia.style.display="none";
            div_fangtianxia.style.display="none";
            div_anjuke.style.display="none";
        }, 2000);

    }
    return div;
}

//XXX市XX区截断,只要后面7个字符
function cut_out_house_name(house_name){
    var index1=house_name.indexOf("区");
    var res=house_name.substring(index1+1,index1+8);
    return res;
}