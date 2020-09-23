package cn.tll.pojo;

import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tll
 * @create 2020/9/16 14:46
 * 博客类型
 */
@Entity
@Table(name = "t_type")
public class Type {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    /**
     * 一个类型下会有多个博客  而一个博客对应一个类型
     * 所以类型对博客是  一对多的关系
     * 博客对应类型 是 多对一的关系
     * 关系被维护端
     */
    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();

    /**
     * 空构造
     */
    public Type() {
    }

    /**
     * get set
     * @return
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    /**
     * toString
     */
    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
