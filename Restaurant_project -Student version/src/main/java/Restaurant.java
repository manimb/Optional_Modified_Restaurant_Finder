import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalDateTime openingTime;
    public LocalDateTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalDateTime openingTime, LocalDateTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        // Checks if the user checking time falls in between the restaurant open and close times
        // and returns a boolean
        if (getCurrentTime().isAfter(this.openingTime) && getCurrentTime().isBefore(this.closingTime))
            return true;
        else
            return false;
        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
    }

    public LocalDateTime getCurrentTime(){ return  LocalDateTime.now(); }

    public List<Item> getMenu() {
        // Returns the list of items
        return menu;
        //DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    // Calculate the cumulative sum of the value of all the menu items selected by the user
    // Returns the order value, given the name of the items in a List format.
    public int cumulativeSumOfMenuItems(List<String> listOfMenuItems) {
        int cumulativeSum = 0;
        for(String menuItemName : listOfMenuItems) {
            cumulativeSum += findItemByName(menuItemName).getPrice();
        }
        return cumulativeSum;
    }

}