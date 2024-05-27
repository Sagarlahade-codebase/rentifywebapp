package com.sl.interview.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sl.interview.test.entity.UniqueEstate;
import com.sl.interview.test.entity.UserAccount;
import com.sl.interview.test.service.UniqueEstateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UniqueEstateController {

    @Autowired
    private UniqueEstateService uniqueEstateService;

    @GetMapping("/")
    public String landingpage() {
        return "login";
    }
    
    @GetMapping("/signin")
    public String displaySignInPage() {
        return "login";
    }
    
    @PostMapping("/validateusr")
    public String loginUser(@RequestParam("userEmail") String email, @RequestParam("userPassword") String password, Model model) {
        UserAccount user = uniqueEstateService.authenticateUser(email, password);
        if (user != null) {
            return redirectToDashboard(user.getUserRole(), user.getUserId());
        }
        model.addAttribute("error", "Invalid email or password");
        return "login";
    }
    
    private String redirectToDashboard(String userRole, Long userId) {
        if ("seller".equals(userRole)) {
            return "redirect:/vendor-dashboard?vendorId=" + userId;
        } else if ("buyer".equals(userRole)) {
            return "redirect:/client-dashboard";
        } else {
            return "redirect:/login"; // Redirect to login page in case of unknown role
        }
    }
    
    @PostMapping("/createuser")
    @ResponseBody
    public String createUser(@ModelAttribute UserAccount user, Model model) {
    	
    	uniqueEstateService.createUser(user);
    	model.addAttribute("user",user);
    	
		return "user craeted now login to portal";
    	
    }
    
    @GetMapping("/signup")
    public String displaySignUpPage() {
        return "register";
    }

    @PostMapping("/signup")
    public String registerUser(@ModelAttribute UserAccount user) {
        // Handle user registration logic
        return "redirect:/signin";
    }

    @GetMapping("/vendor-dashboard")
    public String showVendorDashboard(Model model, @RequestParam Long vendorId) {
        model.addAttribute("properties", uniqueEstateService.fetchVendorProperties(vendorId));
        model.addAttribute("vendorId",vendorId);
        return "vendordashboard";
    }

    @GetMapping("/client-dashboard")
    public String showClientDashboard(Model model) {
        model.addAttribute("properties", uniqueEstateService.fetchAllProperties());
        return "buyerdashboard";
    }

    @GetMapping("/property-details/{id}")
    public String showPropertyDetails(@PathVariable Long id, Model model) {
        model.addAttribute("property", uniqueEstateService.fetchPropertyById(id));
        return "property-details";
    }

    @PostMapping("/add-property")
    public String addProperty(@ModelAttribute UniqueEstate property) {
        uniqueEstateService.addNewProperty(property);
        return "redirect:/vendor-dashboard?vendorId=" + property.getVendorId();
    }

    @GetMapping("/edit-property/{id}/{vendorId}")
    public String editProperty(@PathVariable("id") Long id,@PathVariable("vendorId") Long vendorId, Model model) {
        UniqueEstate property = uniqueEstateService.fetchPropertyById(id);
        property.setVendorId(vendorId);
        model.addAttribute("estate", property);
        return "editproperty";
    }

    @PostMapping("/update-property/{vendorId}")
    public String updateProperty(@ModelAttribute UniqueEstate property, @PathVariable("vendorId") Long vendorId) {
        uniqueEstateService.modifyProperty(property);
        return "redirect:/vendor-dashboard?vendorId=" + vendorId;
    }



    @GetMapping("/filter")
    public String filterProperties(@RequestParam String location, @RequestParam int minBedrooms, Model model) {
        model.addAttribute("properties", uniqueEstateService.filterProperties(location, minBedrooms));
        return "client-dashboard";
        
    }
    
    @GetMapping("/getProperty/{vendorId}")
    public String getProperty(@PathVariable("vendorId") Long vendorId,Model model) {
    	model.addAttribute("vendorId",vendorId);
        return new String("addproperty");
    }
    
    
    @GetMapping("/delete-property/{id}/{vendorId}")
    public String deleteProperty(@PathVariable("id") Long id, @PathVariable("vendorId") Long vendorId) {
        uniqueEstateService.removeProperty(id);
        return "redirect:/vendor-dashboard?vendorId=" + vendorId;
    }
    
    
}
