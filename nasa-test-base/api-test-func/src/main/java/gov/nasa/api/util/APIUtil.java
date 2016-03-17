package gov.nasa.api.util;

public class APIUtil {
	
	public static final String API_SECURE_DOMAIN = "https://api.nasa.gov";
	public static final String REST_PATH_PLANET = "/planetary";
	public static final String API_KEY="api_key=DEMO_KEY";
	
	public static String buildSoundApiRequest(String q, String limit) {
		return new SoundAPIRequest.SoundAPIRequestBuilder().withSearchQuery(q).withLimit(limit).build().toString();
	}

}
