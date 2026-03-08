public class Salesperson implements Comparable<Salesperson>
{
    private String firstName, lastName;
    private int totalSales;
   
    //------------------------------------------------------
   // Constructor: Sets up the sales person object with
  // the given data.
 //------------------------------------------------------
    public Salesperson (String first, String last, int sales)
    { 
       firstName = first;
       lastName = last;
       totalSales = sales;
    }
    //-------------------------------------------
   // Returns the sales person as a string.
  //-------------------------------------------
    @Override
  public String toString()
  {
      return lastName + ", " + firstName + ": \t" + totalSales;
  }
  
  //-------------------------------------------
  // Returns true if the sales people have
  // the same name.
  //-------------------------------------------
    @Override
  public boolean equals (Object other)
  {
      if (!(other instanceof Salesperson))
          return false;
      
      Salesperson otherPerson = (Salesperson) other;
      
      return (lastName.equals(((Salesperson)other).getLastName()) &&
      firstName.equals(((Salesperson)other).getFirstName()));
  }
  //--------------------------------------------------
  // Order is based on total sales with the name
  // (last, then first) breaking a tie.
  //--------------------------------------------------
    @Override
  public int compareTo(Salesperson other)
  {
     Salesperson otherPerson = (Salesperson) other;
     int result;
     
     if(totalSales > otherPerson.getSales())
        result = 1;
     else if (totalSales < otherPerson.getSales())
        result = -1;
     else
     {
        result = lastName.compareTo(otherPerson.getLastName());
        if (result == 0)
            result = firstName.compareTo(otherPerson.getFirstName());
     }
     
     return result;
  }
  
  //-------------------------
  // First name accessor.
  //-------------------------
  public String getFirstName()
  {
     return firstName;
  } 
  //-------------------------
  // Last name accessor.
  //-------------------------
  public String getLastName()
  {
      return lastName;
  }
  //-------------------------
  // Total sales accessor.
  //-------------------------
  public int getSales()
  {
      return totalSales;
  }
}