package cn.tll.controller.admin;

import cn.tll.pojo.Tag;
import cn.tll.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author tll
 * @create 2020/9/23 15:23
 */
@Controller
@RequestMapping("/admin")
public class TagsController {

    /**
     * 注入
     */
    @Autowired
    private TagsService tagsService;

    /**
     * 分页查询
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 8,sort = {"id"},
            direction = Sort.Direction.DESC)Pageable pageable,
                       Model model){
        model.addAttribute("page",tagsService.listPage(pageable));
        return "admin/tags";

    }

    /**
     * 跳转到新增标签页面
     * @param model
     * @return
     */
    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String post(Tag tag, BindingResult result, RedirectAttributes attributes){
        Tag tag1 = tagsService.findByName(tag.getName());
        if (tag1!=null){
            result.rejectValue("name","nameError","不能重复添加标签");
        }
        if (result.hasErrors()){
            return "admin/tags-input";
        }

        Tag t = tagsService.saveTag(tag);
        if (t == null){
            attributes.addFlashAttribute("message","新增失败");

        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/tags";
    }


    /**
     * 更新标签
     * @param tag
     * @param result
     * @param id
     * @param attributes
     * @return
     */
    @PostMapping("/tags/{id}")
    public String editPost(Tag tag, BindingResult result, @PathVariable Long id,RedirectAttributes attributes){
        Tag tag1 = tagsService.findByName(tag.getName());
        if (tag1!=null){
            result.rejectValue("name","nameError","不能重复添加标签");

        }
        if (result.hasErrors()){
            return "admin/tags-input";
        }
        Tag tag2 = tagsService.updateTag(id, tag);
        if (tag2 == null){
            attributes.addFlashAttribute("message","更新失败");
        }else {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/tags";
    }

    /**
     * 跳转到新增标签页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        model.addAttribute("tag",tagsService.getTag(id));
        return "admin/tags-input";
    }

    /**
     * 删除标签
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        tagsService.deleteTag(id);
        attributes.addFlashAttribute("message","删除标签成功");
        return "redirect:/admin/tags";
    }

}
