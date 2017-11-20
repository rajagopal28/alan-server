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

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


/**
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("VisitsViewTests-config.xml")
@ActiveProfiles("jpa")
public class UsersViewTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void getUsersXml() throws Exception {
        ResultActions actions = this.mockMvc.perform(get("/usersFeed.xml").accept(MediaType.APPLICATION_XML));
        actions.andDo(print()); // action is logged into the console
        actions.andExpect(status().isOk());
        actions.andExpect(content().contentType("application/xml"));
        actions.andExpect(xpath("/users/userList[id=1]/firstName").string(containsString("Rajagopal")));

    }
    @Test
    public void getUsersJson() throws Exception {
        ResultActions actions = this.mockMvc.perform(get("/usersFeed.json").accept(MediaType.APPLICATION_JSON));
        actions.andDo(print()); // action is logged into the console
        actions.andExpect(status().isOk());
        actions.andExpect(content().contentType("application/json"));
//        actions.andExpect(content().json("{'users':{'userList':[{'id':1,'firstName':'Rajagopal','lastName':'Muthuchidambaram','address':'B4, Phase B, Sri Harshita apartments','city':'Chennai','telephone':'9791800572','new':false},{'id':2,'firstName':'Creepy','lastName':'Crawly','address':'Some newyork address','city':'Newyork','telephone':'9654462165','new':false}]}}"));
    }
    
    @Test
    public void getUsersAtom() throws Exception {
        ResultActions actions = this.mockMvc.perform(get("/usersFeed.atom").accept(MediaType.APPLICATION_ATOM_XML));
        actions.andDo(print()); // action is logged into the console
        actions.andExpect(status().isOk());
//        actions.andExpect(content().contentType("application/atom+xml"));
//        actions.andExpect(content().json("{'users':{'userList':[{'id':1,'firstName':'Rajagopal','lastName':'Muthuchidambaram','address':'B4, Phase B, Sri Harshita apartments','city':'Chennai','telephone':'9791800572','new':false},{'id':2,'firstName':'Creepy','lastName':'Crawly','address':'Some newyork address','city':'Newyork','telephone':'9654462165','new':false}]}}"));
    }
}
