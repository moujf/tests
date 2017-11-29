package demo.timemout;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 演示超时情况
 *
 * @author mujf
 * @create 2017-11-29 13:07
 */
public class TimeoutDemoService {

    public String ok() {
        return "OK";
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public String sleep10s() throws InterruptedException {
        TimeUnit.SECONDS.sleep(9);
        return "wait 10s";
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public String sleep1m() throws InterruptedException {
        TimeUnit.MINUTES.sleep(1);
        return "wait 1m";
    }

    public String fallback() {
        return "from the fallback";
    }

}
