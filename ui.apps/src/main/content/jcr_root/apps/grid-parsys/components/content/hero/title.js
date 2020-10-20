"use strict";

use(function () {


    var CONST = {
        PROP_TITLE: "title",
    }

    var title = {};

    // The actual title content
    title.text = granite.resource.properties[CONST.PROP_TITLE]
            || resourcePage.getProperties().get(CONST.PROP_TITLE)
            || wcm.currentPage.properties[CONST.PROP_TITLE];


    return title;

});
