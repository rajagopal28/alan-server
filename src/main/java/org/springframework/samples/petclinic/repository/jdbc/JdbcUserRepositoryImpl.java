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
package org.springframework.samples.petclinic.repository.jdbc;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.UserRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.stereotype.Repository;

/**
 * A simple JDBC-based implementation of the {@link UserRepository} interface.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Rob Harrop
 * @author Sam Brannen
 * @author Thomas Risberg
 * @author Mark Fisher
 */
@Repository
public class JdbcUserRepositoryImpl implements UserRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertUser;

    @Autowired
    public JdbcUserRepositoryImpl(DataSource dataSource, NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                                   VisitRepository visitRepository) {

        this.insertUser = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");

        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

    }


    /**
     * Loads {@link User Users} from the data store by last name, returning all users whose last name <i>starts</i> with
     * the given name; also loads the {@link Pet Pets} and {@link Visit Visits} for the corresponding users, if not
     * already loaded.
     */
    @Override
    public Collection<User> findByLastName(String lastName) throws DataAccessException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("lastName", lastName + "%");
        List<User> users = this.namedParameterJdbcTemplate.query(
                "SELECT id, first_name, last_name, address, city, telephone FROM users WHERE last_name like :lastName",
                params,
                ParameterizedBeanPropertyRowMapper.newInstance(User.class)
        );
        return users;
    }

    /**
     * Loads the {@link User} with the supplied <code>id</code>; also loads the {@link Pet Pets} and {@link Visit Visits}
     * for the corresponding user, if not already loaded.
     */
    @Override
    public User findById(int id) throws DataAccessException {
        User user;
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", id);
            user = this.namedParameterJdbcTemplate.queryForObject(
                    "SELECT id, first_name, last_name, address, city, telephone FROM users WHERE id= :id",
                    params,
                    ParameterizedBeanPropertyRowMapper.newInstance(User.class)
            );
        } catch (EmptyResultDataAccessException ex) {
            throw new ObjectRetrievalFailureException(User.class, id);
        }
        return user;
    }

    

    @Override
    public void save(User user) throws DataAccessException {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
        if (user.isNew()) {
            Number newKey = this.insertUser.executeAndReturnKey(parameterSource);
            user.setId(newKey.intValue());
        } else {
            this.namedParameterJdbcTemplate.update(
                    "UPDATE users SET first_name=:firstName, last_name=:lastName, address=:address, " +
                            "city=:city, telephone=:telephone WHERE id=:id",
                    parameterSource);
        }
    }

   
    public Collection<User> findAll() throws DataAccessException {
        Map<String, Object> params = new HashMap<String, Object>();
        List<User> users = this.namedParameterJdbcTemplate.query(
                "SELECT id, first_name, last_name, address, city, telephone FROM users",
                params,
                ParameterizedBeanPropertyRowMapper.newInstance(User.class)
        );
        return users;
    }

}
