package server;

import org.springframework.boot.test.WebIntegrationTest;

@WebIntegrationTest("server.port=9898")
public abstract class AbstractTestIntegration extends AbstractTest{
}
