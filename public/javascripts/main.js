var map;
var markersArray = [];
function initialize() {
	var myLatLng = new google.maps.LatLng(40.423000,-98.737200);
	var mapOptions = {
		zoom: 4,
		center: myLatLng,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	}
	map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
	
//	placeMarker(myLatLng);
	addMarker(myLatLng);
	
	var callBack = "Initial";
	google.maps.event.addListener(map, 'zoom_changed', function() {
		var zoomLevel = map.getZoom();
		map.setCenter(myLatLng);
		console.log("From zoom");
		callBack = postBounds(map.getBounds());
	});
	
	google.maps.event.addListener(map, 'click', function(event) {
		myLatLng = event.latLng;
		deleteOverlays();
		map.setCenter(myLatLng);
//		placeMarker(myLatLng);
		addMarker(myLatLng);
		console.log("From click");
		callBack = postBounds(map.getBounds());
	});
	
	if(callBack != "Initial") {
		alert(callBack);
	}
	
}

function placeMarker(location) {
	var marker = new google.maps.Marker({
		position: location,
		map: map
	});
	
	map.setCenter(location);
}

function addMarker(location) {
	marker = new google.maps.Marker({
		position: location,
		map: map
	});
	markersArray.push(marker);
}

//Removes the overlays from the map, but keeps them in the array
function clearOverlays() {
	if (markersArray) {
		for (i in markersArray) {
			markersArray[i].setMap(null);
		}
	}
}

//Shows any overlays currently in the array
function showOverlays() {
	if (markersArray) {
		for (i in markersArray) {
			markersArray[i].setMap(map);
		}
	}
}

//Deletes all markers in the array by removing references to them
function deleteOverlays() {
	if (markersArray) {
		for (i in markersArray) {
			markersArray[i].setMap(null);
		}
		markersArray.length = 0;
	}
}

google.maps.event.addDomListener(window, 'load', initialize);

function postBounds(bounds) {
	var swPoint = bounds.getSouthWest();
	var nePoint = bounds.getNorthEast();
	var swLat = swPoint.lat().toFixed(6);
    var swLng = swPoint.lng().toFixed(6);
    var neLat = nePoint.lat().toFixed(6);
    var neLng = nePoint.lng().toFixed(6);
    $.ajax({
		type: "POST",
		url: "/bounds",
		data: {
			swLat: swLat,
			swLng: swLng,
			neLat: neLat,
			neLng: neLng
		},
		success: function(response) {
			
		}
	});
}

//$(function() {
//	$.get("sites.json", function(data) {
//		$.each(data, function(index, site) {
//			$("#sites").append("<li>" + site.latitude + ", " + site.longitude + "</li>");
//		});
//	});
//});

