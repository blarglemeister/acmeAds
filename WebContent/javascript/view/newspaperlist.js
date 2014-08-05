
window.NewspaperListView = Backbone.View.extend({

    tagName:'ul',

    initialize:function () {
        this.model.bind("reset", this.render, this);
        var self = this;
        this.model.bind("add", function (newspaper) {
            $(self.el).append(new NewspaperListItemView({model:newspaper}).render().el);
        });
    },

    render:function (eventName) {
        _.each(this.model.models, function (newspaper) {
            $(this.el).append(new NewspaperListItemView({model:newspaper}).render().el);
        }, this);
        return this;
    }
});

window.NewspaperListItemView = Backbone.View.extend({

    tagName:"li",

    initialize:function () {
        this.template = _.template(tpl.get('newspaper-list-item'));
        this.model.bind("change", this.render, this);
        this.model.bind("destroy", this.close, this);
    },

    render:function (eventName) {
        $(this.el).html(this.template(this.model.toJSON()));
        return this;
    }

});