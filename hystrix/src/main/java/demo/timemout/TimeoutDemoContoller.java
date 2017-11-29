package demo.timemout;

import demo.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 演示超时
 *
 * @author mujf
 * @create 2017-11-29 13:13
 */
@RestController
public class TimeoutDemoContoller {

    @Bean
    public TimeoutDemoService timeoutDemoService() {
        return new TimeoutDemoService();
    }

    @Autowired
    private TimeoutDemoService timeoutDemoService;

    @RequestMapping("/to/ok")
    public String ok() throws InterruptedException {
        return this.timeoutDemoService.ok();
    }

    @RequestMapping("/to/10s")
    public String sleep10s() throws InterruptedException {
        return this.timeoutDemoService.sleep10s();
    }

    @RequestMapping("/to/1m")
    public String sleep1m() throws InterruptedException {
        return this.timeoutDemoService.sleep1m();
    }
}
