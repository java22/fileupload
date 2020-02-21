package com.mango.fileupload;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class MyCustomException {

    /*@ExceptionHandler(MaxUploadSizeExceededException.class)
    public void myException(MaxUploadSizeExceededException e, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write("上传文件异常！");
        out.flush();
        out.close();
    }*/

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView myException(MaxUploadSizeExceededException e) throws IOException {
        ModelAndView modelAndView = new ModelAndView("myerror");
        modelAndView.addObject("error","上传文件超出大小限制！");
        return modelAndView;
    }
}
