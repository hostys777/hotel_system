package com.hotel.controller;

import com.hotel.model.*;
import com.hotel.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * 餐饮订单管理控制器
 */
@Controller
@RequestMapping("/food-orders")
public class FoodOrderController {
    
    @Autowired
    private FoodOrderRepository foodOrderRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private DishRepository dishRepository;
    
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    /**
     * 订单列表页面
     */
    @GetMapping
    public String listOrders(Model model) {
        List<FoodOrder> orders = foodOrderRepository.findAll();
        
        // 调试信息：打印每个订单的详细信息
        for (FoodOrder order : orders) {
            System.out.println("订单ID: " + order.getId() + 
                             ", 总金额: " + order.getTotalAmount() + 
                             ", 订单项数量: " + (order.getOrderItems() != null ? order.getOrderItems().size() : 0));
            
            if (order.getOrderItems() != null) {
                for (OrderItem item : order.getOrderItems()) {
                    System.out.println("  - 菜品: " + item.getDish().getName() + 
                                     ", 数量: " + item.getQuantity() + 
                                     ", 单价: " + item.getUnitPrice() + 
                                     ", 小计: " + item.getTotalPrice());
                }
            }
        }
        
        model.addAttribute("orders", orders);
        return "food-orders/list";
    }
    
    /**
     * 创建订单页面
     */
    @GetMapping("/add")
    public String addOrderForm(Model model) {
        model.addAttribute("order", new FoodOrder());
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("employees", employeeRepository.findByIsActiveTrue());
        model.addAttribute("dishes", dishRepository.findByIsAvailableTrue());
        model.addAttribute("categories", dishRepository.findDistinctCategories());
        return "food-orders/add";
    }
    
    /**
     * 处理创建订单
     */
    @PostMapping("/add")
    public String addOrder(@RequestParam Long customerId,
                          @RequestParam Long employeeId,
                          @RequestParam String tableNumber,
                          @RequestParam(required = false) String roomNumber,
                          @RequestParam(required = false) String notes,
                          Model model) {
        
        Customer customer = customerRepository.findById(customerId).orElse(null);
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        
        if (customer != null && employee != null) {
            FoodOrder order = new FoodOrder(customer, employee, tableNumber);
            order.setRoomNumber(roomNumber);
            order.setNotes(notes);
            order.setTotalAmount(BigDecimal.ZERO);
            
            FoodOrder savedOrder = foodOrderRepository.save(order);
            
            // 重新加载页面，保持表单数据并传递订单ID
            model.addAttribute("orderId", savedOrder.getId());
            model.addAttribute("customers", customerRepository.findAll());
            model.addAttribute("employees", employeeRepository.findByIsActiveTrue());
            model.addAttribute("dishes", dishRepository.findByIsAvailableTrue());
            model.addAttribute("categories", dishRepository.findDistinctCategories());
            model.addAttribute("selectedCustomerId", customerId);
            model.addAttribute("selectedEmployeeId", employeeId);
            model.addAttribute("selectedTableNumber", tableNumber);
            model.addAttribute("selectedRoomNumber", roomNumber);
            model.addAttribute("selectedNotes", notes);
            
            return "food-orders/add";
        }
        
        return "redirect:/food-orders/add";
    }
    
    /**
     * 添加菜品到订单
     */
    @PostMapping("/add-item")
    public String addOrderItem(@RequestParam Long orderId,
                              @RequestParam Long dishId,
                              @RequestParam Integer quantity,
                              Model model) {
        
        FoodOrder order = foodOrderRepository.findById(orderId).orElse(null);
        Dish dish = dishRepository.findById(dishId).orElse(null);
        
        if (order != null && dish != null && quantity > 0) {
            // 检查库存
            if (dish.getStockQuantity() >= quantity) {
                OrderItem orderItem = new OrderItem(order, dish, quantity);
                orderItemRepository.save(orderItem);
                
                // 调试信息
                System.out.println("添加菜品到订单: " + dish.getName() + 
                                 ", 数量: " + quantity + 
                                 ", 单价: " + dish.getPrice() + 
                                 ", 小计: " + orderItem.getTotalPrice());
                
                // 更新库存
                dish.setStockQuantity(dish.getStockQuantity() - quantity);
                dishRepository.save(dish);
                
                // 重新计算订单总金额
                updateOrderTotalAmount(orderId);
                
                // 添加成功消息
                model.addAttribute("successMessage", "菜品添加成功！");
            } else {
                model.addAttribute("errorMessage", "库存不足，无法添加该菜品");
            }
        } else {
            model.addAttribute("errorMessage", "添加菜品失败，请检查订单和菜品信息");
        }
        
        // 重新加载页面，保持表单数据
        model.addAttribute("orderId", orderId);
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("employees", employeeRepository.findByIsActiveTrue());
        model.addAttribute("dishes", dishRepository.findByIsAvailableTrue());
        model.addAttribute("categories", dishRepository.findDistinctCategories());
        model.addAttribute("selectedCustomerId", order.getCustomer().getId());
        model.addAttribute("selectedEmployeeId", order.getEmployee().getId());
        model.addAttribute("selectedTableNumber", order.getTableNumber());
        model.addAttribute("selectedRoomNumber", order.getRoomNumber());
        model.addAttribute("selectedNotes", order.getNotes());
        
        return "food-orders/add";
    }
    
    /**
     * 确认订单
     */
    @PostMapping("/confirm/{id}")
    public String confirmOrder(@PathVariable Long id) {
        FoodOrder order = foodOrderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setStatus(OrderStatus.CONFIRMED);
            foodOrderRepository.save(order);
        }
        return "redirect:/food-orders";
    }
    
    /**
     * 开始制作
     */
    @PostMapping("/prepare/{id}")
    public String prepareOrder(@PathVariable Long id) {
        FoodOrder order = foodOrderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setStatus(OrderStatus.PREPARING);
            foodOrderRepository.save(order);
        }
        return "redirect:/food-orders";
    }
    
    /**
     * 完成制作
     */
    @PostMapping("/ready/{id}")
    public String readyOrder(@PathVariable Long id) {
        FoodOrder order = foodOrderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setStatus(OrderStatus.READY);
            foodOrderRepository.save(order);
        }
        return "redirect:/food-orders";
    }
    
    /**
     * 上菜
     */
    @PostMapping("/serve/{id}")
    public String serveOrder(@PathVariable Long id) {
        FoodOrder order = foodOrderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setStatus(OrderStatus.SERVED);
            foodOrderRepository.save(order);
        }
        return "redirect:/food-orders";
    }
    
    /**
     * 取消订单
     */
    @PostMapping("/cancel/{id}")
    public String cancelOrder(@PathVariable Long id) {
        FoodOrder order = foodOrderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setStatus(OrderStatus.CANCELLED);
            foodOrderRepository.save(order);
            
            // 恢复库存
            List<OrderItem> items = orderItemRepository.findByOrderId(id);
            for (OrderItem item : items) {
                Dish dish = item.getDish();
                dish.setStockQuantity(dish.getStockQuantity() + item.getQuantity());
                dishRepository.save(dish);
            }
        }
        return "redirect:/food-orders";
    }
    
    /**
     * 支付订单页面
     */
    @GetMapping("/pay/{id}")
    public String payOrderForm(@PathVariable Long id, Model model) {
        FoodOrder order = foodOrderRepository.findById(id).orElse(null);
        if (order == null) {
            return "redirect:/food-orders";
        }
        model.addAttribute("order", order);
        model.addAttribute("paymentMethods", PaymentMethod.values());
        return "food-orders/pay";
    }
    
    /**
     * 处理支付
     */
    @PostMapping("/pay/{id}")
    public String payOrder(@PathVariable Long id, @RequestParam PaymentMethod paymentMethod) {
        FoodOrder order = foodOrderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setPaymentMethod(paymentMethod);
            order.setIsPaid(true);
            order.setStatus(OrderStatus.PAID);
            foodOrderRepository.save(order);
        }
        return "redirect:/food-orders";
    }
    
    /**
     * 生成支付二维码
     */
    @GetMapping("/qr-code/{id}")
    public String generateQRCode(@PathVariable Long id, Model model) {
        FoodOrder order = foodOrderRepository.findById(id).orElse(null);
        if (order == null) {
            return "redirect:/food-orders";
        }
        model.addAttribute("order", order);
        return "food-orders/qr-code";
    }
    
    /**
     * 二维码测试页面
     */
    @GetMapping("/qr-test")
    public String qrTest() {
        return "food-orders/qr-test";
    }
    
    /**
     * 更新订单总金额
     */
    private void updateOrderTotalAmount(Long orderId) {
        FoodOrder order = foodOrderRepository.findById(orderId).orElse(null);
        if (order != null) {
            List<OrderItem> items = orderItemRepository.findByOrderId(orderId);
            BigDecimal totalAmount = items.stream()
                    .map(OrderItem::getTotalPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            
            // 调试信息
            System.out.println("更新订单总金额 - 订单ID: " + orderId + 
                             ", 订单项数量: " + items.size() + 
                             ", 计算总金额: " + totalAmount);
            
            order.setTotalAmount(totalAmount);
            foodOrderRepository.save(order);
        }
    }
}
