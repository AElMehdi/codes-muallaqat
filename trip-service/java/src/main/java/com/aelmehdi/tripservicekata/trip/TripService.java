package com.aelmehdi.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;
import com.aelmehdi.tripservicekata.exception.UserNotLoggedInException;
import com.aelmehdi.tripservicekata.user.User;
import com.aelmehdi.tripservicekata.user.UserSession;

public class TripService {

   // TODO: To be refactored
   public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
      List<Trip> tripList = new ArrayList<>();
      User loggedUser = getLoggedUser();
      boolean isFriend = false;

      if (loggedUser != null) {
         for (User friend : user.getFriends()) {
            if (friend.equals(loggedUser)) {
               isFriend = true;
               break;
            }
         }

         if (isFriend) {
            tripList = findTripsByUser(user);
         }

         return tripList;
      } else {
         throw new UserNotLoggedInException();
      }
   }

   protected List<Trip> findTripsByUser(User user) {
      return TripDAO.findTripsByUser(user);
   }

   protected User getLoggedUser() {
      return UserSession.getInstance().getLoggedUser();
   }

}
