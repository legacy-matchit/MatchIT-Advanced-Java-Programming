package mountain;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal {

    private Point a;
    private Point b;
    private Point c;

    public Mountain(Point a, Point b, Point c){
        super();
        this.a = a;
        this.b = b;
        this.c = c;
    }



    @Override
    public String getTitle() {
        return "Mountain fractal";
    }

    @Override
    public void draw(TurtleGraphics turtle) {
        drawMountain(turtle,order,a,b,c);
    }
    private void drawMountain(TurtleGraphics turtle,int order, Point a,Point b, Point c){
        if(order == 0){
            turtle.moveTo(a.getX(),a.getY());
            turtle.forwardTo(b.getX(),b.getY());
            turtle.forwardTo(c.getX(),c.getY());
            turtle.forwardTo(a.getX(),a.getY());
        }else{

            Point newA = getHarfPoint(a,b);
            Point newB = getHarfPoint(a,c);
            Point newC = getHarfPoint(b,c);

            drawMountain(turtle,order-1,a,newA,newB);
            drawMountain(turtle,order-1,newA,b,newC);
            drawMountain(turtle,order-1,newB,newC,c);
            drawMountain(turtle,order-1,newA,newB,newC);
        }

    }
    /*
     * Recursive method: Draws a recursive line of the triangle.
     */
    private Point getHarfPoint(Point a, Point b) {
        int x = a.getX() + (b.getX() - a.getX()) / 2;
        int y = a.getY() + (b.getY() - a.getY()) / 2;
        return new Point(x,y);
    }


}
