/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
*/

var argscheck = require('cordova/argscheck'),
    channel = require('cordova/channel'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec'),
    cordova = require('cordova');

/**
 * This represents the mobile deeplink, and provides properties for inspecting the uri of the app.
 * @constructor
 */
function Deeplink() {
    cordova.addWindowEventHandler("deeplinkchange");

    channel.onCordovaReady.subscribe(function() {
        exec(deeplink._start, deeplink._error, "Deeplink", "start", []);
    });
}

/**
 * Callback for deeplink
 *
 * @param {Object} data
 */
Deeplink.prototype._start = function (data) {
	if (data) {		
		cordova.fireWindowEvent("deeplinkchange", data);
	}
};

/**
 * Error callback for deeplink start
 */
Deeplink.prototype._error = function(e) {
    console.log("Error initializing Deeplink: " + e);
};

var deeplink = new Deeplink();

module.exports = deeplink;
