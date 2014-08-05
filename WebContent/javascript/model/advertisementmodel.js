
window.Advertisement = Backbone.Model.extend({
	
	urlRoot:"api/newspapers/{id}",
	defaults: {
		"id": null,
		"title": "",
		"adText": "",
		"paperId": null
	}

});
