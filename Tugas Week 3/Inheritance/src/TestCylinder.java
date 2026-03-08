public class TestCylinder {
  public static void main(String[] args) {
      
    Cylinder c1 = new Cylinder();
    System.out.println("Cylinder:"
      + " \nradius=" + c1.getRadius()
      + " \nheight=" + c1.getHeight()
      + " \nbase area=" + c1.getArea()
      + " \nvolume=" + c1.getVolume()
      + " \nColor=" + c1.getColor()
      + " \n" + c1.toString());     
              
    Cylinder c2 = new Cylinder(10.0);
    System.out.println("Cylinder:"
      + " \nradius=" + c2.getRadius()
      + " \nheight=" + c2.getHeight()
      + " \nbase area=" + c2.getArea()
      + " \nvolume=" + c2.getVolume()
      + " \nColor=" + c2.getColor()
      + " \n" + c2.toString());
    
    Cylinder c3 = new Cylinder(2.0, 10.0, "Magenta");
    System.out.println("Cylinder:"
      + " \nradius=" + c3.getRadius()
      + " \nheight=" + c3.getHeight()
      + " \nbase area=" + c3.getArea()
      + " \nvolume=" + c3.getVolume()
      + " \nColor=" + c3.getColor()
      + " \n" + c3.toString());
  
    Cylinder c4 = new Cylinder(2.0, 10.0,"Cyan");
    System.out.println("Cylinder:"
      + " \nradius=" + c4.getRadius()
      + " \nheight=" + c4.getHeight()
      + " \nbase area=" + c4.getArea()
      + " \nvolume=" + c4.getVolume()
      + " \nColor=" + c4.getColor()
      + " \n" + c4.toString());
  }
}