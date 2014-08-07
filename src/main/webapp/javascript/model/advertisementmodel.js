
window.Advertisement = Backbone.Model.extend({
	
	urlRoot:"/api/newspapers/{id}/ads",
	defaults: {
		"id": null,
		"title": "",
		"adText": "",
		"paperId": null
	}

});
