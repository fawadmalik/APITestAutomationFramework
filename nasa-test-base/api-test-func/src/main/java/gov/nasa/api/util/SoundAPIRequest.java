/**
 * 
 */
package gov.nasa.api.util;

/**
 * @author kvyas
 *
 */
public class SoundAPIRequest {

	String paramLimit;
	String paramQuery;
	
	public static final String RESOURCE_PATH = "/sounds";
	
	private SoundAPIRequest(SoundAPIRequestBuilder builder) {
		this.paramLimit = builder.paramLimit;
		this.paramQuery = builder.paramQuery;
	}	
	
	public static class SoundAPIRequestBuilder {
		private String paramLimit;
		private String paramQuery;
		
		public SoundAPIRequest build() {
			return new SoundAPIRequest(this);
		}
		
		public SoundAPIRequestBuilder withLimit(String paramLimit) {
			this.paramLimit = "limit=" + paramLimit;
			return this;
		}
		
		public SoundAPIRequestBuilder withSearchQuery(String paramQuery) {
			this.paramQuery = "q=" + paramQuery;
			return this;
		}
	}
	
	@Override
	public String toString() {
		return RESOURCE_PATH + "?" + paramQuery + "&" + paramLimit + "&" + APIUtil.API_KEY;
		
	}

	
}
