package com.hotel.controller;

import com.hotel.model.Room;
import com.hotel.model.RoomStatus;
import com.hotel.model.RoomType;
import com.hotel.model.User;
import com.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.util.List;

/**
 * 房间管理控制器
 */
@Controller
@RequestMapping("/rooms")
public class RoomController {
    
    @Autowired
    private RoomRepository roomRepository;
    
    @GetMapping
    public String listRooms(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        
        List<Room> rooms = roomRepository.findAll();
        model.addAttribute("rooms", rooms);
        model.addAttribute("user", user);
        return "rooms/list";
    }
    
    @GetMapping("/available")
    public String listAvailableRooms(Model model) {
        List<Room> rooms = roomRepository.findByStatus(RoomStatus.AVAILABLE);
        model.addAttribute("rooms", rooms);
        return "rooms/available";
    }
    
    @GetMapping("/add")
    public String addRoomForm(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("roomTypes", RoomType.values());
        model.addAttribute("roomStatuses", RoomStatus.values());
        return "rooms/add";
    }
    
    @PostMapping("/add")
    public String addRoom(@ModelAttribute Room room) {
        roomRepository.save(room);
        return "redirect:/rooms";
    }
    
    @GetMapping("/edit/{roomNumber}")
    public String editRoomForm(@PathVariable String roomNumber, Model model) {
        Room room = roomRepository.findById(roomNumber).orElse(null);
        if (room == null) {
            return "redirect:/rooms";
        }
        model.addAttribute("room", room);
        model.addAttribute("roomTypes", RoomType.values());
        model.addAttribute("roomStatuses", RoomStatus.values());
        return "rooms/edit";
    }
    
    @PostMapping("/edit")
    public String editRoom(@ModelAttribute Room room) {
        roomRepository.save(room);
        return "redirect:/rooms";
    }
    
    @GetMapping("/delete/{roomNumber}")
    public String deleteRoom(@PathVariable String roomNumber) {
        roomRepository.deleteById(roomNumber);
        return "redirect:/rooms";
    }
}
