package cop.swing.controls.segment;

import cop.swing.controls.segment.interfaces.ISegment;
import cop.swing.controls.segment.interfaces.Scaleable;
import cop.swing.controls.segment.primitives.BasicShape;

import java.awt.Point;
import java.awt.Rectangle;

/**
 * @author Oleg Cherednik
 * @since 31.10.2015
 */
public abstract class AbstractSegment implements ISegment, Scaleable {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int orientation;

    private int scale = 1;

    protected AbstractSegment(int orientation) {
        setOrientation(orientation);
        setScale(DEF_SCALE);
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        if (this.orientation == orientation)
            return;

        if (orientation == BasicShape.NONE || orientation == BasicShape.DEFAULT)
            this.orientation = getDefaultOrientation();
        else
            this.orientation = orientation;
    }

    protected void build() {
        width = getWidth();
        height = getHeight();
    }

    protected int getDefaultWidth() {
        return (scale <= 1) ? BASE_LENGTH : (BASE_LENGTH * (2 << (scale - 2)));
    }

    protected int getDefaultHeight() {
        return (scale <= 1) ? 2 : (3 + (scale - 2) * 2);
    }

    protected int getWidth() {
        return isHorizontalOrientation() ? getDefaultWidth() : getDefaultHeight();
    }

    protected int getHeight() {
        return isHorizontalOrientation() ? getDefaultHeight() : getDefaultWidth();
    }

    // ========== abstract ==========

    protected abstract int getDefaultOrientation();

    protected abstract boolean isHorizontalOrientation();

    // ========== Scalable ==========

    @Override
    public int getScale() {
        return scale;
    }

    @Override
    public void setScale(int scale) {
        if (this.scale == scale)
            return;

        this.scale = (scale <= DEF_SCALE) ? DEF_SCALE : scale;
        build();
    }

    // ========== ISegment =========

    @Override
    public void setPosition(int x, int y) {
        if (this.x == x && this.y == y)
            return;

        this.x = x;
        this.y = y;

        build();
    }

    @Override
    public void setBounds(int x, int y, int scale) {
        if (this.x == x && this.y == y && this.scale == scale)
            return;

        this.x = x;
        this.y = y;
        this.scale = scale;

        build();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public Point getSize() {
        return new Point(width, height);
    }

	/*
	 * Object
	 */

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();

        buf.append("height=").append(height);
        buf.append(", scale=").append(scale);
        buf.append(", width=").append(width);
        buf.append(", x=").append(x);
        buf.append(", y=").append(y);

        return buf.toString();
    }
}
