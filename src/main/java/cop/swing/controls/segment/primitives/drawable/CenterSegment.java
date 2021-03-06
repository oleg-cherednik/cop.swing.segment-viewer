package cop.swing.controls.segment.primitives.drawable;

import cop.swing.controls.segment.primitives.RombusShape;

public final class CenterSegment extends DrawableSegment {
    public static SimpleSegment create(int orientation) {
        return new CenterSegment(orientation);
    }

    CenterSegment(int orientation) {
        super(RombusShape.create(), orientation);
    }

	/*
     * SimpleSegment
	 */

    @Override
    protected int getDefaultHeight() {
        return (getScale() < 2) ? 1 : (1 + (getScale() - 1) * 2);
    }
}
