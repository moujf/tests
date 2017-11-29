package demo.timeout;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * 超时测试之禁用配置
 *
 * @author mujf
 * @create 2017-11-29 13:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@TestPropertySource(properties = "hystrix.command.default.execution.timeout.enabled: false")
public class TimeoutDisableDemoTests {
    @LocalServerPort
    int port;

    @Test
    public void testOk() {
        ResponseEntity<String> response = new TestRestTemplate().getForEntity("http://localhost:" + port + "/to/ok", String.class);
        assertNotNull("response was null", response);
        assertEquals("Bad status code", HttpStatus.OK, response.getStatusCode());
        assertEquals("Wrong response text", "OK", response.getBody());
    }

    @Test
    public void test1m() {
        ResponseEntity<String> response = new TestRestTemplate().getForEntity("http://localhost:" + port + "/to/1m", String.class);
        assertNotNull("response was null", response);
        assertEquals("Bad status code", HttpStatus.OK, response.getStatusCode());
        assertEquals("Wrong response text", "wait 1m", response.getBody());
    }
}
