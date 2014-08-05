Backbone.View.prototype.close = function () {
    console.log('Closing view ' + this);
    if (this.beforeClose) {
        this.beforeClose();
    }
    this.remove();
    this.unbind();
};

var AppRouter = Backbone.Router.extend({

    initialize: function() {
        $('#header').html( new HeaderView().render().el );
    },

	routes: {
		""				: "list",
		"newspaper/new"	: "newNewspaper",
		"newspaper/:id"	: "newspaperDetails"
	},

	list: function() {
        this.before();
  	},

	newspaperDetails: function(id) {
        this.before(function() {
			var newspaper = app.newspaperList.get(id);
		    app.showView( '#content', new NewspaperView({model: newspaper}) );
        });
  	},

	newNewspaper: function() {
        this.before(function() {
    		app.showView( '#content', new NewspaperView({model: new Newspaper()}) );
        });
	},

    showView: function(selector, view) {
        if (this.currentView)
            this.currentView.close();
        $(selector).html(view.render().el);
        this.currentView = view;
        return view;
    },

    before: function(callback) {
        if (this.newspaperList) {
            if (callback) callback();
        } else {
            this.newspaperList = new newspaperCollection();
       		this.newspaperList.fetch({success: function() {
               $('#sidebar').html( new NewspaperListView({model: app.newspaperList}).render().el );
               if (callback) callback();
            }});
        }
    }

});

tpl.loadTemplates(['header', 'newspaper-details', 'newspaper-list-item'], function() {
    app = new AppRouter();
    Backbone.history.start();
});