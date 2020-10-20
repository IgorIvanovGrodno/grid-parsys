"use strict";

use(function () {


    var CONST = {
        PROP_LINK_URL: "linkURL",
        PROP_LINK_TEXT: "textLink",
        PROP_OPENING:"opening",
    }

    var link = {};


    var url= granite.resource.properties[CONST.PROP_LINK_URL]
            || resourcePage.getProperties().get(CONST.PROP_LINK_URL)
            || wcm.currentPage.properties[CONST.PROP_LINK_URL];

    link.link ="http://localhost:4502" + url +".html";

    link.text = granite.resource.properties[CONST.PROP_LINK_TEXT]
                || resourcePage.getProperties().get(CONST.PROP_LINK_TEXT)
                || wcm.currentPage.properties[CONST.PROP_LINK_TEXT];

    link.opening = granite.resource.properties[CONST.PROP_OPENING]
                || resourcePage.getProperties().get(CONST.PROP_OPENING)
                || wcm.currentPage.properties[CONST.PROP_OPENING];

    return link;

});