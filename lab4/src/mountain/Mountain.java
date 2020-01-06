package mountain;

import fractal.Fractal;
import fractal.TurtleGraphics;

import java.util.ArrayList;

public class Mountain extends Fractal {

    private int length;

    private ArrayList<Point> vertexes;

    public Mountain(int length){
        super();
        this.length = length;
        vertexes = new ArrayList<>();
    }



    @Override
    public String getTitle() {
        return "Mountain fractal";
    }

    @Override
    public void draw(TurtleGraphics turtle) {
        turtle.moveTo(turtle.getWidth() / 2.0,
                turtle.getHeight() / 2.0 - length/2);
        drawMountain(turtle,order,length);
    }
    private void drawMountain(TurtleGraphics turtle,int order, double length){
        if(order == 0){
            int direction = -120;
            for (int i = 0; i < 3; i++){
                turtle.setDirection(direction);
                direction+=120;
                turtle.forward(length);
            }
        }else{
            drawMountain(turtle,order-1,length/2);
            drawMountain(turtle,order-1,length/2);
            drawMountain(turtle,order-1,length/2);
        }

    }
    /*
     * Recursive method: Draws a recursive line of the triangle.
     */
    private void fractalLine(TurtleGraphics turtle, int order, double length, int alpha) {
        if (order == 0) {
            turtle.setDirection(alpha);
            turtle.forward(length);
            System.out.println(alpha);
        } else {
            fractalLine(turtle,order-1, length/2, alpha);
            fractalLine(turtle,order-1, length/2, alpha);
            fractalLine(turtle,order-1, length/2, alpha);
        }

    }


}
