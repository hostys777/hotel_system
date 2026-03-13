package com.hotel.controller;

import com.hotel.model.Customer;
import com.hotel.repository.CustomerRepository;
import com.hotel.repository.FoodOrderRepository;
import com.hotel.repository.ReservationRepository;
import com.hotel.repository.ParkingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

/**
 * 客户管理控制器
 */
@Controller
@RequestMapping("/customers")
public class CustomerController {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private FoodOrderRepository foodOrderRepository;
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private ParkingRecordRepository parkingRecordRepository;
    
    @GetMapping
    public String listCustomers(Model model) {
        List<Customer> customers = customerRepository.findAll();
        model.addAttribute("customers", customers);
        return "customers/list";
    }
    
    @GetMapping("/add")
    public String addCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customers/add";
    }
    
    @PostMapping("/add")
    public String addCustomer(@ModelAttribute Customer customer) {
        customerRepository.save(customer);
        return "redirect:/customers";
    }
    
    @GetMapping("/edit/{id}")
    public String editCustomerForm(@PathVariable Long id, Model model) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return "redirect:/customers";
        }
        model.addAttribute("customer", customer);
        return "customers/edit";
    }
    
    @PostMapping("/edit")
    public String editCustomer(@ModelAttribute Customer customer) {
        customerRepository.save(customer);
        return "redirect:/customers";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            // 检查客户是否存在
            Customer customer = customerRepository.findById(id).orElse(null);
            if (customer == null) {
                redirectAttributes.addFlashAttribute("error", "客户不存在");
                return "redirect:/customers";
            }
            
            // 检查是否有相关的餐饮订单
            long foodOrderCount = foodOrderRepository.countByCustomerId(id);
            if (foodOrderCount > 0) {
                redirectAttributes.addFlashAttribute("error", "该客户有 " + foodOrderCount + " 个餐饮订单，无法删除。请先处理相关订单。");
                return "redirect:/customers";
            }
            
            // 检查是否有相关的预订记录
            long reservationCount = reservationRepository.countByCustomerId(id);
            if (reservationCount > 0) {
                redirectAttributes.addFlashAttribute("error", "该客户有 " + reservationCount + " 个预订记录，无法删除。请先处理相关预订。");
                return "redirect:/customers";
            }
            
            // 检查是否有相关的停车记录
            long parkingRecordCount = parkingRecordRepository.countByCustomerId(id);
            if (parkingRecordCount > 0) {
                redirectAttributes.addFlashAttribute("error", "该客户有 " + parkingRecordCount + " 个停车记录，无法删除。请先处理相关停车记录。");
                return "redirect:/customers";
            }
            
            // 如果没有关联数据，则删除客户
            customerRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "客户删除成功");
            
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "删除失败：该客户有关联数据，无法删除。");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "删除失败：" + e.getMessage());
        }
        
        return "redirect:/customers";
    }
    
    @GetMapping("/search")
    public String searchCustomers(@RequestParam String name, Model model) {
        List<Customer> customers = customerRepository.findByNameContaining(name);
        model.addAttribute("customers", customers);
        model.addAttribute("searchName", name);
        return "customers/search";
    }
}
