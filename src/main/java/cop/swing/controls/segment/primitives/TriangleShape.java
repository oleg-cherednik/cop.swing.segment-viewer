package cop.swing.controls.segment.primitives;

import cop.common.ext.BitExt;

/**
 * @author Oleg Cherednik
 * @since 30.10.2015
 */
public final class TriangleShape extends AbstractBasicShape {
    private static final TriangleShape OBJ = new TriangleShape();

    public static TriangleShape create() {
        return OBJ;
    }

    private TriangleShape() {
    }

	/*
     * BasicShape
	 */

    @Override
    public int[] getShape(int x, int y, int width, int height) {
        return getShape(x, y, width, height, DOWN);
    }

    @Override
    public int[] getShape(int x, int y, int width, int height, int orientation) {
        if (BitExt.isBitSet(orientation, RIGHT))
            return createRightOrientatedTriangle(x, y, width, height);

        if (BitExt.isBitSet(orientation, LEFT))
            return createLeftOrientatedTriangle(x, y, width, height);

        if (BitExt.isBitSet(orientation, UP))
            return createUpOrientatedTriangle(x, y, width, height);

        return createDownOrientatedTriangle(x, y, width, height);
    }

	/*
     * static
	 */

    private static int[] createRightOrientatedTriangle(int x, int y, int width, int height) {
        int size = width << 2;
        int[] arr = new int[size];

        for (int i = 0, j = 0; i < size; j++) {
            i = addPoint(arr, i, x + j, y + j);
            i = addPoint(arr, i, x + j, y + height - 1 - j);
        }

        return arr;
    }

    private static int[] createLeftOrientatedTriangle(int x, int y, int width, int height) {
        int size = width << 2;
        int[] arr = new int[size];

        for (int i = 0, j = 0; i < size; j++) {
            i = addPoint(arr, i, x - j, y + j);
            i = addPoint(arr, i, x - j, y + height - 1 - j);
        }

        return arr;
    }

    private static int[] createDownOrientatedTriangle(int x, int y, int width, int height) {
        int size = height << 2;
        int[] arr = new int[size];

        for (int i = 0, j = 0; i < size; j++) {
            i = addPoint(arr, i, x + j, y + j);
            i = addPoint(arr, i, x + width - 1 - j, y + j);
        }

        return arr;
    }

    private static int[] createUpOrientatedTriangle(int x, int y, int width, int height) {
        int size = height << 2;
        int[] arr = new int[size];

        for (int i = 0, j = 0; i < size; j++) {
            i = addPoint(arr, i, x + j, y - j);
            i = addPoint(arr, i, x + width - 1 - j, y - j);
        }

        return arr;
    }
}
