package jpabook.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

//    Logger log = LoggerFactory.getLogger(getClass());     // @Slf4j와 같은 역할

    @RequestMapping("/")
    public String home() {
        log.info("home controller");
        return "home";  // home.html 타임리프 파일로 찾아가게됨
    }

}
