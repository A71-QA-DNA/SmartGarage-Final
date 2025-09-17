package com.telerikacademy.web.smartgarageti.controllers.mvc;

import com.telerikacademy.web.smartgarageti.helpers.AuthenticationHelper;
import com.telerikacademy.web.smartgarageti.models.User;
import com.telerikacademy.web.smartgarageti.models.dto.ContactRequestDto;
//import com.telerikacademy.web.smartgarageti.services.EmailService;
import com.telerikacademy.web.smartgarageti.services.FileStorageService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
@Slf4j
public class HomeMvcController {
//    @Value("${spring.mail.username}")
//    private String defaultFromEmail;

    private final AuthenticationHelper authenticationHelper;
    private FileStorageService fileStorageService;

    @Autowired
    public HomeMvcController(AuthenticationHelper authenticationHelper, /*EmailService emailService,*/ FileStorageService fileStorageService) {
        this.authenticationHelper = authenticationHelper;
        this.fileStorageService = fileStorageService;
    }

    @ModelAttribute("isAuthenticated")
    public boolean populateIsAuthenticated(HttpSession session) {
        return session.getAttribute("currentUser") != null;
    }

    @ModelAttribute("isEmployee")
    public boolean populateIsEmployee(HttpSession session) {
        if (session.getAttribute("currentUser") != null) {
            User user = authenticationHelper.tryGetUserFromSession(session);
            return user.getRole().getName().equals("Employee");
        }
        return false;
    }

    @ModelAttribute("username")
    public String populateUsername(HttpSession session) {
        if (session.getAttribute("currentUser") != null) {
            User user = authenticationHelper.tryGetUserFromSession(session);
            return user.getUsername();
        }
        return null;
    }

    @ModelAttribute("loggedInUser")
    public User populateUser(HttpSession session) {
        if (session.getAttribute("currentUser") != null) {
            return authenticationHelper.tryGetUserFromSession(session);
        }
        return null;
    }

    @GetMapping
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/about")
    public String showAboutPage() {
        return "about";
    }

    @GetMapping("/gallery")
    public String showGalleryPage() {
        return "galleries";
    }

    @GetMapping("/appointment")
    public String showAppointmentPage() {
        return "appointment";
    }

    @GetMapping("/contact")
    public String showContactPage(Model model) {
        model.addAttribute("contactRequest", new ContactRequestDto());
        return "contact_2";
    }

    @GetMapping("/contact/success")
    public String showSuccessPage() {
        return "success";
    }

    //    @PostMapping("/contact")
//    public String handleContactForm(
//            @Valid @ModelAttribute("contactRequest") ContactRequestDto contactRequestDto,
//            BindingResult bindingResult,
//            @RequestParam("pdfFile") MultipartFile pdfFile,
//            Model model) {
//
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("errorMessage", "There are errors in the form. Please correct them.");
//            System.out.println("Validation errors: " + bindingResult.getAllErrors());
//            return "contact_2";
//        }
//
//        String subject = "New Job Application Request";
//        String text = "Name: " + contactRequestDto.getName() + "<br>" +
//                "Email: " + contactRequestDto.getEmail() + "<br>" +
//                "Phone: " + contactRequestDto.getPhone() + "<br>" +
//                "Message: " + contactRequestDto.getMessage();
//
//        try {
//            if (pdfFile != null && !pdfFile.isEmpty()) {
//                emailService.sendEmailWithAttachment(
//                        defaultFromEmail,
//                        subject,
//                        text,
//                        contactRequestDto.getEmail(),
//                        pdfFile);
//            } else {
//                emailService.sendEmail(defaultFromEmail, subject, text, contactRequestDto.getEmail());
//            }
//        } catch (Exception e) {
//            model.addAttribute("errorMessage", "Upload a CV!");
//            e.printStackTrace();
//            return "contact_2";
//        }
//        return "redirect:/contact/success";
//    }
    @PostMapping("/contact")
    public String handleContactForm(
            @Valid @ModelAttribute("contactRequest") ContactRequestDto contactRequestDto,
            BindingResult bindingResult,
            @RequestParam("pdfFile") MultipartFile pdfFile,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "There are errors in the form. Please correct them.");
            return "contact_2";
        }

        if (pdfFile == null || pdfFile.isEmpty()) {
            model.addAttribute("errorMessage", "Upload a CV!");
            return "contact_2";
        }

        try {
            fileStorageService.saveContactRequestToFile(
                    contactRequestDto.getName(),
                    contactRequestDto.getEmail(),
                    contactRequestDto.getPhone(),
                    contactRequestDto.getMessage(),
                    pdfFile
            );
        } catch (IOException e) {
            model.addAttribute("errorMessage", "Failed to save information.");
            e.printStackTrace();
            return "contact_2";
        }

        return "redirect:/contact/success";
    }

    @PostMapping("/appointment")
    public String handleAppointmentForm(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("vehicle-year") String vehicleYear,
            @RequestParam("vehicle-make") String vehicleMake,
            @RequestParam("vehicle-mileage") String vehicleMileage,
            @RequestParam("appointment-date") String appointmentDate,
            @RequestParam("time-frame") String timeFrame,
            @RequestParam(value = "services", required = false) String[] services,
            @RequestParam("message_appointment") String message,
            Model model) {

        try {
            fileStorageService.saveAppointmentRequest(
                    name, email, phone, vehicleYear, vehicleMake, vehicleMileage,
                    appointmentDate, timeFrame, services, message
            );

            model.addAttribute("successMessage", "Appointment request saved successfully!");
            return "redirect:/success-appointment";

        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Failed to save appointment request.");
            return "appointment";
        }
    }

    @GetMapping("/admin-panel")
    public String showAdminPanel() {
        return "AdminPanel";
    }

    @GetMapping("/success-appointment")
    public String showSuccessAppointmentPage() {
        return "SuccessAppointment";
    }
}


