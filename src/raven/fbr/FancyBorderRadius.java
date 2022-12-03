package raven.fbr;

import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Raven
 */
public class FancyBorderRadius {

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Border getBorder() {
        return border;
    }

    public void setBorder(Border border) {
        this.border = border;
    }

    public FancyBorderRadius(double width, double height, Border border) {
        this.width = width;
        this.height = height;
        this.border = new Border();
        this.border = border;
    }

    public FancyBorderRadius(double width, double height, String border) {
        this(width, height, new Border(border));
    }

    public FancyBorderRadius() {
    }

    private double width;
    private double height;
    private Border border;

    public Shape getShape() {
        Area area = new Area(new Rectangle2D.Double(0, 0, width, height));
        area.intersect(new Area(create1()));
        area.intersect(new Area(create2()));
        area.intersect(new Area(create3()));
        area.intersect(new Area(create4()));
        return area;
    }

    private Shape create1() {
        double w = border.getTop().getRight() * this.width;
        double h = border.getRight().getRight() * this.height;
        Path2D path = new Path2D.Double();
        path.moveTo(width - w, 0);
        path.lineTo(0, 0);
        path.lineTo(0, height);
        path.lineTo(width, height);
        path.lineTo(width, h);
        Area area = new Area(path);
        area.add(new Area(new Ellipse2D.Double(width - w * 2, 0, w * 2, h * 2)));
        return area;
    }

    private Shape create2() {
        double w = border.getTop().getLeft() * this.width;
        double h = border.getLeft().getRight() * this.height;
        Path2D path = new Path2D.Double();
        path.moveTo(0, h);
        path.lineTo(0, height);
        path.lineTo(width, height);
        path.lineTo(width, 0);
        path.lineTo(w, 0);
        Area area = new Area(path);
        area.add(new Area(new Ellipse2D.Double(0, 0, w * 2, h * 2)));
        return area;
    }

    private Shape create3() {
        double w = border.getBottom().getLeft() * this.width;
        double h = border.getLeft().getLeft() * this.height;
        Path2D path = new Path2D.Double();
        path.moveTo(w, height);
        path.lineTo(width, height);
        path.lineTo(width, 0);
        path.lineTo(0, 0);
        path.lineTo(0, height - h);
        Area area = new Area(path);
        area.add(new Area(new Ellipse2D.Double(0, height - h * 2, w * 2, h * 2)));
        return area;
    }

    private Shape create4() {
        double w = border.getBottom().getRight() * this.width;
        double h = border.getRight().getLeft() * this.height;
        Path2D path = new Path2D.Double();
        path.moveTo(width, height - h);
        path.lineTo(width, 0);
        path.lineTo(0, 0);
        path.lineTo(0, height);
        path.lineTo(width - w, height);
        Area area = new Area(path);
        area.add(new Area(new Ellipse2D.Double(width - w * 2, height - h * 2, w * 2, h * 2)));
        return area;
    }

    public void setBorder(float tl, float tr, float br, float bl, float lr, float rr, float rl, float ll) {
        border.setBorder(tl, tr, br, bl, lr, rr, rl, ll);
    }

    public void setBorder(String text) {
        border.setBorder(text);
    }
}
