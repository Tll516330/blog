package cn.tll.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tll
 * @create 2020/9/16 14:49
 * 标签实体类
 */
@Entity
@Table(name = "t_tag")
public class Tag {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    /**
     * 多对多的关系
     */
    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs = new ArrayList<>();

    /**
     * 空构造
     */
    public Tag() {
    }

    /**
     * get set
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
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
