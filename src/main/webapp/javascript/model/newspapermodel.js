
window.Newspaper = Backbone.RelationalModel.extend({
	
	urlRoot:"/api/newspapers",
	defaults: {
		"id": null,
		"name": "",
		"ads": []
	},
	relations: [{
		type: Backbone.HasMany,
		key: 'ads',
		relatedModel: 'Advertisement',
		autoFetch: false,
		collectionType: 'AdvertisementCollection',
		reverseRelation: {
			key: 'hasAd',
			includeInJSON: true
		}
	}]

});

window.NewspaperCollection = Backbone.Collection.extend({
	model:Newspaper,
	url:"/api/newspapers"
});

