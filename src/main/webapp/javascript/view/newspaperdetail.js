
window.NewspaperView = Backbone.View.extend({


    initialize: function() {
        this.template = _.template(tpl.get('newspaper-details'));
		this.model.bind("change", this.render, this);
    },

    render: function(eventName) {
		$(this.el).html(this.template(this.model.toJSON()));
		return this;
    },

    events: {
        "change input": "change",
		"click .save": "saveNewspaper"
		//"click .addAd": "addAd"
		//"click .delete": "deleteNewspaper"
    },

    change: function(event) {
        var target = event.target;
        console.log('changing ' + target.id + ' from: ' + target.defaultValue + ' to: ' + target.value);
		// You could change your model on the spot, like this:
        // var change = {};
        // change[target.name] = target.value;
        // this.model.set(change);
    },

	saveNewspaper: function() {
		this.model.set({
			name: $('#name').val()
		});
		if (this.model.isNew()) {
			var self = this;
			app.newspaperList.create(this.model, {
				success: function() {
					app.navigate('api/newspaper/'+self.model.id, false);
				}
			});
		} else {
			this.model.save();
		}

		return false;
	}
	
	//addAd: function() {

	//}

	//deleteNewspaper: function() {
	//	this.model.destroy({
	//		success: function() {
	//			alert('Wine deleted successfully');
	//			window.history.back();
	//		}
	//	});
	//	return false;
	//}

});