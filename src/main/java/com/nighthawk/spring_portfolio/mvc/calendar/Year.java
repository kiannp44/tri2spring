package com.nighthawk.spring_portfolio.mvc.calendar;
//import com.nighthawk.spring_portfolio.mvc.calendar.APCalendar.firstDayOfYear;

/** Simple POJO 
 * Used to Interface with APCalendar
 * The toString method(s) prepares object for JSON serialization
 * Note... this is NOT an entity, just an abstraction
 */
class Year {
   private int year;
   private boolean isLeapYear;
   

   // zero argument constructor
   public Year() {} 

   /* year getter/setters */
   public int getYear() {
      return year;
   }
   public void setYear(int year) {
      this.year = year;
      this.setIsLeapYear(year);
      this.firstDayOfYear(year);
   }


   /* isLeapYear getter/setters */
   public boolean getIsLeapYear(int year) {
      return APCalendar.isLeapYear(year);
   }
   private void setIsLeapYear(int year) {  // this is private to avoid tampering
      this.isLeapYear = APCalendar.isLeapYear(year);
   }

   /* isLeapYearToString formatted to be mapped to JSON */
   public String isLeapYearToString(){
      return ( "{ \"year\": "  +this.year+  ", " + "\"isLeapYear\": "  +this.isLeapYear+ " }" );
   }	
    /* standard toString placeholder until class is extended */
    public String toString() { 
      return isLeapYearToString(); 
   }



   /* firstDayOfYear getter/setters */
   public int getfirstDayOfYear(int year) {
      return APCalendar.firstDayOfYear(year);
   }
  
   public String firstDayOfYearToString(){
      return ( "{ \"year\": "  +this.year+  ", " + "\"firstDayOfYear\": "  +this.firstDayOfYear(year)+ " }" );
   }	


   
//MAKE SURE U DELETE THESE AFTER YOU FIGURE OUT HOW TO IMPORT FROM AP CALENDER!

   static int dayofyear(int d, int m, int y){
      int t[] = { 0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4 };
      if (m < 3)
          y--;
      return (y + y / 4 - y / 100 + y / 400 + t[m - 1]+ d) % 7;
  }
   
      
  /** Returns the value representing the day of the week 
   * 0 denotes Sunday, 
   * 1 denotes Monday, ..., 
   * 6 denotes Saturday. 
   * firstDayOfYear(2019) returns 2 for Tuesday.
  */
  public int firstDayOfYear(int year) {
      // implementation not shown
      return dayofyear(1, 1, year);
     
      }
   public static void main(String[] args) {
      Year year = new Year();
      year.setYear(2022);
      System.out.println(year);
   }
}