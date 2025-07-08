package acceptance; // Make sure this matches the actual package of your file

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.ConfigurationParameters;
import org.junit.platform.suite.api.Suite;

// These static imports help make the ConfigurationParameter keys cleaner
import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.FEATURES_PROPERTY_NAME;
// If you need to specify plugins (e.g., for reports), also import:
// import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;

// This tells JUnit 5 to run this class as a test suite
@Suite
@ConfigurationParameters({
    @ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "classpath:feature"), // Set your features path here
    @ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "acceptance.steps")         // Set your step definitions package here
    // If you need to specify plugins (e.g., "pretty", "html:target/cucumber-reports.html"), add:
    // @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty,html:target/cucumber-reports.html")
})
public class AcceptanceTest {
    // This class can now be empty, as the configuration is done via annotations
}