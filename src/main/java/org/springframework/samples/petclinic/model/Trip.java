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
package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.core.style.ToStringCreator;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Simple JavaBean domain object representing an owner.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 */
@Entity
@Table(name = "trips")
public class Trip extends BaseEntity{
    @Column(name = "title")
    @NotEmpty
    private String title;

    @Column(name = "description")
    @NotEmpty
    private String description;

    @Column(name = "status")
    @NotEmpty
    private String status;
    

    @Column(name = "trip_type")
    @NotEmpty
    private String tripType;
    

    @Column(name = "place_id")
    @NotEmpty
    private String placeId;
    
    @Column(name = "planned_on")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private DateTime plannedOn;
    
    @Column(name = "ends_on")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private DateTime endsOn;


    public String getAddress() {
        return this.title;
    }

    public void setAddress(String title) {
        this.title = title;
    }

    public String getCity() {
        return this.description;
    }

    public void setCity(String description) {
        this.description = description;
    }

    public String getTelephone() {
        return this.status;
    }

    public void setTelephone(String status) {
        this.status = status;
    }
   
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTripType() {
		return tripType;
	}

	public void setTripType(String tripType) {
		this.tripType = tripType;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String place) {
		this.placeId = place;
	}

	public DateTime getPlannedOn() {
		return plannedOn;
	}

	public void setPlannedOn(DateTime plannedOn) {
		this.plannedOn = plannedOn;
	}

	public DateTime getEndsOn() {
		return endsOn;
	}

	public void setEndsOn(DateTime endsOn) {
		this.endsOn = endsOn;
	}

	@Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId())
                .append("new", this.isNew())
                .append("title", this.title)
                .append("description", this.description)
                .append("status", this.status)
                .append("placeId", this.placeId)
                .append("tripType", this.tripType)
                .append("plannedOn", this.plannedOn)
                .append("endOn", this.endsOn)
                .toString();
    }
}
