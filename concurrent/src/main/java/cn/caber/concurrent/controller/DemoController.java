package cn.caber.concurrent.controller;

import cn.caber.concurrent.test.TestCallable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private TestCallable testCallable;
    @RequestMapping("/test")
    public String test(){
        testCallable.test("zhangsan");
        return "成功";
    }

}
