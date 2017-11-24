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
import org.springframework.samples.petclinic.model.Trip;
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
@SessionAttributes(types = Trip.class)
public class TripController {

    private final ClinicService clinicService;


    @Autowired
    public TripController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping(value = "/trips/new", method = RequestMethod.GET)
    public String initCreationForm(Map<String, Object> model) {
        Trip trip = new Trip();
        model.put("trip", trip);
        return "trips/createOrUpdateTripForm";
    }

    @RequestMapping(value = "/trips/new", method = RequestMethod.POST)
    public String processCreationForm(@Valid Trip trip, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "trips/createOrUpdateTripForm";
        } else {
            this.clinicService.saveTrip(trip);
            status.setComplete();
            return "redirect:/trips/" + trip.getId();
        }
    }

    @RequestMapping(value = "/trips/find", method = RequestMethod.GET)
    public String initFindForm(Map<String, Object> model) {
        model.put("trip", new Trip());
        return "trips/findTrips";
    }

    @RequestMapping(value = "/trips", method = RequestMethod.GET)
    public String processFindForm(Trip trip, BindingResult result, Map<String, Object> model) {

        // allow parameterless GET request for /trips to return all records
        if (trip.getTitle() == null) {
            trip.setTitle(""); // empty string signifies broadest possible search
        }

        // find trips by last name
        Collection<Trip> results = this.clinicService.findTripByTitle(trip.getTitle());
        if (results.size() < 1) {
            // no trips found
            result.rejectValue("title", "notFound", "not found");
            return "trips/findTrips";
        }
        if (results.size() > 1) {
            // multiple trips found
            model.put("selections", results);
            return "trips/tripList";
        } else {
            // 1 trip found
            trip = results.iterator().next();
            return "redirect:/trips/" + trip.getId();
        }
    }

    @RequestMapping(value = "/trips/{tripId}/edit", method = RequestMethod.GET)
    public String initUpdateTripForm(@PathVariable("tripId") int tripId, Model model) {
        Trip trip = this.clinicService.findTripById(tripId);
        model.addAttribute(trip);
        return "trips/createOrUpdateTripForm";
    }

    @RequestMapping(value = "/trips/{tripId}/edit", method = RequestMethod.PUT)
    public String processUpdateTripForm(@Valid Trip trip, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "trips/createOrUpdateTripForm";
        } else {
            this.clinicService.saveTrip(trip);
            status.setComplete();
            return "redirect:/trips/{tripId}";
        }
    }

    /**
     * Custom handler for displaying an trip.
     *
     * @param tripId the ID of the trip to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping("/trips/{tripId}")
    public ModelAndView showTrip(@PathVariable("tripId") int tripId) {
        ModelAndView mav = new ModelAndView("trips/tripDetails");
        mav.addObject(this.clinicService.findTripById(tripId));
        return mav;
    }
    
    @RequestMapping("/trips/all")
    public String showAllTrips(Map<String, Object> model) {
    	Collection<Trip> results = this.clinicService.findTrips();
        // multiple trips found
        model.put("selections", results);
        return "trips/tripList";
    }

}
