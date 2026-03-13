package com.hotel.repository;

import com.hotel.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜品数据访问接口
 */
@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    
    /**
     * 根据分类查找菜品
     */
    List<Dish> findByCategory(String category);
    
    /**
     * 查找可用的菜品
     */
    List<Dish> findByIsAvailableTrue();
    
    /**
     * 根据分类和可用状态查找菜品
     */
    List<Dish> findByCategoryAndIsAvailableTrue(String category);
    
    /**
     * 根据名称模糊查询
     */
    List<Dish> findByNameContaining(String name);
    
    /**
     * 查找所有不重复的分类
     */
    @Query("SELECT DISTINCT d.category FROM Dish d WHERE d.category IS NOT NULL")
    List<String> findDistinctCategories();
}
