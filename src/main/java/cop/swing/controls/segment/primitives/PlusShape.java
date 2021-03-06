package cop.swing.controls.segment.primitives;

import cop.common.ext.BitExt;

/**
 * @author Oleg Cherednik
 * @since 30.10.2015
 */
public final class PlusShape extends AbstractBasicShape {
    private static final PlusShape INSTANCE = new PlusShape();

    public static PlusShape create() {
        return INSTANCE;
    }

    private PlusShape() {
    }

    // ========== BasicShape ==========

    @Override
    public int[] getShape(int x, int y, int width, int height) {
        return getShape(x, y, width, height, HORIZONTAL);
    }

    @Override
    public int[] getShape(int x, int y, int width, int height, int orientation) {
        if (BitExt.isBitSet(orientation, HORIZONTAL))
            return getHorizontalShape(x, y, width, height);
        return getVerticalShape(x, y, width, height);
    }

    // ========== static ==========

    private static int[] getVerticalShape(int x, int y, int width, int height) {
        int[] arr = new int[24];
        int step = width / 3;

        int i = 0;

        int x1 = x + step;
        int x3 = x + width;
        int x2 = x3 - step;

        int y1 = y + step;
        int y3 = y + height;
        int y2 = y3 - step;

        i = addPoint(arr, i, x, y1);
        i = addPoint(arr, i, x1, y1);
        i = addPoint(arr, i, x1, y);
        i = addPoint(arr, i, x2, y);
        i = addPoint(arr, i, x2, y1);
        i = addPoint(arr, i, x3, y1);
        i = addPoint(arr, i, x3, y2);
        i = addPoint(arr, i, x2, y2);
        i = addPoint(arr, i, x2, y3);
        i = addPoint(arr, i, x1, y3);
        i = addPoint(arr, i, x1, y2);
        i = addPoint(arr, i, x, y2);

        return arr;
    }

    private static int[] getHorizontalShape(int x, int y, int width, int height) {
        int[] arr = new int[24];
        int step = width / 3;

        int i = 0;

        int x1 = x + step;
        int x3 = x + width;
        int x2 = x3 - step;

        int y1 = y + step;
        int y3 = y + height;
        int y2 = y3 - step;

        i = addPoint(arr, i, x, y1);
        i = addPoint(arr, i, x1, y1);
        i = addPoint(arr, i, x1, y);
        i = addPoint(arr, i, x2, y);
        i = addPoint(arr, i, x2, y1);
        i = addPoint(arr, i, x3, y1);
        i = addPoint(arr, i, x3, y2);
        i = addPoint(arr, i, x2, y2);
        i = addPoint(arr, i, x2, y3);
        i = addPoint(arr, i, x1, y3);
        i = addPoint(arr, i, x1, y2);
        i = addPoint(arr, i, x, y2);

        return arr;
    }
}
