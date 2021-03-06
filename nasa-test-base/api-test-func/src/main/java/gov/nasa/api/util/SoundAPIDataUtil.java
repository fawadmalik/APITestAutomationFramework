/**
 * 
 */
package gov.nasa.api.util;

import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;

/**
 * @author kvyas
 *
 */
public class SoundAPIDataUtil {

	@DataProvider(name = "headerTestParams")
	public static Object[][] headerTestParams() {
		return new Object[][] { 
				{ "apollo", "1", HttpStatus.SC_OK }, // valid data
				{ "apollo", "char", HttpStatus.SC_BAD_REQUEST } //invalid data and has a bug resulting in test failures
			};
	}
	
	@DataProvider(name = "bodyTestParams")
	public static Object[][] bodyTestParams() {
		return new Object[][] { 
				{ "apollo", "1"}
			};
	}
	

}
