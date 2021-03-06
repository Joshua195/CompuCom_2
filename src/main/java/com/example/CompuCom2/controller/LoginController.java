package com.example.CompuCom2.controller;

import com.example.CompuCom2.Constants.Constants;
import com.example.CompuCom2.model.UserAddressModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.example.CompuCom2.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private static final Log LOG = LogFactory.getLog(LoginController.class);

    @GetMapping("/login")
    public ModelAndView showLoginForm(@RequestParam(name = "error", required = false) String error,
                                      Integer logged){
        LOG.info("METHOD: showLoginForm()");
        ModelAndView modelAndView = new ModelAndView(Constants.LOGIN);
        modelAndView.addObject("error", error);
//        If the Client clicked to 'Add Product' without stayed logged
        if (logged != null){
            modelAndView.addObject("loggin", 1);
        }
        return modelAndView;
    }

    @GetMapping({"/loginsuccess", "/"})
    public ModelAndView loginCheck(HttpSession httpSession){
        ModelAndView modelAndView = new ModelAndView("redirect:/index");
        UserModel userGlobal  = (UserModel) httpSession.getAttribute("userGlobal");
        if (userGlobal != null) {
            if (userGlobal.getRoles().toString().contains("ADMIN")) {
                modelAndView.setViewName("redirect:/admin/index");
            }
        }
        return modelAndView;
    }

    @GetMapping("/registro")
    public ModelAndView register(Model model){
        LOG.info("METHOD: register()");
        ModelAndView modelAndView = new ModelAndView(Constants.REGISTER);
        UserModel pop = (UserModel) model.asMap().get("user");
        if (pop != null){
            modelAndView.addObject("userModel", pop);
            modelAndView.addObject("userAddressModel", model.asMap().get("address"));
            modelAndView.addObject("repeat", true);
        }else {
            modelAndView.addObject("userModel", new UserModel());
            modelAndView.addObject("userAddressModel", new UserAddressModel());
        }
        modelAndView.addObject("search", "");
        return modelAndView;

    }
}
