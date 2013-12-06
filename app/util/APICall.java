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
		return bodyPromise.get();
	}

	public static String postAPI(String apiString, JsonNode jsonData) {

		Promise<WS.Response> responsePromise = WS.url(apiString).post(jsonData);
		final Promise<String> bodyPromise = responsePromise
				.map(new Function<WS.Response, String>() {
					@Override
					public String apply(WS.Response response) throws Throwable {
						return response.getBody();
					}
				});
		return bodyPromise.get(play.mvc.Http.Status.REQUEST_TIMEOUT);
	}
}