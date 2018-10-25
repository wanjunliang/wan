/**
 * Created by 万峻良 on 2018/5/18.
 */
$(document).ready(function(){
    touch();
    add_map_listener();
});
//手指触摸事件
var start_x=0;//触摸开始x坐标
var start_y=0;//触摸开始y坐标
var end_x=0;//触摸结束x坐标
var end_y=0;//触摸结束y坐标
var touch_number=1;//触摸点数量1表示一个指头，2表示2个指头

var two_dis=0;//两点触摸的距离开始时候的距离

var map_to_point=null;//缩放后要定位到的地图中心点
function touch(){
    //触摸百度地图的div不是地图
    touch_map();


}
//触摸地图，触摸的是div不是百度地图
function touch_map() {
    var obj = document.getElementById('allmap');
    obj.addEventListener("touchstart",function (event) {
        console.log("当前层级:"+mp.getZoom());
        if (event.targetTouches.length == 1) {
            // event.preventDefault();// 阻止浏览器默认事件，重要
            var touch = event.targetTouches[0];
            start_x=touch.pageX;
            start_y=-touch.pageY;

        }
        if (event.targetTouches.length == 2) {
            // event.preventDefault();// 阻止浏览器默认事件，重要
            var touch_0 = event.targetTouches[0];
            var touch_1=event.targetTouches[1];
            two_dis=Math.sqrt((touch_0.pageY-touch_1.pageY)*(touch_0.pageY-touch_1.pageY)+(touch_0.pageX-touch_1.pageX)*(touch_0.pageX-touch_1.pageX));

        }
    });
    obj.addEventListener("touchmove",function (event) {
        event.preventDefault();// 阻止浏览器默认事件，重要
        if (event.targetTouches.length == 1) {
            touch_number=1;
            // event.preventDefault();// 阻止浏览器默认事件，重要
            var touch = event.changedTouches[0];
            //检测手势
            end_x=touch.pageX;
            end_y=-touch.pageY;
            var flag=checkTouchGesture(1,end_x,end_y,null,null);
        }
        if (event.targetTouches.length == 2) {
            var touch_0=event.targetTouches[0];
            var touch_1=event.targetTouches[1];
            if(touch_1!=null){
                console.log("双点，one x:"+touch_0.pageX+" y:"+touch_0.pageY+"     tow x:"+touch_1.pageX+" y:"+touch_1.pageY);
                touch_number=2;
                two_dis_2=Math.sqrt((touch_0.pageY-touch_1.pageY)*(touch_0.pageY-touch_1.pageY)+(touch_0.pageX-touch_1.pageX)*(touch_0.pageX-touch_1.pageX));
                if(two_dis<two_dis_2){
                    console.log("放大");
                    fangda_or_suoxiao_map("放大");
                }
                else if(two_dis>two_dis_2){
                    console.log("缩小");
                    fangda_or_suoxiao_map("缩小");
                }
            }
        }

    });
    obj.addEventListener('touchend', function(event) {
        // 如果这个元素的位置内只有一个手指的话
        if (event.changedTouches.length == 1) {
            // event.preventDefault();// 阻止浏览器默认事件，重要
            var touch = event.changedTouches[0];
            //检测手势
            end_x=touch.pageX;
            end_y=-touch.pageY;
            // var flag=checkTouchGesture(1,end_x,end_y,null,null);

        }

    });

}
//判断上移，下移，左移，右移，放大，缩小
//type判断是两个手指头还是一个手套指头
function checkTouchGesture(type,x1,y1,x2,y2){
    var flag="";
    if(touch_number==1){//一个指头
        // console.log("屏幕宽度:"+document.body.clientWidth+" "+" 高度"+document.body.clientHeight);
        // console.log("x1:"+x1+" "+" y1:"+y1);
        console.log("检测单点触摸");
        //斜率

        var k=0;
        var flag_k=1;//标记斜率存在否，存在是1，不存在是0
        if(x1!=start_x){
            k=(y1-start_y)/(x1-start_x);
        }
        else{
            flag_k=0;
        }



        //两点之间的距离
        var s=Math.sqrt((y1-start_y)*(y1-start_y)+(x1-start_x)*(x1-start_x));



        moveMap(k,s,flag_k);

    }
    else if(touch_number==2){//两个指头，判断缩放
        // console.log("检测双点触摸");
        // fangda_or_suoxiao_map();
    }
    return flag;
}
//传入斜率,屏幕触摸的移动距离
function moveMap(k,s,flag_k){
    // console.log("单点触摸开始 x:"+start_x+" y:"+start_y);
    // console.log("单点触摸结束 x:"+end_x+" y:"+end_y);
    var lng=mp.getCenter().lng;//经度
    var lat=mp.getCenter().lat;//纬度
    var b=lat-k*lng;
    //y=kx+b;
    if(flag_k==1){
        // console.log("公式：y="+k+"x+"+b+ "  屏幕移动距离："+s);
        var f1=1;
        if((end_x-start_x)>0){
            f1=1;
        }
        else{
            f1=-1;
        }

        var f2=1;
        if((end_y-start_y)>0){
            f2=1;
        }
        else{
            f2=-1;
        }

        //坐标移动距离=(方向正或负)*地图层级*屏幕移动距离*倍数
        var zoom=mp.getZoom();

        var j=1;
        if(zoom==16){
            j=0.5;
        }
        else if(zoom==17){
            j=0.2
        }
        else if(zoom>17){
            j=0.1;
        }
        var d=j*s*0.0001;

        //lag直接加上距离
        var lng2=lng+d;
        var lat2=k*lng2+b;

        //或者lat直接加上距离
        var lat3=lat+d;
        var lng3=(lat3-b)/k;

        //比较这两个新坐标与元坐标的距离，选择最小的那个

        var d2=Math.sqrt((lng2-lng)*(lng2-lng)-(lat2-lat)*(lat2-lat));
        var d3=Math.sqrt((lng3-lng)*(lng3-lng)-(lat3-lat)*(lat3-lat));
        if(d2<d3){
            //按照X坐标移动
            lng2=lng-f1*d;
            lat2=k*lng2+b;
            mp.panTo(new BMap.Point(lng2,lat2));   //地图移动
        }
        else{
            //按照Y坐标移动
            lat3=lat-f2*d;
            lng3=(lat3-b)/k;
            mp.panTo(new BMap.Point(lng3,lat3));   //地图移动
        }
    }
    else{//斜率不存在的时候
        //坐标移动距离=(方向正或负)*地图层级*屏幕移动距离*倍数
        var zoom=mp.getZoom();

        var j=1;
        if(zoom==16){
            j=0.5;
        }
        else if(zoom==17){
            j=0.2
        }
        else if(zoom>17){
            j=0.1;
        }
        var d=j*s*0.0001;

        var lat2=0;
        if(end_y-start_y>0){
            lat2=lat-d;
        }
        else{
            lat2=lat+d;
        }
        mp.panTo(new BMap.Point(lng,lat2));   //地图移动
    }

}

//放大地图或者缩小地图
function fangda_or_suoxiao_map(flag) {

    if(flag=="放大"){


        // mp.panTo(map_to_point);   //地图移动
        var zoom=mp.getZoom();
        mp.setZoom(zoom+1);

    }
    else if(flag=="缩小"){

        // mp.panTo(map_to_point);   //地图移动
        var zoom=mp.getZoom();
        mp.setZoom(zoom-1);
    }
}
function add_map_listener(){
    // mp.addEventListener("click",function (event) {
    //    map_to_point= event.point;
    // });
}