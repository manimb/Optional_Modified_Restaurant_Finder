import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;

    // This Code will be run before each of the unit test case
    @BeforeEach
    public void setupRestaurant(){
        // Opening and closing times overlap in two dates. The restuarant opens
        // at 27th september 10 PM and closes on 28 september 5 AM
        LocalDateTime openingTime = LocalDateTime.parse("2021-09-27T22:00:00.00");
        LocalDateTime closingTime = LocalDateTime.parse("2021-09-28T05:00:00.00");
        restaurant =new Restaurant("Amelie's cafe","Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    //REFACTOR ALL THE REPEATED LINES OF CODE


    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE

    // Test if the method isRestaurantOpen() returns true if the current time is between the opening and closing time.
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        Restaurant spyRestaurant = Mockito.spy(restaurant);
        //Mock getCurrentTime to return time between opening and closing time.
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(LocalDateTime.parse("2021-09-28T00:00:00.00"));
        // spyRestaurant.isRestaurantOpen() returns true in this case
        assertTrue(spyRestaurant.isRestaurantOpen());
        //WRITE UNIT TEST CASE HERE
    }

    // Test if the method isRestaurantOpen() returns false if the current time is outside the opening and closing time.
    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        Restaurant spyRestaurant = Mockito.spy(restaurant);
        //Mock getCurrentTime to return time outside opening and closing time.
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(LocalDateTime.parse("2021-09-28T09:30:00.00"));
        // spyRestaurant.isRestaurantOpen() returns false in this case
        assertFalse(spyRestaurant.isRestaurantOpen());
        //WRITE UNIT TEST CASE HERE

    }

    @Test
    public void cumulative_sum_of_menu_items_with_no_menu_items_should_give_0() {
        List<String> listOfMenuItems = new ArrayList<String>();
        assertEquals(0, restaurant.cumulativeSumOfMenuItems(listOfMenuItems));
    }

    @Test
    public void cumulative_sum_of_menu_items_with_2_menu_items_with_rates_119_and_269_should_give_388() {
        List<String> listOfMenuItems = Arrays.asList( "Sweet corn soup", "Vegetable lasagne");
        assertEquals(388, restaurant.cumulativeSumOfMenuItems(listOfMenuItems));
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        LocalDateTime openingTime = LocalDateTime.parse("10:30:00");
        LocalDateTime closingTime = LocalDateTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        LocalDateTime openingTime = LocalDateTime.parse("10:30:00");
        LocalDateTime closingTime = LocalDateTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        LocalDateTime openingTime = LocalDateTime.parse("10:30:00");
        LocalDateTime closingTime = LocalDateTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}