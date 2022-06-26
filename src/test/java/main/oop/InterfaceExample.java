package main.oop;

public class InterfaceExample {

    public static void main(String[] args) {
        Drawable drawable = new Circle();
        drawable.draw();
        drawable = new Rectangle();
        drawable.draw();
    }
}

interface Drawable{
    void draw();
}
//Implementation: by second user
class Rectangle implements Drawable{
    public void draw(){System.out.println("drawing rectangle");}
}
class Circle implements Drawable{
    public void draw(){System.out.println("drawing circle");}
}