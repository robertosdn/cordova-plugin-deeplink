<!---
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
-->

# org.plugin.cordova.deeplink

This plugin add a listener to deeplink url from browser

```html
<a href="example://app/login">open app</a>
````

## Installation

cordova plugin add org.plugin.cordova.deeplink --variable URL_SCHEME=example

### Supported Platforms

- Android

### Quick Example

window.addEventListener("deeplinkchange", onDeeplinkChange, false);

function onDeeplinkChange(data) {
	alert(data.uri);// show string: example://app/login
}
