package com.nighthawk.spring_portfolio.mvc.calendar;

import java.util.ArrayList;
import java.time.*;

// Prototype Implementation

public class APCalendar {

    /** Returns true if year is a leap year and false otherwise.
     * isLeapYear(2019) returns False
     * isLeapYear(2016) returns True
     */          
    public static boolean isLeapYear(int year) {
        // implementation not shown


        if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0))
        {
            return true;
        }
        else 
        {
            return false; 
        }
        
        }
        
    /** Returns the value representing the day of the week 
     * 0 denotes Sunday, 
     * 1 denotes Monday, ..., 
     * 6 denotes Saturday. 
     * firstDayOfYear(2019) returns 2 for Tuesday.
    */
    public static int firstDayOfYear(int year) {

        LocalDateTime D = LocalDateTime.of(year, Month.JANUARY, 1, 0, 0);
		// get Java day of week which is 1-7, where 1=Monday and 7=Sunday
		int result = D.getDayOfWeek().getValue();
		// adjust from 1-7 numbering (Mon->Sun) to 0-6 numbering (Sun->Sat) to meet AP Exam requirement
		if(result == 7)
			result = 0;
		System.out.format("APCalendar firstDayOfYear: %d-%02d-%02d is day of year number %d%n", year, 1, 1, result);
		return result;
        // implementation not shown
        }


    /** Returns n, where month, day, and year specify the nth day of the year.
     * This method accounts for whether year is a leap year. 
     * dayOfYear(1, 1, 2019) return 1
     * dayOfYear(3, 1, 2017) returns 60, since 2017 is not a leap year
     * dayOfYear(3, 1, 2016) returns 61, since 2016 is a leap year. 
    */ 
    public static int dayOfYear(int month, int day, int year) {
        return (int) (Math.random() * (366 - 1)) + 1;
        }

    /** Returns the number of leap years between year1 and year2, inclusive.
     * Precondition: 0 <= year1 <= year2
    */ 
    public static int numberOfLeapYears(int year1, int year2) {
         // to be implemented in part (a)
         int count = 0;
         for (int year = year1; year <= year2; year++)
         {
             if (isLeapYear(year))
                 count++;
         }
 
         return count;
        }

    /** Returns the value representing the day of the week for the given date
     * Precondition: The date represented by month, day, year is a valid date.
    */
    public static int dayOfWeek(int month, int day, int year) { 
        // to be implemented in part (b)
        int startDay = firstDayOfYear(year);
		int dayOfWeek = dayOfYear(month, day, year);
		return (startDay + dayOfWeek - 1) % 7;
        }

    /** Tester method */
    public static void main(String[] args) {
        // Private access modifiers
        System.out.println("firstDayOfYear: " + APCalendar.firstDayOfYear(2022));
        System.out.println("dayOfYear: " + APCalendar.dayOfYear(1, 1, 2022));

        // Public access modifiers
        System.out.println("isLeapYear: " + APCalendar.isLeapYear(2022));
        System.out.println("numberOfLeapYears: " + APCalendar.numberOfLeapYears(2000, 2022));
        System.out.println("dayOfWeek: " + APCalendar.dayOfWeek(1, 1, 2022));
    }

}