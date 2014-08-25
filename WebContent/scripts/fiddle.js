/*
 * File: fiddle.js
 * Date: 28 Feb, 14
 * ----------------------------
 * fiddle.js bottstraps the fiddle application and holds
 * the application wide applicable settings and content.
 *
*/

/*
 *  global vars - window, document, $
 */

var fiddle = (function() {
	
	var	boot = function() {
		
			//fiddle.model.init();
			fiddle.notification.init();
			fiddle.toolbar.init();
			fiddle.map.init();
		};

	return {
		bootstrap: boot
	};
	
})();