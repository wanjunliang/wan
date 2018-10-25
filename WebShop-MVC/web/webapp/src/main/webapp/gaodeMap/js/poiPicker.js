if(typeof(AMapUI)=="undefined") {
    $.getScript("https://webapi.amap.com/ui/1.0/main.js?v=1.0.11").done(function (script, textstatus) {
        if (textstatus == "success" && typeof(AMapUI) != undefined) {
            AMapUI.loadUI(['misc/PoiPicker'], function (PoiPicker) {

                var poiPicker = new PoiPicker({
                    //city:'北京',
                    input: 'pickerInput'
                });
                //初始化poiPicker
                poiPickerReady(poiPicker);
            });
        }
        else {
            console.log("无法加载地图,请打开网络");
        }
    });
}
else{
    AMapUI.loadUI(['misc/PoiPicker'], function (PoiPicker) {

        var poiPicker = new PoiPicker({
            //city:'北京',
            input: 'pickerInput'
        });
        //初始化poiPicker
        poiPickerReady(poiPicker);
    });
}

function poiPickerReady(poiPicker) {

    window.poiPicker = poiPicker;

    var marker = new AMap.Marker();

    var infoWindow = new AMap.InfoWindow({
        offset: new AMap.Pixel(0, -20)
    });

    //选取了某个POI
    poiPicker.on('poiPicked', function (poiResult) {

        var source = poiResult.source,
            poi = poiResult.item,
            info = {
                source: source,
                id: poi.id,
                name: poi.name,
                location: poi.location.toString(),
                address: poi.address
            };

        marker.setMap(map);
        infoWindow.setMap(map);

        marker.setPosition(poi.location);
        infoWindow.setPosition(poi.location);

        // 点显示窗口信息
        // infoWindow.setContent('POI信息: <pre>' + JSON.stringify(info, null, 2) + '</pre>');
        // infoWindow.open(map, marker.getPosition());

        map.setCenter(marker.getPosition());
    });

    poiPicker.onCityReady(function () {
        poiPicker.suggest('');
    });
}
// 点击了搜索按钮
function clickSousuo(){

    AMap.service(["AMap.PlaceSearch"], function() {
        var placeSearch = new AMap.PlaceSearch({ //构造地点查询类
            pageSize: 1,
            pageIndex: 1,
            // city: "010", //城市
            map: map,
            // panel: ""
        });
        //关键字查询
        placeSearch.search($("#pickerInput").val());

    });
}