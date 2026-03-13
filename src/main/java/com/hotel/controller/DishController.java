package com.hotel.controller;

import com.hotel.model.Dish;
import com.hotel.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * 菜品管理控制器
 */
@Controller
@RequestMapping("/dishes")
public class DishController {
    
    @Autowired
    private DishRepository dishRepository;
    
    /**
     * 菜品列表页面
     */
    @GetMapping
    public String listDishes(Model model, 
                           @RequestParam(required = false) String category,
                           @RequestParam(required = false) String search) {
        List<Dish> dishes;
        
        if (search != null && !search.trim().isEmpty()) {
            // 按名称搜索
            dishes = dishRepository.findByNameContaining(search.trim());
        } else if (category != null && !category.trim().isEmpty()) {
            // 按分类筛选
            dishes = dishRepository.findByCategoryAndIsAvailableTrue(category);
        } else {
            // 显示所有可用菜品
            dishes = dishRepository.findByIsAvailableTrue();
        }
        
        // 获取所有分类用于筛选
        List<String> categories = dishRepository.findDistinctCategories();
        
        model.addAttribute("dishes", dishes);
        model.addAttribute("categories", categories);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("searchTerm", search);
        
        return "dishes/list";
    }
    
    /**
     * 添加菜品页面
     */
    @GetMapping("/add")
    public String addDishForm(Model model) {
        model.addAttribute("dish", new Dish());
        model.addAttribute("categories", dishRepository.findDistinctCategories());
        return "dishes/add";
    }
    
    /**
     * 保存新菜品
     */
    @PostMapping("/add")
    public String addDish(@ModelAttribute Dish dish, RedirectAttributes redirectAttributes) {
        try {
            // 设置默认值
            if (dish.getIsAvailable() == null) {
                dish.setIsAvailable(true);
            }
            if (dish.getStockQuantity() == null) {
                dish.setStockQuantity(0);
            }
            
            dishRepository.save(dish);
            redirectAttributes.addFlashAttribute("successMessage", "菜品添加成功！");
            return "redirect:/dishes";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "添加菜品失败：" + e.getMessage());
            return "redirect:/dishes/add";
        }
    }
    
    /**
     * 编辑菜品页面
     */
    @GetMapping("/edit/{id}")
    public String editDishForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Dish dish = dishRepository.findById(id).orElse(null);
        if (dish == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "菜品不存在！");
            return "redirect:/dishes";
        }
        
        model.addAttribute("dish", dish);
        model.addAttribute("categories", dishRepository.findDistinctCategories());
        return "dishes/edit";
    }
    
    /**
     * 更新菜品
     */
    @PostMapping("/edit/{id}")
    public String updateDish(@PathVariable Long id, @ModelAttribute Dish dish, RedirectAttributes redirectAttributes) {
        try {
            Dish existingDish = dishRepository.findById(id).orElse(null);
            if (existingDish == null) {
                redirectAttributes.addFlashAttribute("errorMessage", "菜品不存在！");
                return "redirect:/dishes";
            }
            
            // 更新字段
            existingDish.setName(dish.getName());
            existingDish.setDescription(dish.getDescription());
            existingDish.setPrice(dish.getPrice());
            existingDish.setCategory(dish.getCategory());
            existingDish.setStockQuantity(dish.getStockQuantity());
            existingDish.setIsAvailable(dish.getIsAvailable());
            
            dishRepository.save(existingDish);
            redirectAttributes.addFlashAttribute("successMessage", "菜品更新成功！");
            return "redirect:/dishes";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "更新菜品失败：" + e.getMessage());
            return "redirect:/dishes/edit/" + id;
        }
    }
    
    /**
     * 删除菜品
     */
    @PostMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            Dish dish = dishRepository.findById(id).orElse(null);
            if (dish == null) {
                redirectAttributes.addFlashAttribute("errorMessage", "菜品不存在！");
                return "redirect:/dishes";
            }
            
            // 软删除：设置为不可用
            dish.setIsAvailable(false);
            dishRepository.save(dish);
            
            redirectAttributes.addFlashAttribute("successMessage", "菜品已删除！");
            return "redirect:/dishes";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "删除菜品失败：" + e.getMessage());
            return "redirect:/dishes";
        }
    }
    
    /**
     * 切换菜品可用状态
     */
    @PostMapping("/toggle/{id}")
    public String toggleDishStatus(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            Dish dish = dishRepository.findById(id).orElse(null);
            if (dish == null) {
                redirectAttributes.addFlashAttribute("errorMessage", "菜品不存在！");
                return "redirect:/dishes";
            }
            
            dish.setIsAvailable(!dish.getIsAvailable());
            dishRepository.save(dish);
            
            String status = dish.getIsAvailable() ? "启用" : "禁用";
            redirectAttributes.addFlashAttribute("successMessage", "菜品已" + status + "！");
            return "redirect:/dishes";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "操作失败：" + e.getMessage());
            return "redirect:/dishes";
        }
    }
}
