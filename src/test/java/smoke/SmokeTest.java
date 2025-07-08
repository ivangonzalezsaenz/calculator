package smoke;

// JUnit 5 Suite API imports
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.ConfigurationParameters;
import org.junit.platform.suite.api.Suite;

// Cucumber Constants for configuration parameters
import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.FEATURES_PROPERTY_NAME;
// Optional: If you use plugins for reporting, uncomment the next line
// import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;

// This tells JUnit 5 to run this class as a test suite
@Suite
@ConfigurationParameters({
    @ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "classpath:smoke"), // Your feature path
    @ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "smoke.steps")         // Example: Your step definitions package
    // Optional: If you need to specify Cucumber plugins (e.g., for reports), uncomment and configure:
    // @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty,html:target/cucumber-reports.html")
})
public class SmokeTest {
    // This class can now be empty, as all configuration is handled by annotations
}