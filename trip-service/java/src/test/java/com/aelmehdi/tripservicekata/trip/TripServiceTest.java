package com.aelmehdi.tripservicekata.trip;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import com.aelmehdi.tripservicekata.user.User;

public class TripServiceTest {

   private User myUser = new User();

   @Test
   void should_get_trip_by_user() {
      TripService tripService = createTripService();
      myUser.addFriend(myUser);
      myUser.addFriend(new User());

      List<Trip> tripsByUser = tripService.getTripsByUser(myUser);

      assertThat(tripsByUser).isNotEmpty();
   }

   private TripService createTripService() {
      return new TripService() {

         @Override
         protected User getLoggedUser() {
            return myUser;
         }

         @Override
         protected List<Trip> findTripsByUser(User user) {
            return asList(new Trip(), new Trip());
         }
      };
   }

}
