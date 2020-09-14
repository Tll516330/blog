package cn.tll.controller;


import cn.tll.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * @author tll
 * @create 2020/9/11 16:53
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "blog";
    }
}
