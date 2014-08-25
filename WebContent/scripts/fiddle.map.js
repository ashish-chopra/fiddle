fiddle.map = (function() {
	
	var init, destroy, addMarker, deleteMarkers, clearMarkers, showMarkers,
	    loadMarkers, infoWindow, redrawInfoWindow;
	
	var map,   			// the map object
		data,  			// server data 
		markers = [],   // list of markers
		popupTemplate,  // holds the compiled template for popup
		
		// constants used
		constants = {
			isLoaded: false,
			centerLat: 39.995294,  
			centerLng: 5.233096,
			zoomLevel: 3,
			fiveMinutes: 30000,
			tenSeconds: 10000
		},
		
		// google maps specific map options
		mapOptions = {
				zoom: constants.zoomLevel,
				center: new google.maps.LatLng(constants.centerLat, constants.centerLng),
				draggableCursor: 'default',
			    mapTypeControl: true,
				mapTypeControlOptions: {
			    	position: google.maps.ControlPosition.BOTTOM_LEFT, 
			    	style: google.maps.MapTypeControlStyle.DROPDOWN_MENU
			    },
			    zoomControl: true,
			    zoomControlOptions: {
			      style: google.maps.ZoomControlStyle.SMALL
			    }
			};
	
	// add a marker to the markers' array
	addMarker = function(lat, lng, data) {
		var location = getLocation(lat, lng);
		var marker = new google.maps.Marker({
			position: location,
			animation: google.maps.Animation.DROP
		});
		marker.setTitle(data.title);
		google.maps.event.addListener(marker, 'click', function() {
			var win = redrawInfoWindow(data);
			win.open(map, marker);
			
		});
		markers.push(marker);
	};
	
	// redraws info window when a marker position is clicked
	// on the map. It toggles any other infoWindow on the map.
	redrawInfoWindow = function(data) {
		if(infoWindow != undefined) {
			infoWindow.close();
		}
		infoWindow = new google.maps.InfoWindow({
				content : popupTemplate(data)
		});
		return infoWindow;
	};
	// removes markers from the map and delete its references.
	deleteMarkers = function() {
		clearMarkers();
		markers = [];
	};
	
	// shows all the markers on the map.
	showMarkers = function() {
		setAllMap(map);
	};
	
	// removes the markers from the map object, but keep its references.
	clearMarkers = function() {
		setAllMap(null);
	};
	
	// set all markers to given map object
	setAllMap = function(map) {
		for (var i = 0; i < markers.length; i++) {
			markers[i].setMap(map);
		}
	};
	
	// converts the lat, lng into Google's LatLng object.
	getLocation = function(lat, lng) {
		return new google.maps.LatLng(lat, lng);
	};
	

	// initialize the map feature module
	init =  function() {
		if(constants.isLoaded) {
			destroy();
		}
		
		var temp = $("#popup-template").html();
		console.log(temp);
		popupTemplate = Handlebars.compile(temp);
		console.log(popupTemplate);
		map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
		constants.isLoaded = true;
		loadMarkers();
	};
	
	// destroys the map feature module
	destroy =  function() {
		deleteMarkers();
		map = null;
		popupTemplate = null;
		constants.isLoaded = false;
	};

	loadMarkers = function() {
		fiddle.notification.show();
		$.ajax({
			url: "getMarkers.do?subAction=listMarkers",
			dataType: 'json',
			type: 'GET',
			success: function(response) {
				data = response;
				var locations = response.data;
				for(var i = 0; i < locations.length; i++) {
					var loc = locations[i];
					addMarker(loc.latitude, loc.longitude, loc);
				}	
				showMarkers();
				setTimeout(function() {
					fiddle.notification.hide();
				}, 1000);
			},
			error : function(xhr, msg, err) {
				alert("Request Error: " + msg);
			}
		});
	};
	
	
	// reload the map on user's click
	reload = function() {
		var count = 3;
		var prompt = setInterval(function(){
			fiddle.notification.show("Reloading map in " + count + " seconds.");
			count--;
			if(count == -1) {
				clearInterval(prompt);
				deleteMarkers();
				loadMarkers();
			}
		}, 1000);
	};

	return {
		init: init,
		reload: reload,
		destroy: destroy
	};
})();