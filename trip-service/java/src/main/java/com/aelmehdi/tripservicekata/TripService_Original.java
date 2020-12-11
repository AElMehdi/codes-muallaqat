package com.aelmehdi.tripservicekata;

import java.util.ArrayList;
import java.util.List;
import com.aelmehdi.tripservicekata.exception.UserNotLoggedInException;
import com.aelmehdi.tripservicekata.trip.Trip;
import com.aelmehdi.tripservicekata.trip.TripDAO;
import com.aelmehdi.tripservicekata.user.User;
import com.aelmehdi.tripservicekata.user.UserSession;

public class TripService_Original {

   public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
      List<Trip> tripList = new ArrayList<Trip>();
      User loggedUser = UserSession.getInstance().getLoggedUser();
      boolean isFriend = false;
      if (loggedUser != null) {
         for (User friend : user.getFriends()) {
            if (friend.equals(loggedUser)) {
               isFriend = true;
               break;
            }
         }
         if (isFriend) {
            tripList = TripDAO.findTripsByUser(user);
         }
         return tripList;
      } else {
         throw new UserNotLoggedInException();
      }
   }

}
