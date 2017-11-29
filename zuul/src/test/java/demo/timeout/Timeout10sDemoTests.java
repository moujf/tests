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
 * 演示timeout之10秒
 *
 * @author mujf
 * @create 2017-11-29 13:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@TestPropertySource(properties = "hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds: 10000")
public class Timeout10sDemoTests {
    @LocalServerPort
    int port;

    @Test
    public void test10s() {
        assertEquals("timeout 10s", new TestRestTemplate()
                .getForObject("http://localhost:" + this.port + "/static/10s", String.class));
    }
}
