package com.example.blogproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //RestApi 용 컨트롤러 json을 반환함
public class FirstApiController {
    @GetMapping("/api/hello")
    public String hello(){
        return "hello world"; // 뷰페이지가 아닌 데이터를 반환함
    }
}
