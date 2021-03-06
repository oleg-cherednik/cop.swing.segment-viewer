package cop.swing.controls.segment.groups.numeric;

import cop.swing.controls.segment.ISegmentConfig;
import cop.swing.controls.segment.SegmentContainer;
import cop.swing.controls.segment.SegmentIndicator;
import cop.swing.controls.segment.SignSegment;
import cop.swing.controls.segment.seven.DigitalNumericSevenSegment;
import cop.swing.controls.segment.tmp.SignPositionEnum;

import static cop.common.ext.CollectionExt.replaceAll;
import static cop.common.ext.NumericExt.isInRangeMinMax;
import static cop.common.ext.NumericExt.toCharArray;

/**
 * @author Oleg Cherednik
 * @since 31.10.2015
 */
public abstract class NumericSegmentContainer<T extends Number> extends SegmentContainer<T> {
    protected T minimum;
    protected T maximum;

    protected NumericSegmentContainer(/*Canvas canvas,*/ int orientation, int totalSegments) {
        this(/*canvas,*/ orientation, totalSegments, false);
    }

    protected NumericSegmentContainer(/*Canvas canvas,*/ int orientation, int totalSegments, boolean signSegment) {
        super(/*canvas,*/ orientation, totalSegments, null);
    }

    protected NumericSegmentContainer(/*Canvas canvas,*/ ISegmentConfig config) {
        super(/*canvas,*/ config.getOrientation(), config.getTotalSegments(), config);
    }

    private void fillSegments() {
        for (SegmentIndicator segment : segments) {
            if (!config.isLeadingZero() || (segment instanceof SignSegment))
                segment.setValue(null);
            else
                segment.setValue('0');
        }
    }

    private void setSignMarker(char[] arr) {
        boolean negative = arr[0] == '-';

        if (isZero(arr))
            segments[0].setValue(null);
        else if (!negative)
            segments[0].setValue((segments[0] instanceof SignSegment) ? '+' : null);
        else if (config.getSignPosition() == SignPositionEnum.OUTSIDE || config.isLeadingZero()) {
            segments[0].setValue('-');
            replaceAll(arr, '-', '\0');
        } else
            segments[0].setValue(null);
    }

    private static boolean isZero(char[] arr) {
        return arr[0] == '0' && arr.length == 1;
    }

	/*
     * AbstractSegmentIndicator
	 */

    @Override
    protected void createHorizontalOrientatedParts(boolean invert) {
        int i = 0;
        int totalSegments = config.getTotalSegments();
        SignPositionEnum signPosition = config.getSignPosition();

        if (signPosition != SignPositionEnum.NONE)
            totalSegments++;

        segments = new SegmentIndicator[totalSegments];

        if (signPosition == SignPositionEnum.OUTSIDE) {
            switch (config.getSignType()) {
                case PLUS_MINUS:
                case PLUS:
                case MINUS:
                default:
                    segments[i++] = new SignSegment(x, y, getScale());
                    break;
            }
        }

        for (; i < totalSegments; i++)
            segments[i] = new DigitalNumericSevenSegment(x, y, getScale());
    }

    @Override
    protected void createVerticalOrientatedParts(boolean invert) {
        segments = new DigitalNumericSevenSegment[totalSegments];

        for (int i = 0; i < totalSegments; i++)
            segments[i] = new DigitalNumericSevenSegment(x, y, getScale());
    }

    @Override
    protected void _setValue() {
        if (value == null)
            clear(null);
        else if (isInRangeMinMax(value, minimum, maximum)) {
            int j = segments.length - 1;
            char[] arr = toCharArray(value);

            fillSegments();
            setSignMarker(arr);

            for (int i = arr.length - 1; i >= 0; i--, j--)
                if (arr[i] != '\0')
                    segments[j].setValue(arr[i]);
        }
    }
}
