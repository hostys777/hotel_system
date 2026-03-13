package com.hotel.controller;

import com.hotel.model.*;
import com.hotel.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/parking")
public class ParkingController {
    
    @Autowired
    private ParkingSpotRepository parkingSpotRepository;
    
    @Autowired
    private ParkingRecordRepository parkingRecordRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    /**
     * 停车管理首页
     */
    @GetMapping
    public String index(Model model) {
        // 获取统计数据
        List<ParkingSpot> allSpots = parkingSpotRepository.findAll();
        List<ParkingRecord> activeRecords = parkingRecordRepository.findActiveRecords();
        List<ParkingSpot> availableSpots = parkingSpotRepository.findAvailableSpots();
        
        // 计算统计信息
        long totalSpots = allSpots.size();
        long occupiedSpots = allSpots.stream().mapToLong(spot -> 
            spot.getStatus() == ParkingSpotStatus.OCCUPIED ? 1 : 0).sum();
        long availableCount = availableSpots.size();
        long activeParking = activeRecords.size();
        
        // 今日收入
        LocalDateTime startOfDay = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfDay = startOfDay.plusDays(1);
        Double todayRevenue = parkingRecordRepository.getTodayRevenue(startOfDay, endOfDay);
        if (todayRevenue == null) todayRevenue = 0.0;
        
        model.addAttribute("totalSpots", totalSpots);
        model.addAttribute("occupiedSpots", occupiedSpots);
        model.addAttribute("availableSpots", availableCount);
        model.addAttribute("activeParking", activeParking);
        model.addAttribute("todayRevenue", todayRevenue);
        model.addAttribute("spots", allSpots);
        model.addAttribute("activeRecords", activeRecords);
        
        return "parking/index";
    }
    
    /**
     * 停车位管理页面
     */
    @GetMapping("/spots")
    public String spots(Model model) {
        List<ParkingSpot> spots = parkingSpotRepository.findAll();
        model.addAttribute("spots", spots);
        return "parking/spots";
    }
    
    /**
     * 添加停车位页面
     */
    @GetMapping("/spots/add")
    public String addSpotForm(Model model) {
        model.addAttribute("spotTypes", ParkingSpotType.values());
        model.addAttribute("spotStatuses", ParkingSpotStatus.values());
        return "parking/add-spot";
    }
    
    /**
     * 处理添加停车位
     */
    @PostMapping("/spots/add")
    public String addSpot(@RequestParam String spotNumber,
                         @RequestParam ParkingSpotType spotType,
                         @RequestParam String location,
                         @RequestParam Double hourlyRate,
                         @RequestParam(required = false) String description,
                         RedirectAttributes redirectAttributes) {
        
        // 检查停车位编号是否已存在
        if (parkingSpotRepository.findBySpotNumber(spotNumber).isPresent()) {
            redirectAttributes.addFlashAttribute("error", "停车位编号已存在");
            return "redirect:/parking/spots/add";
        }
        
        ParkingSpot spot = new ParkingSpot(spotNumber, spotType, location, hourlyRate);
        if (description != null && !description.trim().isEmpty()) {
            spot.setDescription(description);
        }
        
        parkingSpotRepository.save(spot);
        redirectAttributes.addFlashAttribute("success", "停车位添加成功");
        return "redirect:/parking/spots";
    }
    
    /**
     * 编辑停车位页面
     */
    @GetMapping("/spots/edit/{id}")
    public String editSpotForm(@PathVariable Long id, Model model) {
        Optional<ParkingSpot> spot = parkingSpotRepository.findById(id);
        if (spot.isPresent()) {
            model.addAttribute("spot", spot.get());
            model.addAttribute("spotTypes", ParkingSpotType.values());
            model.addAttribute("spotStatuses", ParkingSpotStatus.values());
            return "parking/edit-spot";
        }
        return "redirect:/parking/spots";
    }
    
    /**
     * 处理编辑停车位
     */
    @PostMapping("/spots/edit/{id}")
    public String editSpot(@PathVariable Long id,
                          @RequestParam String spotNumber,
                          @RequestParam ParkingSpotType spotType,
                          @RequestParam ParkingSpotStatus status,
                          @RequestParam String location,
                          @RequestParam Double hourlyRate,
                          @RequestParam(required = false) String description,
                          @RequestParam(required = false) Boolean isReserved,
                          RedirectAttributes redirectAttributes) {
        
        Optional<ParkingSpot> spotOpt = parkingSpotRepository.findById(id);
        if (spotOpt.isPresent()) {
            ParkingSpot spot = spotOpt.get();
            
            // 检查停车位编号是否被其他停车位使用
            Optional<ParkingSpot> existingSpot = parkingSpotRepository.findBySpotNumber(spotNumber);
            if (existingSpot.isPresent() && !existingSpot.get().getId().equals(id)) {
                redirectAttributes.addFlashAttribute("error", "停车位编号已被其他停车位使用");
                return "redirect:/parking/spots/edit/" + id;
            }
            
            spot.setSpotNumber(spotNumber);
            spot.setSpotType(spotType);
            spot.setStatus(status);
            spot.setLocation(location);
            spot.setHourlyRate(hourlyRate);
            spot.setDescription(description);
            spot.setIsReserved(isReserved != null ? isReserved : false);
            
            parkingSpotRepository.save(spot);
            redirectAttributes.addFlashAttribute("success", "停车位更新成功");
        }
        
        return "redirect:/parking/spots";
    }
    
    /**
     * 停车记录页面
     */
    @GetMapping("/records")
    public String records(Model model) {
        List<ParkingRecord> records = parkingRecordRepository.findAll();
        model.addAttribute("records", records);
        return "parking/records";
    }
    
    /**
     * 车辆入场页面
     */
    @GetMapping("/checkin")
    public String checkinForm(Model model) {
        List<Customer> customers = customerRepository.findAll();
        List<ParkingSpot> availableSpots = parkingSpotRepository.findAvailableSpots();
        
        model.addAttribute("customers", customers);
        model.addAttribute("availableSpots", availableSpots);
        return "parking/checkin";
    }
    
    /**
     * 处理车辆入场
     */
    @PostMapping("/checkin")
    public String checkin(@RequestParam Long customerId,
                         @RequestParam Long spotId,
                         @RequestParam String licensePlate,
                         @RequestParam(required = false) String vehicleType,
                         @RequestParam(required = false) String vehicleColor,
                         @RequestParam(required = false) String notes,
                         RedirectAttributes redirectAttributes) {
        
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        Optional<ParkingSpot> spotOpt = parkingSpotRepository.findById(spotId);
        
        if (customerOpt.isPresent() && spotOpt.isPresent()) {
            Customer customer = customerOpt.get();
            ParkingSpot spot = spotOpt.get();
            
            // 检查停车位是否可用
            if (spot.getStatus() != ParkingSpotStatus.AVAILABLE) {
                redirectAttributes.addFlashAttribute("error", "所选停车位不可用");
                return "redirect:/parking/checkin";
            }
            
            // 检查客户是否已有进行中的停车记录
            Optional<ParkingRecord> activeRecord = parkingRecordRepository.findActiveRecordByCustomer(customer);
            if (activeRecord.isPresent()) {
                redirectAttributes.addFlashAttribute("error", "该客户已有进行中的停车记录");
                return "redirect:/parking/checkin";
            }
            
            // 创建停车记录
            ParkingRecord record = new ParkingRecord(customer, spot, licensePlate);
            if (vehicleType != null && !vehicleType.trim().isEmpty()) {
                record.setVehicleType(vehicleType);
            }
            if (vehicleColor != null && !vehicleColor.trim().isEmpty()) {
                record.setVehicleColor(vehicleColor);
            }
            if (notes != null && !notes.trim().isEmpty()) {
                record.setNotes(notes);
            }
            
            parkingRecordRepository.save(record);
            
            // 更新停车位状态
            spot.setStatus(ParkingSpotStatus.OCCUPIED);
            parkingSpotRepository.save(spot);
            
            redirectAttributes.addFlashAttribute("success", "车辆入场成功");
        } else {
            redirectAttributes.addFlashAttribute("error", "客户或停车位不存在");
        }
        
        return "redirect:/parking";
    }
    
    /**
     * 车辆出场
     */
    @PostMapping("/checkout/{id}")
    public String checkout(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<ParkingRecord> recordOpt = parkingRecordRepository.findById(id);
        
        if (recordOpt.isPresent()) {
            ParkingRecord record = recordOpt.get();
            
            if (record.getStatus() != ParkingRecordStatus.ACTIVE) {
                redirectAttributes.addFlashAttribute("error", "该停车记录不是进行中状态");
                return "redirect:/parking/records";
            }
            
            // 计算停车时长和费用
            LocalDateTime exitTime = LocalDateTime.now();
            long durationMinutes = ChronoUnit.MINUTES.between(record.getEntryTime(), exitTime);
            BigDecimal totalFee = BigDecimal.valueOf(record.getParkingSpot().getHourlyRate())
                .multiply(BigDecimal.valueOf(durationMinutes))
                .divide(BigDecimal.valueOf(60), 2, RoundingMode.UP);
            
            // 更新停车记录
            record.setExitTime(exitTime);
            record.setDurationMinutes((int) durationMinutes);
            record.setTotalFee(totalFee);
            record.setStatus(ParkingRecordStatus.COMPLETED);
            parkingRecordRepository.save(record);
            
            // 更新停车位状态
            ParkingSpot spot = record.getParkingSpot();
            spot.setStatus(ParkingSpotStatus.AVAILABLE);
            parkingSpotRepository.save(spot);
            
            redirectAttributes.addFlashAttribute("success", 
                String.format("车辆出场成功，停车时长：%d分钟，费用：¥%.2f", durationMinutes, totalFee.doubleValue()));
        } else {
            redirectAttributes.addFlashAttribute("error", "停车记录不存在");
        }
        
        return "redirect:/parking/records";
    }
    
    /**
     * 删除停车位
     */
    @PostMapping("/spots/delete/{id}")
    public String deleteSpot(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<ParkingSpot> spotOpt = parkingSpotRepository.findById(id);
        
        if (spotOpt.isPresent()) {
            ParkingSpot spot = spotOpt.get();
            
            // 检查是否有进行中的停车记录
            Optional<ParkingRecord> activeRecord = parkingRecordRepository.findActiveRecordBySpot(id);
            if (activeRecord.isPresent()) {
                redirectAttributes.addFlashAttribute("error", "该停车位有进行中的停车记录，无法删除");
                return "redirect:/parking/spots";
            }
            
            parkingSpotRepository.delete(spot);
            redirectAttributes.addFlashAttribute("success", "停车位删除成功");
        }
        
        return "redirect:/parking/spots";
    }
}
