/*
 * Copyright (c) 2013 Carnegie Mellon University Silicon Valley. 
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available
 * under the terms of dual licensing(GPL V2 for Research/Education
 * purposes). GNU Public License v2.0 which accompanies this distribution
 * is available at http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * 
 * Please contact http://www.cmu.edu/silicon-valley/ if you have any 
 * questions.
 * 
 * */
package util;

import play.libs.WS;
import play.libs.F.Function;
import play.libs.F.Promise;

import com.fasterxml.jackson.databind.JsonNode;

public class APICall {

	public static JsonNode callAPI(String apiString) {

		Promise<WS.Response> responsePromise = WS.url(apiString).get();
		final Promise<JsonNode> bodyPromise = responsePromise
				.map(new Function<WS.Response, JsonNode>() {
					@Override
					public JsonNode apply(WS.Response response)
							throws Throwable {

						if (response.getStatus() == 200) {
							return response.asJson();
						} else { // no response from the server
							return null;
						}
					}
				});

		return bodyPromise.get(3000);				

	}

	public static JsonNode postAPI(String apiString, JsonNode jsonData) {

		Promise<WS.Response> responsePromise = WS.url(apiString).post(jsonData);
		final Promise<JsonNode> bodyPromise = responsePromise
				.map(new Function<WS.Response, JsonNode>() {
					@Override
					public JsonNode apply(WS.Response response)
							throws Throwable {
						if (response.getStatus() == 200) {
							return response.asJson();
						} else { // no response from the server
							return null;
						}
					}
				});
		return bodyPromise.get(3000);
	}
}