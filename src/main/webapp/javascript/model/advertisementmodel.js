
window.Advertisement = Backbone.RelationalModel.extend({
	
	urlRoot:"/api/newspapers/{id}/ads",
	defaults: {
		"id": null,
		"title": "",
		"adText": "",
		"paperId": null
	}

});
