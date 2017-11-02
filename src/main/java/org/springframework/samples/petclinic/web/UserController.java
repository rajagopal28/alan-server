/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
@SessionAttributes(types = User.class)
public class UserController {

    private final ClinicService clinicService;


    @Autowired
    public UserController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping(value = "/users/new", method = RequestMethod.GET)
    public String initCreationForm(Map<String, Object> model) {
        User user = new User();
        model.put("user", user);
        return "users/createOrUpdateUserForm";
    }

    @RequestMapping(value = "/users/new", method = RequestMethod.POST)
    public String processCreationForm(@Valid User user, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "users/createOrUpdateUserForm";
        } else {
            this.clinicService.saveUser(user);
            status.setComplete();
            return "redirect:/users/" + user.getId();
        }
    }

    @RequestMapping(value = "/users/find", method = RequestMethod.GET)
    public String initFindForm(Map<String, Object> model) {
        model.put("user", new User());
        return "users/findUsers";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String processFindForm(User user, BindingResult result, Map<String, Object> model) {

        // allow parameterless GET request for /users to return all records
        if (user.getLastName() == null) {
            user.setLastName(""); // empty string signifies broadest possible search
        }

        // find users by last name
        Collection<User> results = this.clinicService.findUserByLastName(user.getLastName());
        if (results.size() < 1) {
            // no users found
            result.rejectValue("lastName", "notFound", "not found");
            return "users/findUsers";
        }
        if (results.size() > 1) {
            // multiple users found
            model.put("selections", results);
            return "users/usersList";
        } else {
            // 1 user found
            user = results.iterator().next();
            return "redirect:/users/" + user.getId();
        }
    }

    @RequestMapping(value = "/users/{userId}/edit", method = RequestMethod.GET)
    public String initUpdateUserForm(@PathVariable("userId") int userId, Model model) {
        User user = this.clinicService.findUserById(userId);
        model.addAttribute(user);
        return "users/createOrUpdateUserForm";
    }

    @RequestMapping(value = "/users/{userId}/edit", method = RequestMethod.PUT)
    public String processUpdateUserForm(@Valid User user, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "users/createOrUpdateUserForm";
        } else {
            this.clinicService.saveUser(user);
            status.setComplete();
            return "redirect:/users/{userId}";
        }
    }

    /**
     * Custom handler for displaying an user.
     *
     * @param userId the ID of the user to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping("/users/{userId}")
    public ModelAndView showUser(@PathVariable("userId") int userId) {
        ModelAndView mav = new ModelAndView("users/userDetails");
        mav.addObject(this.clinicService.findUserById(userId));
        return mav;
    }
    
    @RequestMapping("/users/all")
    public String showAllUsers(Map<String, Object> model) {
    	Collection<User> results = this.clinicService.findUsers();
        // multiple users found
        model.put("selections", results);
        return "users/usersList";
    }

}
