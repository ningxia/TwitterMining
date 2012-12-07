function initialize() {
        var mapOptions = {
            center: new google.maps.LatLng(40.423000, -98.737200),
            zoom: 4,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        var map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
}

google.maps.event.addDomListener(window, 'load', initialize);
