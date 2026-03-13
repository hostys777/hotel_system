package com.hotel.util;

import com.hotel.model.User;
import com.hotel.model.UserRole;
import javax.servlet.http.HttpSession;

public class PermissionUtils {
    
    /**
     * 检查用户是否有指定权限
     */
    public static boolean hasPermission(HttpSession session, String permission) {
        User user = (User) session.getAttribute("user");
        return user != null && user.hasPermission(permission);
    }
    
    /**
     * 检查用户是否可以管理用户
     */
    public static boolean canManageUsers(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return user != null && user.canManageUsers();
    }
    
    /**
     * 检查用户是否可以管理员工
     */
    public static boolean canManageEmployees(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return user != null && user.canManageEmployees();
    }
    
    /**
     * 检查用户是否已登录
     */
    public static boolean isLoggedIn(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return user != null;
    }
    
    /**
     * 获取当前用户
     */
    public static User getCurrentUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }
    
    /**
     * 检查用户角色
     */
    public static boolean hasRole(HttpSession session, UserRole role) {
        User user = (User) session.getAttribute("user");
        return user != null && user.getRole() == role;
    }
}
