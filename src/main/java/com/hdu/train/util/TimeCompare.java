package com.hdu.train.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class TimeCompare {

//    public static void main(String[] args) {
//        System.out.println("6:48".compareTo("4:19"));
//    }
    public boolean Compare(String time2,String time3){
        if (time2.length()<time3.length()){
            return true;
        } else return time2.length() == time3.length() && time2.compareTo(time3) < 0;
    }
}

