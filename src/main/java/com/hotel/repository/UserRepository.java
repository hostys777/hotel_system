package com.hotel.repository;

import com.hotel.model.User;
import com.hotel.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // 根据用户名查找用户
    Optional<User> findByUsername(String username);
    
    // 根据用户名和密码查找用户
    Optional<User> findByUsernameAndPassword(String username, String password);
    
    // 查找活跃用户
    List<User> findByActiveTrue();
    
    // 根据角色查找用户
    List<User> findByRole(UserRole role);
    
    // 根据角色和活跃状态查找用户
    List<User> findByRoleAndActiveTrue(UserRole role);
    
    // 检查用户名是否存在
    boolean existsByUsername(String username);
    
    // 根据姓名模糊查询
    @Query("SELECT u FROM User u WHERE u.fullName LIKE %:name% AND u.active = true")
    List<User> findByFullNameContaining(@Param("name") String name);
    
    // 统计各角色用户数量
    @Query("SELECT u.role, COUNT(u) FROM User u WHERE u.active = true GROUP BY u.role")
    List<Object[]> countUsersByRole();
}
