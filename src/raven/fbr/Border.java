package raven.fbr;

/**
 *
 * @author Raven
 */
public class Border {

    public Point getTop() {
        return top;
    }

    public void setTop(Point top) {
        this.top = top;
    }

    public Point getBottom() {
        return bottom;
    }

    public void setBottom(Point bottom) {
        this.bottom = bottom;
    }

    public Point getLeft() {
        return left;
    }

    public void setLeft(Point left) {
        this.left = left;
    }

    public Point getRight() {
        return right;
    }

    public void setRight(Point right) {
        this.right = right;
    }

    public Border(String border) {
        this();
        init(border);
    }

    public Border() {
        top = new Point();
        bottom = new Point();
        left = new Point();
        right = new Point();
    }

    private void init(String border) {
        setBorder(border);
    }

    private Point top;
    private Point bottom;
    private Point left;
    private Point right;

    @Override
    public String toString() {
        return convert(top.left) + " " + convert(top.right) + " " + convert(bottom.right) + " " + convert(bottom.left) + " / " + convert(left.right) + " " + convert(right.right) + " " + convert(right.left) + " " + convert(left.left);
    }

    public float[] toArray() {
        return new float[]{top.left, top.right, bottom.right, bottom.left, left.right, right.right, right.left, left.left};
    }

    private String convert(float f) {
        return (Math.round(f * 100 * 100) / 100) + "%";
    }

    public void setBorder(float tl, float tr, float br, float bl, float lr, float rr, float rl, float ll) {
        top.setLeft(tl);
        top.setRight(tr);
        bottom.setRight(br);
        bottom.setLeft(bl);
        left.setRight(lr);
        left.setLeft(ll);
        right.setRight(rr);
        right.setLeft(rl);
    }

    public void setBorder(String text) {
        text = text.replace("%", "");
        String arr[] = text.split("/");
        if (arr.length == 2) {
            String s1[] = arr[0].trim().split(" ");
            String s2[] = arr[1].trim().split(" ");
            setBorder(percent(s1[0]), percent(s1[1]), percent(s1[2]), percent(s1[3]), percent(s2[0]), percent(s2[1]), percent(s2[2]), percent(s2[3]));
        } else {
            String s1[] = arr[0].trim().split(" ");
            float v1 = percent(s1[0]);
            float v2 = percent(s1[1]);
            top.setLeft(v1);
            top.setRight(v2);
            right.setLeft(v1);
            right.setRight(v2);
            bottom.setRight(v1);
            bottom.setLeft(v2);
            left.setRight(v1);
            left.setLeft(v2);
        }
    }

    private float percent(String f) {
        return Float.parseFloat(f) / 100f;
    }

    protected class Point {

        public float getLeft() {
            return left;
        }

        public void setLeft(float left) {
            this.left = left;
        }

        public float getRight() {
            return right;
        }

        public void setRight(float right) {
            this.right = right;
        }

        public Point(float left, float right) {
            this.left = left;
            this.right = right;
        }

        public Point() {
        }

        private float left;
        private float right;
    }
}
