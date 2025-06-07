package com.blogapp.springblogapp.entity;

import java.util.ArrayList;
import java.util.List;

public class Appointment {
    public static void main(String[] args) {
   doctorAppointment("Monday");

    }

    public static String doctorAppointment(String message) {
        List<String> days = new ArrayList<>(List.of("Monday", "Tuesday", "Wednesday"));
        System.out.println(days);
        for (String c : days) {
            if (c.contains(message) ) {
                System.out.println("Doctor is available");
                return "doctor is available";
            } else {
                System.out.println("Doctor is not available");

            }
        }
        return "doctor is not available";
    }

}