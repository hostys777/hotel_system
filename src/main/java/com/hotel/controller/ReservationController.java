package com.hotel.controller;

import com.hotel.model.Reservation;
import com.hotel.model.Customer;
import com.hotel.model.Room;
import com.hotel.model.ReservationStatus;
import com.hotel.repository.ReservationRepository;
import com.hotel.repository.CustomerRepository;
import com.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * 预订管理控制器
 */
@Controller
@RequestMapping("/reservations")
public class ReservationController {
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private RoomRepository roomRepository;
    
    @GetMapping
    public String listReservations(Model model) {
        List<Reservation> reservations = reservationRepository.findAll();
        model.addAttribute("reservations", reservations);
        return "reservations/list";
    }
    
    @GetMapping("/add")
    public String addReservationForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("rooms", roomRepository.findByStatus(com.hotel.model.RoomStatus.AVAILABLE));
        return "reservations/add";
    }
    
    @PostMapping("/add")
    public String addReservation(@RequestParam Long customerId,
                                @RequestParam String roomNumber,
                                @RequestParam String checkInDate,
                                @RequestParam String checkOutDate,
                                @RequestParam(required = false) String remarks) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        Room room = roomRepository.findById(roomNumber).orElse(null);
        
        if (customer != null && room != null) {
            Reservation reservation = new Reservation();
            reservation.setCustomer(customer);
            reservation.setRoom(room);
            
            // 手动转换日期
            reservation.setCheckInDate(LocalDate.parse(checkInDate));
            reservation.setCheckOutDate(LocalDate.parse(checkOutDate));
            reservation.setNotes(remarks);
            reservation.setStatus(ReservationStatus.CONFIRMED);
            
            // 计算总金额
            long days = ChronoUnit.DAYS.between(reservation.getCheckInDate(), reservation.getCheckOutDate());
            BigDecimal totalAmount = room.getPrice().multiply(BigDecimal.valueOf(days));
            reservation.setTotalAmount(totalAmount);
            
            reservationRepository.save(reservation);
            
            // 更新房间状态
            room.setStatus(com.hotel.model.RoomStatus.RESERVED);
            roomRepository.save(room);
        }
        
        return "redirect:/reservations";
    }
    
    @GetMapping("/checkin/{id}")
    public String checkIn(@PathVariable Long id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation != null) {
            reservation.setStatus(ReservationStatus.CHECKED_IN);
            reservationRepository.save(reservation);
            
            // 更新房间状态
            Room room = reservation.getRoom();
            room.setStatus(com.hotel.model.RoomStatus.OCCUPIED);
            roomRepository.save(room);
        }
        return "redirect:/reservations";
    }
    
    @GetMapping("/checkout/{id}")
    public String checkOut(@PathVariable Long id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation != null) {
            reservation.setStatus(ReservationStatus.CHECKED_OUT);
            reservationRepository.save(reservation);
            
            // 更新房间状态
            Room room = reservation.getRoom();
            room.setStatus(com.hotel.model.RoomStatus.AVAILABLE);
            roomRepository.save(room);
        }
        return "redirect:/reservations";
    }
    
    @GetMapping("/cancel/{id}")
    public String cancelReservation(@PathVariable Long id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation != null) {
            reservation.setStatus(ReservationStatus.CANCELLED);
            reservationRepository.save(reservation);
            
            // 更新房间状态
            Room room = reservation.getRoom();
            room.setStatus(com.hotel.model.RoomStatus.AVAILABLE);
            roomRepository.save(room);
        }
        return "redirect:/reservations";
    }
}
