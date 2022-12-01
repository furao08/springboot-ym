package org.springframework.boot.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ASUS
 */
@RestController
public class TestController {
    @RequestMapping("/test")
    public String test() {
        return "hello world.";
    }
}
