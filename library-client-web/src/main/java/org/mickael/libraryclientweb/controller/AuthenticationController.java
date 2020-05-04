package org.mickael.libraryclientweb.controller;

import org.mickael.libraryclientweb.bean.customer.AccountLoginBean;
import org.mickael.libraryclientweb.proxy.FeignBookProxy;
import org.mickael.libraryclientweb.security.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AuthenticationController {

    private final FeignBookProxy feignBookProxy;
    private static final String LOGIN_VIEW = "login";
    private static final String HOME_VIEW = "home";
    private static final String REGISTRATION_VIEW = "register";
    private static final String REDIRECT_LOGIN_VIEW = "redirect:/login";
    private static final String REDIRECT_HOME_VIEW = "redirect:/home";
    private static final String CUSTOMER_ATT = "customer";
    private static final String USERNAME_FIELD = "username";
    private static final String BAD_CREDENTIALS_MSG = "Mauvais login/mot de passe";

    @Autowired
    public AuthenticationController(FeignBookProxy feignBookProxy) {
        this.feignBookProxy = feignBookProxy;
    }

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("user", new AccountLoginBean());
        return LOGIN_VIEW;
    }

/*    @PostMapping("/login")
    public String login(@Valid AccountLoginBean accountLoginDto, Model model, HttpServletResponse response,
                        BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            bindingResult.addError(new FieldError(CUSTOMER_ATT, USERNAME_FIELD, BAD_CREDENTIALS_MSG));
            model.addAttribute("user", accountLoginDto);
            return LOGIN_VIEW;
        }
        ResponseEntity responseEntity;
        try {
            responseEntity = feignBookProxy.login(accountLoginDto);
        } catch (Exception e){
            model.addAttribute("user", accountLoginDto);
            return LOGIN_VIEW;
        }
 *//*       ResponseEntity responseEntity = feignProxy.login(accountLoginDto);
        if (responseEntity.getStatusCode().is4xxClientError()){
            model.addAttribute("user", accountLoginDto);
            return LOGIN_VIEW;
        }*//*
        String token = responseEntity.getHeaders().getFirst("Authorization").replace("Bearer ", "");
        Cookie cookie = CookieUtils.generateCookie(token);
        response.addCookie(cookie);
        return REDIRECT_HOME_VIEW;
    }

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("user", new AccountLoginBean());
        return REGISTRATION_VIEW;
    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody CustomerBean customerBean){
        ResponseEntity responseEntity = feignBookProxy.register(customerBean);
        if (responseEntity.getStatusCode().equals(HttpStatus.UNAUTHORIZED)){
            return REGISTRATION_VIEW;
        }
        return REDIRECT_HOME_VIEW;
    }*/

    @GetMapping({"/home", "/", "/index"})
    public String home(HttpServletRequest request, HttpSession session){
        String value = CookieUtils.getValue(request, CookieUtils.HEADER);
        if (value != null){
            session.setAttribute("logged", "true");
        }
        return HOME_VIEW;
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response, HttpSession session){
        CookieUtils.clear(response);
        session.invalidate();
        return REDIRECT_LOGIN_VIEW;
    }
}
