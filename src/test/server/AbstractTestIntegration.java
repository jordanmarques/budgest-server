package server;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.jayway.restassured.RestAssured;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;


public abstract class AbstractTestIntegration extends AbstractTest {

    @BeforeClass
    public static void initApp() throws Exception {
        RestAssured.port = Integer.parseInt(ApplicationIntegration.SERVERPORT);
        ApplicationIntegration.main(new String[]{});
    }

    @AfterClass
    public static void closeApp() {
        ApplicationIntegration.exit(0);
    }
}
