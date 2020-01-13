package mountain;

import fractal.Fractal;
import fractal.TurtleGraphics;
import java.util.HashMap;


public class Mountain extends Fractal {

    private Point a;
    private Point b;
    private Point c;
    private double dev;
    HashMap<Side,Point> midPoints;

    public Mountain(Point a, Point b, Point c){
        super();
        this.a = a;
        this.b = b;
        this.c = c;
        dev = 10;
        midPoints = new HashMap<>();
    }


    @Override
    public String getTitle() {
        return "Mountain fractal";
    }

    @Override
    public void draw(TurtleGraphics turtle) {
        drawMountain(turtle,order,a,b,c,dev);
    }
    
    private void drawMountain(TurtleGraphics turtle,int order, Point a,Point b, Point c, double dev){
        if(order == 0){
            turtle.moveTo(a.getX(),a.getY());
            turtle.forwardTo(b.getX(),b.getY());
            turtle.forwardTo(c.getX(),c.getY());
            turtle.forwardTo(a.getX(),a.getY());
        }else{

            Point m1 = getHalfPoint(a,b,dev);
            Point m2 = getHalfPoint(a,c,dev);
            Point m3 = getHalfPoint(b,c,dev);


            drawMountain(turtle,order-1,a,m1,m2,dev/2);
            drawMountain(turtle,order-1,m1,b,m3,dev/2);
            drawMountain(turtle,order-1,m2,m3,c,dev/2);
            drawMountain(turtle,order-1,m1,m2,m3,dev/2);

        }

    }

    private Point getHalfPoint(Point a, Point b, double dev) {

        if(midPoints.containsKey(new Side(a,b))){
            return midPoints.get(new Side(a,b));
        }else{
            int x = a.getX() + (b.getX() - a.getX()) / 2;
            int y = a.getY() + (b.getY() - a.getY()) / 2;
            //y+=RandomUtilities.randFunc(dev);

            Point p = new Point(x,y);
            midPoints.put(new Side(a,b),p);
            return p;
        }


    }

    public class Side{

        private Point p1;
        private Point p2;

        Side(Point p1, Point p2){
            this.p1 = p1;
            this.p2 = p2;
        }

        @Override
        public boolean equals(Object o) {

            if (!(o instanceof Side)){
                return false;
            }

            Side side = (Side) o;

            return p1.getX() == side.p1.getX() && p1.getY() == side.p1.getY() &&
                    p2.getX() == side.p2.getX() && p2.getY() == side.p2.getY();
        }

        @Override
        public int hashCode() {
            return p1.hashCode() + p2.hashCode();
        }
    }


}
