package com.cahue.model;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class ParkingSpot {

    private Double longitude;
    private Double latitude;
    private Float accuracy;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date time = new Date();

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    public ParkingSpot() {
    }

    public ParkingSpot(double latitude, double longitude, float accuracy) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.accuracy = accuracy;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Float accuracy) {
        this.accuracy = accuracy;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getTime() {
		return time;
	}

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "id=" + id +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", accuracy=" + accuracy +
                ", time=" + time +
                '}';
    }
}
