package cn.tll.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tll
 * @create 2020/9/11 16:41
 * 异常拦截器
 */
@ControllerAdvice
public class ControllerExceptionHandle {

    /**
     * 获取日志对象
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 统一拦截错误 异常处理
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandle(HttpServletRequest request,Exception e) throws Exception {
        logger.error("Request URL : {},Exception : {}",request.getRequestURI(),e);

        /**
         * 如果对应的异常有处理 交给处理页面单独处理
         * 有处理页面则不为空  无则为空
         */
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
            throw e;
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURI());
        mv.addObject("exception",e);
        mv.setViewName("error/error");
        return mv;
    }
}
