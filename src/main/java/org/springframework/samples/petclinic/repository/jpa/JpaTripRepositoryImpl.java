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
package org.springframework.samples.petclinic.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;
import org.springframework.samples.petclinic.model.Trip;
import org.springframework.samples.petclinic.repository.TripRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA implementation of the {@link TripRepository} interface.
 *
 * @author Mike Keith
 * @author Rod Johnson
 * @author Sam Brannen
 * @author Michael Isvy
 * @since 22.4.2006
 */
@Repository
public class JpaTripRepositoryImpl implements TripRepository {

    @PersistenceContext
    private EntityManager em;


    /**
     * Important: in the current version of this method, we load Trips with all their Pets and Visits while 
     * we do not need Visits at all and we only need one property from the Pet objects (the 'name' property).
     * There are some ways to improve it such as:
     * - creating a Ligtweight class (example here: https://community.jboss.org/wiki/LightweightClass)
     * - Turning on lazy-loading and using {@link OpenSessionInViewFilter}
     */
    @SuppressWarnings("unchecked")
    public Collection<Trip> findByTitle(String title) {
        // using 'join fetch' because a single query should load both trips and pets
        // using 'left join fetch' because it might happen that an trip does not have pets yet
        Query query = this.em.createQuery("SELECT DISTINCT trip FROM Trip trip WHERE trip.title LIKE :title");
        query.setParameter("title", title + "%");
        return query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public Collection<Trip> findAll() {
        // using 'join fetch' because a single query should load both trips and pets
        // using 'left join fetch' because it might happen that an trip does not have pets yet
        Query query = this.em.createQuery("SELECT DISTINCT trip FROM Trip trip");
        return query.getResultList();
    }

    @Override
    public Trip findById(int id) {
        // using 'join fetch' because a single query should load both trips and pets
        // using 'left join fetch' because it might happen that an trip does not have pets yet
        Query query = this.em.createQuery("SELECT trip FROM Trip trip WHERE trip.id =:id");
        query.setParameter("id", id);
        return (Trip) query.getSingleResult();
    }

    @Override
    public void save(Trip trip) {
    	if (trip.getId() == null) {
    		this.em.persist(trip);     		
    	}
    	else {
    		this.em.merge(trip);    
    	}

    }

}
