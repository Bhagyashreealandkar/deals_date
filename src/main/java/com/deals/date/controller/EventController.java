//package com.deals.date.controller;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.Date;
//import java.util.List;
// 
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
// 
//import com.deals.date.exception.BadDateFormatException;
//import com.deals.date.model.Event;
//import com.deals.date.repository.EventJpaRepository;
//@CrossOrigin(origins = "http://localhost:3000")
//
//@RestController 
//@RequestMapping("/calendar")
//
//public class EventController {
//
//    @Autowired
//    private EventJpaRepository eventRepository;
//
//    @RequestMapping(value="/allevents", method=RequestMethod.GET)
//    public List<Event> allEvents() {
//        return eventRepository.findAll();
//    }
//
//    @RequestMapping(value="/event", method=RequestMethod.POST)
//    public Event addEvent(@RequestBody Event event) {
//        Event created = eventRepository.save(event);
//        return created; 
//    }
// 
//    @RequestMapping(value="/event", method=RequestMethod.PATCH)
//    public Event updateEvent(@RequestBody Event event) {
//        return eventRepository.save(event);
//    }
// 
//    @RequestMapping(value="/event", method=RequestMethod.DELETE)
//    public void removeEvent(@RequestBody Event event) {
//        eventRepository.delete(event);
//    }
//
//    @RequestMapping(value="/events", method=RequestMethod.GET)
//    public List<Event> getEventsInRange(@RequestParam(value = "start", required = true) String start, 
//                                        @RequestParam(value = "end", required = true) String end) {
//        Date startDate = null;
//        Date endDate = null;
//        SimpleDateFormat inputDateFormat=new SimpleDateFormat("yyyy-MM-dd");
//
//        try {
//            startDate = inputDateFormat.parse(start);
//        } catch (ParseException e) {
//            throw new BadDateFormatException("bad start date: " + start);
//        }
//
//        try {
//            endDate = inputDateFormat.parse(end);
//        } catch (ParseException e) {
//            throw new BadDateFormatException("bad end date: " + end);
//        }
//
//        LocalDateTime startDateTime = LocalDateTime.ofInstant(startDate.toInstant(),
//                ZoneId.systemDefault());
//
//        LocalDateTime endDateTime = LocalDateTime.ofInstant(endDate.toInstant(),
//                ZoneId.systemDefault());
//
//        return eventRepository.findByDateBetween(startDateTime, endDateTime); 
//    }
//}
