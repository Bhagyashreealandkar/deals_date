//package com.deals.date.controller;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
// 
//import com.deals.date.repository.EventJpaRepository;
//@CrossOrigin(origins = "http://localhost:3000")
//
//@Controller
//public class CalendarController {
//
//    @Autowired
//    private EventJpaRepository eventRepository; 
//
//    @RequestMapping(value="/", method=RequestMethod.GET) 
//    public ModelAndView index() {
//        return new ModelAndView("index");
//    }
//
//    @RequestMapping(value="/staticcalendar", method=RequestMethod.GET) 
//    public ModelAndView staticcalendar() {
//        return new ModelAndView("staticcalendar");
//    }
//
//    @RequestMapping(value="/calendar", method=RequestMethod.GET) 
//    public ModelAndView calendar() {
//        return new ModelAndView("calendar");
//    }
//
//    @RequestMapping(value="/jsoncalendar", method=RequestMethod.GET) 
//    public ModelAndView jsoncalendar() {
//        return new ModelAndView("jsoncalendar");
//    }
//
//    @RequestMapping(value="/eventlist", method=RequestMethod.GET) 
//    public String events(HttpServletRequest request, Model model) {
//        model.addAttribute("events", eventRepository.findAll());
//        return "events";
//    }
//}