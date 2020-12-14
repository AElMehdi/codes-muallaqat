package com.aelmehdi.tripservicekata.user;

import java.util.ArrayList;
import java.util.List;
import com.aelmehdi.tripservicekata.trip.Trip;

public class User {

   private final List<Trip> trips = new ArrayList<>();
   private final List<User> friends = new ArrayList<>();

   public List<User> getFriends() {
      return friends;
   }

   public void addFriend(User user) {
      friends.add(user);
   }

   public void addTrip(Trip trip) {
      trips.add(trip);
   }

   public List<Trip> trips() {
      return trips;
   }

}
