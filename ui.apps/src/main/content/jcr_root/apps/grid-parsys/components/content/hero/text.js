/*******************************************************************************
 * Copyright 2016 Adobe Systems Incorporated
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

"use strict";

/**
 * Text foundation component JS backing script
 */
use(["/libs/wcm/foundation/components/utils/AuthoringUtils.js"], function (AuthoringUtils) {
    
    var CONST = {
        PROP_TEXT: "text",
        PROP_RICH_FORMAT: "textIsRich",
        CONTEXT_TEXT: "text",
        CONTEXT_HTML: "html"
    };
    
    var text = {};
    
    // The actual text content
    text.text = granite.resource.properties[CONST.PROP_TEXT]
            || "";
    
    // Wether the text contains HTML or not
    text.context = granite.resource.properties[CONST.PROP_RICH_FORMAT]
            ? CONST.CONTEXT_HTML : CONST.CONTEXT_TEXT
    

    // Adding the constants to the exposed API
    text.CONST = CONST;
    
    return text;
    
});
