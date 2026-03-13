package com.hotel.controller;

import com.hotel.model.Employee;
import com.hotel.repository.EmployeeRepository;
import com.hotel.repository.FoodOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

/**
 * 员工管理控制器
 */
@Controller
@RequestMapping("/employees")
public class EmployeeController {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private FoodOrderRepository foodOrderRepository;
    
    @GetMapping
    public String listEmployees(Model model) {
        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "employees/list";
    }
    
    @GetMapping("/add")
    public String addEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/add";
    }
    
    @PostMapping("/add")
    public String addEmployee(@ModelAttribute Employee employee, Model model) {
        // 检查工号是否已存在
        Employee existingEmployee = employeeRepository.findByEmployeeNumber(employee.getEmployeeNumber());
        if (existingEmployee != null) {
            model.addAttribute("error", "工号 " + employee.getEmployeeNumber() + " 已存在，请使用其他工号");
            return "employees/add";
        }
        
        // 设置默认状态为在职
        if (employee.getIsActive() == null) {
            employee.setIsActive(true);
        }
        
        employeeRepository.save(employee);
        return "redirect:/employees";
    }
    
    @GetMapping("/edit/{id}")
    public String editEmployeeForm(@PathVariable Long id, Model model) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            return "redirect:/employees";
        }
        model.addAttribute("employee", employee);
        return "employees/edit";
    }
    
    @PostMapping("/edit")
    public String editEmployee(@ModelAttribute Employee employee, Model model) {
        // 检查工号是否被其他员工使用
        Employee existingEmployee = employeeRepository.findByEmployeeNumber(employee.getEmployeeNumber());
        if (existingEmployee != null && !existingEmployee.getId().equals(employee.getId())) {
            model.addAttribute("error", "工号 " + employee.getEmployeeNumber() + " 已被其他员工使用，请使用其他工号");
            return "employees/edit";
        }
        
        employeeRepository.save(employee);
        return "redirect:/employees";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            // 检查员工是否存在
            Employee employee = employeeRepository.findById(id).orElse(null);
            if (employee == null) {
                redirectAttributes.addFlashAttribute("error", "员工不存在");
                return "redirect:/employees";
            }
            
            // 检查是否有相关的餐饮订单
            long foodOrderCount = foodOrderRepository.countByEmployeeId(id);
            if (foodOrderCount > 0) {
                redirectAttributes.addFlashAttribute("error", "该员工有 " + foodOrderCount + " 个餐饮订单，无法删除。请先处理相关订单。");
                return "redirect:/employees";
            }
            
            // 如果没有关联数据，则删除员工
            employeeRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "员工删除成功");
            
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "删除失败：该员工有关联数据，无法删除。");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "删除失败：" + e.getMessage());
        }
        
        return "redirect:/employees";
    }
    
    @GetMapping("/toggle-status/{id}")
    public String toggleEmployeeStatus(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            employee.setIsActive(!employee.getIsActive());
            employeeRepository.save(employee);
        }
        return "redirect:/employees";
    }
    
    @GetMapping("/search")
    public String searchEmployees(@RequestParam String name, Model model) {
        List<Employee> employees = employeeRepository.findByNameContaining(name);
        model.addAttribute("employees", employees);
        model.addAttribute("searchName", name);
        return "employees/search";
    }
}
