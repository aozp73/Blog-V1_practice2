package shop.mtcoding.blogv1_2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/tes")
    public String tes() {
        return "board/tes";
    }
}
