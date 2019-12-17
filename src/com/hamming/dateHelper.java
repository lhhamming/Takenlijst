package com.hamming;


public abstract class dateHelper {
    public static boolean dateCorrect(String date){
        int check = 0;
        String[] splitDate = date.split("-");
        //Date format: dd-mm-yyyy
        for (int i = 0; i < splitDate.length; i++) {
            if( i < 2){
                //its checking the month and day
                try {
                    Integer.parseInt(splitDate[i]);
                    if(splitDate[i].length() == 2){
                        //Continue the length is good.
                        check++;
                    }
                    else {
                        return false;
                    }
                }catch (Exception e){
                    //the filled in date isnt Integers
                    return false;
                }
            }
            else{
                try{
                    //Its checking the year
                    Integer.parseInt(splitDate[i]);
                    if(splitDate[i].length() == 4){
                        //The length of the year is correct
                        check++;
                    }
                    else {
                        return false;
                    }
                }catch (Exception e){
                    //The filled in date isnt Integers
                    return false;
                }
            }
        }

        if(check == 3){
            return true;
        }
        return false;
    }
}
