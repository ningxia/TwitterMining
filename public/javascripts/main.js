var map;
var markersArray = [];

function initialize() {
	
	/* 	Original Map Center */
	var mapCenter = new google.maps.LatLng(40.423000,-98.737200);
	
	/* 	Map Options */
	var mapOptions = {
		zoom: 4,
		center: mapCenter,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	}
	
	map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
	
	/* 	Add a marker */
	addMarker(mapCenter);

	var content = "Obama: 53%, Romney: 46%";
	
	attachMessage(markersArray, mapCenter, content);
	
	/* 	Check if Google Map is fully loaded */
	google.maps.event.addListenerOnce(map, 'idle', function(){
/* 		alert("Map is fully loaded!"); */
	    getData(eventMonitor(map));
	});

}

/* Listen to multiple events and get data within bounds */
function eventMonitor(map) {

	var center = map.getCenter();
	
	/* 	Zoom changed event listener */
	google.maps.event.addListener(map, 'zoom_changed', function(event) {
		addMarker(center);
/* 		alert("From zoom: " + bounds); */
	});
	
	/* 	Click event listener */
	google.maps.event.addListener(map, 'click', function(event) {
/* 		deleteOverlays(); */
		center = event.latLng;
		map.setCenter(center);
		addMarker(center);
/* 		alert("From click: " + bounds); */
	});
	
	/* 	Check if Google Map is fully loaded */
	google.maps.event.addListener(map, 'idle', function() {
/* 		alert("From idle: " + map.getBounds()); */
/* 		alert("From idle: " + map.getZoom()); */
/* 		getData(map.getZoom(), map.getBounds()); */
		addMarker(map.getCenter());
	});
	
}

/* Add markers to the marker array */
function addMarker(location) {
	marker = new google.maps.Marker({
		position: location,
		map: map
	});
	markersArray.push(marker);
	attachMessage(marker, location, "Obama: 55%, Romney: 45%");
}

function attachMessage(marker, myLatLng, content) {
	var infowindow = new google.maps.InfoWindow({
    	content: content,
    	position: myLatLng
    });
    infowindow.open(map);
}

/* Removes the overlays from the map, but keeps them in the array */
function clearOverlays() {
	if (markersArray) {
		for (i in markersArray) {
			markersArray[i].setMap(null);
		}
	}
}

/* Shows any overlays currently in the array */
function showOverlays() {
	if (markersArray) {
		for (i in markersArray) {
			markersArray[i].setMap(map);
		}
	}
}

/* Deletes all markers in the array by removing references to them */
function deleteOverlays() {
	if (markersArray) {
		for (i in markersArray) {
			markersArray[i].setMap(null);
		}
		markersArray.length = 0;
	}
}

/* Get data within certain boudns */
function getData(zoomLevel, bounds) {
	var swPoint = bounds.getSouthWest();
	var nePoint = bounds.getNorthEast();
	var swLat = swPoint.lat().toFixed(6);
    var swLng = swPoint.lng().toFixed(6);
    var neLat = nePoint.lat().toFixed(6);
    var neLng = nePoint.lng().toFixed(6);
    var data;
    $.ajax({
		type: "GET",
		url: "/bounds",
		data: {
			zoomLevel: zoomLevel,
			swLat: swLat,
			swLng: swLng,
			neLat: neLat,
			neLng: neLng
		},
		success: function(response) {
			if((typeof response) != "object") {
				data = $.parseJSON(response);
			}
			else {
				data = response;
			}
			alert(data);
		}
	});
}

google.maps.event.addDomListener(window, 'load', initialize);


