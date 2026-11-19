package com.taskflow.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Day 1: the first REST endpoint.
 *
 * @RestController = @Controller + @ResponseBody, so whatever a method returns
 * becomes the HTTP response body. The returned Map is serialized to JSON
 * automatically by Jackson, which spring-boot-starter-web auto-configured.
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public Map<String, String> hello(@RequestParam(defaultValue = "world") String name) {
        return Map.of("message", "Hello, " + name + "!");
    }
}
