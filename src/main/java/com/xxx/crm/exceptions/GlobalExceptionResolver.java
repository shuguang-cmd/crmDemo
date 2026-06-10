package com.xxx.crm.exceptions;

import com.alibaba.fastjson.JSON;
import com.xxx.base.ResultInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        
        // 非登录异常拦截
        if (ex instanceof NoLoginException) {
            ModelAndView mv = new ModelAndView("redirect:/index");
            return mv;
        }

        ModelAndView mv = new ModelAndView("error"); // 默认错误页
        mv.addObject("code", 500);
        mv.addObject("msg", "系统异常，请稍后再试...");

        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            ResponseBody responseBody = hm.getMethodAnnotation(ResponseBody.class);

            if (null == responseBody) {
                // 页面请求
                if (ex instanceof ParamsException) {
                    ParamsException p = (ParamsException) ex;
                    mv.addObject("code", p.getCode());
                    mv.addObject("msg", p.getMsg());
                }
                return mv;
            } else {
                // JSON 请求
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setCode(500);
                resultInfo.setMsg("系统异常，请稍后再试...");

                if (ex instanceof ParamsException) {
                    ParamsException p = (ParamsException) ex;
                    resultInfo.setCode(p.getCode());
                    resultInfo.setMsg(p.getMsg());
                }

                response.setContentType("application/json;charset=utf-8");
                try (PrintWriter out = response.getWriter()) {
                    out.write(JSON.toJSONString(resultInfo));
                    out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        return mv;
    }
}
