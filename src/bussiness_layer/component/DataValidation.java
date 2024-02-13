
package bussiness_layer.component;

import application.utilities.DataInput;
import bussiness_Layer.entity.HotelInformation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DataValidation {
    
    public String  inputID(ArrayList<HotelInformation> arr){
       String id = "";
       do{
            id = DataInput.enterId();
           try {
               HotelInformation tmp = searchDataByID(id, arr);
               if(tmp != null){
                 System.out.println("Duplicated Code.Try With Another One");
            } else if (id.trim().isEmpty()) {
                System.out.println("ID Can't Not Empty!");
            } else {
                return id.toUpperCase();
            }
           } catch (Exception e) {
               System.out.println("Please enter the correct format of the id !");
           }
           
       }while(true);
    }
       
    public String inputName(){
        String name = "";
        do{
        try {
            name = DataInput.inputString("Enter The Name Hotel: ",  "^[a-zA-Z\\s]+$");
            if(name.trim().isEmpty()){
                System.out.println("Name Can't Not Empty!");
            }else{
                return name;
            }
        } catch (Exception e) {
            System.out.println("Please enter the correct format of the name !");
        }
        }while(true);
    }
    public int inputRoom(){
        int room = 0;
        do{
            try{
            room = DataInput.inputInteger("Enter The Number Of Available Rooms: ", 1, 10000);
            if(room != 0){
                return room;
            }
            }catch(Exception e){
                System.out.println("Please Enter The Room_Available Again !");
            }
        }while(true);
    }
    
    public String inputAddress(){
        String address = "";
        do{
            try {
                address = DataInput.inputString("Enter The Address Hotel: ", "(.)+");
                if (address.trim().isEmpty()) {
                    System.out.println("Address Can't Not Empty!");
                }else{
                    return address;
                }
            } catch (Exception e) {
                System.out.println("Please Enter The Correct Format Of The Address !");
            }
        }while(true);
    }
    
    
    public String inputPhone(){
        String phone = "";
        do{
            try{
                phone = DataInput.inputString("Enter The Phone Of Hotel: ", "0\\d{9}");
                if(phone.matches("\\d+")){
                    return phone;
                }else{
                    System.out.println("Please Enter The Correct Format Of The Phone !");
                    continue;
                }
            }catch(Exception e){
                System.out.println("Please Enter The Correct Format Of The Phone !");
            }
        }while(true);
    }
    
    public int inputRating(){
        int rating = 0;
        do{
            try{
                rating = DataInput.inputInteger("Enter The Rating Of The Hotel(1-5): ", 1, 5);
                if(rating >= 1 || rating <= 5){
                    return rating;
                }else{
                    System.out.println("Please Enter The Correct Format Of The Rating(1-5)!");
                    continue;
                }
            }catch(Exception e){
                System.out.println("Please Enter The Correct Format Of The Rating !");
            }
        }while(true);
    }
    
     public HotelInformation searchDataByID(String id, ArrayList<HotelInformation> arr){
        for (HotelInformation x : arr) {
            if(x.getHotel_id().equals(id)){
                return x;
            }
        }
        return null;
    }
    public HotelInformation searchDataByName(String name, ArrayList<HotelInformation> arr ) {
        for (HotelInformation n : arr) {
            if(n.getHotel_Name().equals(name)){
                return n;
            }
        }
        return null;
    }
    
    public void sortHotel(ArrayList<HotelInformation> arr){
        Collections.sort(arr, new Comparator<HotelInformation>(){
            @Override
            public int compare(HotelInformation o1, HotelInformation o2) {
                return o1.getHotel_Name().compareTo(o2.getHotel_Name());
            }
        });
    }
    
    public void sortHotelById(ArrayList<HotelInformation> arr){
        Collections.sort(arr, new Comparator<HotelInformation>(){
            @Override
            public int compare(HotelInformation o1, HotelInformation o2) {
                return o2.getHotel_id().compareTo(o1.getHotel_id());
            }
        });
    }
    
    public int inputRoomNoFormat() {
    int room = 0;
    try {
        String input = DataInput.inputString("Enter The Number Of Available Rooms (Press Enter to skip): ", ".*");
        if (!input.isEmpty()) {
            room = Integer.parseInt(input);
            if (room < 1) {
                System.out.println("Please enter a valid room number (1 or greater).");
                return inputRoom();
            }
        }
    } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid number.");
        return inputRoom();
    }
    return room;
}
    
    public int inputRatingNoFormat() {
    int rating = 0;
    try {
        String input = DataInput.inputString("Enter The Rating Of The Hotel (Press Enter to skip): ", ".*");
        if (!input.isEmpty()) {
            rating = Integer.parseInt(input);
            if (rating < 1 || rating > 5) {
                System.out.println("Please enter a valid rating (1 - 5).");
                return inputRating();
            }
        }
    } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid number.");
        return inputRating(); 
    }
    return rating;
}
}
