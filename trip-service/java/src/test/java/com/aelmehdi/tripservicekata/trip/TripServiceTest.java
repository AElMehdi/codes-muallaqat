package com.aelmehdi.tripservicekata.trip;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;
import com.aelmehdi.tripservicekata.exception.UserNotLoggedInException;
import com.aelmehdi.tripservicekata.user.User;

public class TripServiceTest {

   private User aUser = new User();

   @Test
   void should_raise_exception_when_not_logged() {
      TripService tripService = createTripServiceNotLogged();
      aUser.addFriend(aUser);
      aUser.addFriend(new User());

      assertThatThrownBy(() -> tripService.getTripsByUser(aUser))
            .isInstanceOf(UserNotLoggedInException.class);
   }


   @Test
   void should_get_trip_by_user_when_logged_and_has_friend() {
      TripService tripService = createTripService();
      aUser.addFriend(aUser);
      aUser.addFriend(new User());

      List<Trip> tripsByUser = tripService.getTripsByUser(aUser);

      assertThat(tripsByUser).isNotEmpty();
   }

   @Test
   void should_get_trip_by_user_when_logged_and_has_do_not_have_friends() {
      TripService tripService = createTripService();
      User userWithoutHimAsFriend = this.aUser;
      userWithoutHimAsFriend.addFriend(new User());

      List<Trip> tripsByUser = tripService.getTripsByUser(userWithoutHimAsFriend);

      assertThat(tripsByUser).isEmpty();
   }


   private TripService createTripService() {
      return new TripService() {
         @Override
         protected User getLoggedUser() {
            return aUser;
         }

         @Override
         protected List<Trip> findTripsByUser(User user) {
            return asList(new Trip(), new Trip());
         }
      };
   }

   private TripService createTripServiceNotLogged() {
      return new TripService() {
         @Override
         protected User getLoggedUser() {
            return null;
         }

         @Override
         protected List<Trip> findTripsByUser(User user) {
            return asList(new Trip(), new Trip());
         }
      };
   }

}
