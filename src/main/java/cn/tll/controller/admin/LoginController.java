package cn.tll.controller.admin;

import cn.tll.pojo.User;
import cn.tll.service.UserService;
import cn.tll.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author tll
 * @create 2020/9/16 16:22
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    /**
     * 注入 userService
     */
    @Autowired
    private UserService userService;

    /**
     * 到达后台登录界面
     * @return
     */
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    /**
     * 用户登录后台界面验证
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(username, MD5Utils.code(password));
        if (user!=null){
            //登录成功 跳转到后台页面
            //防止密码传输到前端页面
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else {
            //登录失败，重定向到登录界面 并返回信息
            attributes.addAttribute("message","用户名或密码错误");
            //重定向不可以使用Model 来传递信息  重定向会导致信息传递不过去
            //Model 中的信息是放在请求域中，重定向是另一个请求 所以拿不到信息
            return "redirect:/admin";

        }

    }

    /**
     * 用户退出登录
     * @return
     */
    @GetMapping("/logout")
    public String loginOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
