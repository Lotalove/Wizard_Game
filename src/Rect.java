import java.awt.*;

public class Rect {
    int x;
    int y;
    int prevX;
    int prevY;
    int w;
    int h;

    int width;
    int height;

    public Rect(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;
    }

    public boolean overlaps(Rect r) {
        return (x <= r.x + r.width) &&
                (y <= r.y + r.height) &&

                (r.x <= x + width) &&
                (r.y <= y + height);
    }
    public boolean contains(int mx, int my) {
        return (mx > x)        &&
                (mx < x + width) &&  // was w
                (my > y)        &&
                (my < y + height); // was h
    }
    public void pushLeft(Rect r)
    {
        double penetration = r.x + r.width - x;

        if(penetration < r.width/2)

            r.x -= penetration + 1;
    }

    public void pushRight(Rect r)
    {
        double penetration = x + width - r.x;

        if(penetration < r.width/2)

            r.x += penetration + 1;
    }

    public void pushUp(Rect r)
    {
        double penetration = r.y + r.height - y ;

        if(penetration < r.height/2)

            r.y -= penetration + 1;
    }

    public void pushDown(Rect r)
    {
        double penetration = y + height - r.y;

        if(penetration < r.height/2)

            r.y += penetration + 1;
    }


    public void pushes(Rect r)
    {
        pushDown(r);
        pushUp(r);
        pushRight(r);
        pushLeft(r);

    }
    public void draw(Graphics g) {
        g.drawRect(x-Camera.x, y-Camera.y, width, height);
    }

    public void moveBy(int dx, int dy) {
        x += dx;
        y += dy;
    }
}