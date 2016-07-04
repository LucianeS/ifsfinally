package br.edu.ifrs.canoas.lds.ifsfinally.selenium;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.edu.ifrs.canoas.lds.ifsfinally.SpringStarterApplication;
import br.edu.ifrs.canoas.lds.ifsfinally.selenium.selenium.support.SeleniumTest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringStarterApplication.class)
@WebIntegrationTest(value = "server.port=9000")
@SeleniumTest(baseUrl = "http://localhost:9000")
public class HomeControllerTest {

    @Autowired
    private WebDriver driver;

    private HomePage homePage;

    @Before
    public void setUp() throws Exception {
        homePage = PageFactory.initElements(driver, HomePage.class);
    }

    @Test
    public void containsActuatorLinks() {
        homePage.assertThat()
                .hasActuatorLink("autoconfig", "beans", "configprops", "dump", "env", "health", "info", "metrics", "mappings", "trace")
                .hasNoActuatorLink("shutdown");
    }

    @Test
    @Ignore
    public void failingTest() {
        homePage.assertThat()
                .hasNoActuatorLink("autoconfig");
    }
}