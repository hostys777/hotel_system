package com.hotel.controller;

import com.hotel.model.User;
import com.hotel.model.UserRole;
import com.hotel.repository.UserRepository;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, 
                       @RequestParam String password,
                       HttpSession session,
                       RedirectAttributes redirectAttributes) {
        
        System.out.println("登录尝试 - 用户名: " + username + ", 密码: " + password);
        
        // 简单的硬编码用户验证
        if ("admin".equals(username) && "admin123".equals(password)) {
            System.out.println("管理员登录成功");
            User user = new User("admin", "admin123", "系统管理员", UserRole.ADMIN);
            session.setAttribute("user", user);
            session.setAttribute("userRole", user.getRole());
            session.setAttribute("userName", user.getFullName());
            return "redirect:/";
        } else if ("manager".equals(username) && "manager123".equals(password)) {
            System.out.println("经理登录成功");
            User user = new User("manager", "manager123", "张经理", UserRole.MANAGER);
            session.setAttribute("user", user);
            session.setAttribute("userRole", user.getRole());
            session.setAttribute("userName", user.getFullName());
            return "redirect:/";
        } else if ("frontdesk1".equals(username) && "front123".equals(password)) {
            System.out.println("前台1登录成功");
            User user = new User("frontdesk1", "front123", "李前台", UserRole.FRONT_DESK);
            session.setAttribute("user", user);
            session.setAttribute("userRole", user.getRole());
            session.setAttribute("userName", user.getFullName());
            return "redirect:/";
        } else if ("frontdesk2".equals(username) && "front123".equals(password)) {
            System.out.println("前台2登录成功");
            User user = new User("frontdesk2", "front123", "王前台", UserRole.FRONT_DESK);
            session.setAttribute("user", user);
            session.setAttribute("userRole", user.getRole());
            session.setAttribute("userName", user.getFullName());
            return "redirect:/";
        }
        
        System.out.println("登录失败 - 用户名: " + username + ", 密码: " + password);
        redirectAttributes.addFlashAttribute("error", "用户名或密码错误");
        return "redirect:/login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("success", "已成功退出登录");
        return "redirect:/login";
    }

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        
        model.addAttribute("user", user);
        return "index";
    }

    // 权限检查辅助方法
    public static boolean hasPermission(HttpSession session, String permission) {
        User user = (User) session.getAttribute("user");
        return user != null && user.hasPermission(permission);
    }

    public static boolean canManageUsers(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return user != null && user.canManageUsers();
    }

    public static boolean canManageEmployees(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return user != null && user.canManageEmployees();
    }
}
