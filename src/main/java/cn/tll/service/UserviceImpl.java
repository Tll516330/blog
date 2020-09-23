package cn.tll.service;

import cn.tll.dao.UserDao;
import cn.tll.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tll
 * @create 2020/9/16 16:12
 * Service一定要加上 @Service 才可用
 */
@Service
public class UserviceImpl implements UserService{

    /**
     * 注入持久层
     */
    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        User user = userDao.findByUsernameAndPassword(username,password);
        return user;
    }
}
