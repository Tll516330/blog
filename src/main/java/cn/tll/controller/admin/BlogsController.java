package cn.tll.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tll
 * @create 2020/9/18 14:12
 */
@Controller
@RequestMapping("/admin")
public class BlogsController {

    @GetMapping("/blogs")
    public String blogs(){
        return "admin/blogs";
    }
}
