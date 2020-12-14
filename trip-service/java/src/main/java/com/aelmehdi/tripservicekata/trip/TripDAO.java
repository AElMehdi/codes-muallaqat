package com.aelmehdi.tripservicekata.trip;

import java.util.List;
import com.aelmehdi.tripservicekata.exception.CollaboratorCallException;
import com.aelmehdi.tripservicekata.user.User;

public class TripDAO {

   public static List<Trip> findTripsByUser(User user) {
      throw new CollaboratorCallException(
            "TripDAO should not be invoked on an unit test.");
   }

}