/*
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
*/
package org.plugin.cordova.deeplink;

import java.util.TimeZone;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.provider.Settings;

public class Deeplink extends CordovaPlugin {
    public static final String TAG = "Deeplink";

    public static Intent startIntent;

    private CallbackContext pluginCallbackContext = null;

    /**
     * Constructor.
     */
    public Deeplink() {
    }

    /**
     * Sets the context of the Command. This can then be used to do things like
     * get file paths associated with the Activity.
     *
     * @param cordova The context of the main Activity.
     * @param webView The CordovaWebView Cordova is running in.
     */
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
		Deeplink.startIntent = cordova.getActivity().getIntent();		
    }

    /**
     * Executes the request and returns PluginResult.
     *
     * @param action            The action to execute.
     * @param args              JSONArry of arguments for the plugin.
     * @param callbackContext   The callback id used when calling back into JavaScript.
     * @return                  True if the action was valid, false if not.
     */
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
		if (action.equals("start")) {
			this.pluginCallbackContext = callbackContext;			
			JSONObject data = getData(Deeplink.startIntent);			
			if(data != null){
				callbackContext.success(data);
			}
        } else {
            return false;
        }
        return true;
    }

	/**
     * Called when the activity receives a new intent.
     */
    public void onNewIntent(Intent intent) {
		sendUpdate(getData(intent), true);
    }

	private JSONObject getData(Intent intent) {
		JSONObject obj = new JSONObject();
        try {
			Uri uri = intent.getData();
			if(uri != null){
				obj.put("uri", uri.toString());
				return obj;
			}			
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return null;
    }

    /**
     * Create a new plugin result and send it back to JavaScript
     *
     * @param deeplink data to set as navigator.deeplink
     */
    private void sendUpdate(JSONObject data, boolean keepCallback) {
        if (data != null && this.pluginCallbackContext != null) {
            PluginResult result = new PluginResult(PluginResult.Status.OK, data);
            result.setKeepCallback(keepCallback);			
            this.pluginCallbackContext.sendPluginResult(result);
        }
    }
}
