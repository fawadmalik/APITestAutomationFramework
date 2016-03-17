package gov.nasa.api;

import gov.nasa.api.model.SoundApiResponseModel;
import gov.nasa.api.util.APIUtil;
import gov.nasa.api.util.SoundAPIDataUtil;

import java.util.ArrayList;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

/**
 * Planetary sounds API test class
 * 
 * @author kvyas
 */
public class SoundsApiTest extends BaseApiTest {
	
	@BeforeClass
	public void setup() {
		super.setup();
		// do any test class specific setup..
	}
	
	@AfterClass
	public void teardown() {
		super.teardown();
		// do any test class specific tear down.
	}
	
	/*
	 * There is a defect on server side where it fails to handle invalid limit
	 * input. It ends up returning code 500 (Internal server error) instead of
	 * 400 (bad request). We have to be careful as sometimes leaking stacktraces
	 * can also be a security concern.
	 */
	@Test(dataProviderClass=SoundAPIDataUtil.class, dataProvider = "headerTestParams")
	public void testGetDataResponseHeaders(String q, String limit,
			int httpStatusCode) {
		
		RestAssured
				.expect()
				.statusCode(httpStatusCode)
				.contentType(ContentType.JSON)
				.when()
				.get(APIUtil.buildSoundApiRequest(q,limit));
	}

	@Test(dataProviderClass=SoundAPIDataUtil.class, dataProvider = "bodyTestParams")
	public void testDataLimitCount(String q, String limit) {

		RestAssured
				.expect()
				.body("count", Matchers.equalTo(1))
				.when()
				.get(APIUtil.buildSoundApiRequest(q,limit));
	}
	
	@SuppressWarnings("unchecked")
	@Test(dataProviderClass = SoundAPIDataUtil.class, dataProvider = "bodyTestParams")
	public void testDataDetails2(String q, String limit) {
		ArrayList<Map<String,String>>  results = (ArrayList<Map<String,String>>) RestAssured.when()
				.get(APIUtil.buildSoundApiRequest(q, limit))
				.then().extract().path("results");
		
		//validate we are getting values for the fields
		for(Map<String,String> soundApiResponseElement : results) {
			SoundApiResponseModel responseData = new ObjectMapper().convertValue(soundApiResponseElement, SoundApiResponseModel.class);
			Assert.assertNotNull(responseData.getId());
			Assert.assertNotNull(responseData.getDuration());
		}
		
	}


}
