/**
 * Asynchronously Loading the API
 */
function initialize() {
	
	var myCenter = new google.maps.LatLng(40.423000, -98.737200);
	
	var mapOptions = {
		center: myCenter,
		zoom: 4,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	}
  
	var map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
	
	google.maps.event.addListener(map, 'idle', function(event) {
		var bounds = map.getBounds();
		var swPoint = bounds.getSouthWest();
		var nePoint = bounds.getNorthEast();
		var swLat = swPoint.lat();
	    var swLng = swPoint.lng();
	    var neLat = nePoint.lat();
	    var neLng = nePoint.lng();
	    postBounds(swLat, swLng, neLat, neLng);
	});

	
	
	var marker = new google.maps.Marker({
		position: myCenter
	});
	
	marker.setMap(map);
	
}

google.maps.event.addDomListener(window, 'load', initialize);


function postBounds(swLat, swLng, neLat, neLng) {
	$.ajax({
		type: "POST",
		url: "/postbounds",
		data: {
			swLat: swLat,
			swLng: swLng,
			neLat: neLat,
			neLng: neLng
		},
		success: function(response) {
			console.log(response);
		}
	});
}

$(function() {
	$.get("sites.json", function(data) {
		$.each(data, function(index, site) {
			$("#sites").append("<li>" + site.latitude + ", " + site.longitude + "</li>");
		});
	});
});

