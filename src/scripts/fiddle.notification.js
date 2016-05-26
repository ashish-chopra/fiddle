fiddle.notification = (function() {
			
			var show, hide, noticeHandle, 
			defaultText = "Welcome to Fiddle!",
			timerOn = false,
			timer,
			delay = 3000;
			
			show = function(msg) {
				if(timerOn) {
					timerOn = false;
					clearTimeout(timer);
					timer = null;
				}
				msg = msg || "Loading...";
				noticeHandle.html("<b>"+ msg +"</b>")
				noticeHandle.fadeIn(800);
			};
			
			hide = function() {
				timerOn = true;
				noticeHandle.empty();
				createTimer();
				
			};
			
			createTimer = function() {
				timer = setTimeout(function(){ show(defaultText)}, delay);
			};
			
			init = function() {
				noticeHandle = $("#loading-bar");
			};
			
			return {
				init: init,
				show: show,
				hide: hide
			};
		})();


fiddle.toolbar = (function() {
	var init, destroy, reloadButton, aboutButton, closeAboutButton, 
		aboutScreen, reload, openAbout, closeAbout;
	
	var isLoaded = false, 
		isAboutOpen = false;
	
	// opens the about screen
	openAbout = function(e) {
		if (isAboutOpen) {
			return false;
		}
		aboutScreen.fadeIn(1000);
		isAboutOpen = true;
	};
	
	// close the about screen
	closeAbout = function(e) {
		aboutScreen.fadeOut(1000);
		isAboutOpen = false;
	};
	
	// reloads the map
	reload = function(e) {
		fiddle.map.reload();
	};
	
	init = function() {
		if(isLoaded) {
			destroy();
		}
		reloadButton = $("#reload-fiddle-button");
		aboutButton = $("#about-fiddle-button");
		aboutScreen = $("#about-fiddle");
		closeAboutButton = $(".about-close");
		
		reloadButton.bind('click', reload);
		aboutButton.bind('click', openAbout);
		closeAboutButton.bind('click', closeAbout);
		isLoaded = true;
	};
	
	destroy = function() {
		reloadButton.unbind('click',reload);
		aboutButton.unbind('click', openAbout);
		closeAboutButton.unbind('click', closeAbout);
		reloadButton = null;
		aboutButton = null;
		closeAboutButton = null;
		isLoaded = false;
		isAboutOpen = false;
	};
	
	return {
		init: init,
		destroy: destroy
	};
})();