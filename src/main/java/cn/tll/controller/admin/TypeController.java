package cn.tll.controller.admin;

import cn.tll.pojo.Type;
import cn.tll.service.TypeService;
import javassist.runtime.Desc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author tll
 * @create 2020/9/18 15:08
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    /**
     * 注入service
     */
    @Autowired
    private TypeService typeService;

    /**
     * 分页查询
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/types")
    public String types(@PageableDefault(size = 8,sort = {"id"},direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model){
        model.addAttribute("page",typeService.listPage(pageable));
        return "admin/types";
    }

    /**
     * 到达新增类型页面
     * @return
     */
    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    /**
     * 新增type
     * @param type
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/types")
    public String post(Type type, BindingResult result, RedirectAttributes attributes){
        Type type1 = typeService.findByName(type.getName());
        if (type1!=null){
            result.rejectValue("name","nameError","不能重复添加分类");
        }
        if (result.hasErrors()){
            return "admin/types-input";
        }
        Type t = typeService.saveType(type);
        if (t == null){
            attributes.addFlashAttribute("message","操作失败");

        }else {
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/types";
    }
}
