package com.cahue.persistence;

import com.cahue.api.ParkingSpot;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Date: 16.12.14
 *
 * @author francesco
 */
public interface Persistence {

    public final static String CartoDB = "CartoDB";
    public final static String MySQL = "MySQL";


    List<ParkingSpot> queryNearest(Double latitude, Double longitude, int nearest);

    List<ParkingSpot> queryArea(
            Double southwestLatitude,
            Double southwestLongitude,
            Double northeastLatitude,
            Double northeastLongitude);

    void put(ParkingSpot spot);

    int deleteBefore(Date date);
}