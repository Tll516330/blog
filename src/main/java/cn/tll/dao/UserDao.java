package cn.tll.dao;

import cn.tll.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tll
 * @create 2020/9/16 16:12
 */
public interface UserDao extends JpaRepository<User,Long> {

    /**
     * Jpa 自动根据username 和password查询用户
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username,String password);
}
