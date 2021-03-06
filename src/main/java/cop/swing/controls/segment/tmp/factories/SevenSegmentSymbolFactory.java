package cop.swing.controls.segment.tmp.factories;

public class SevenSegmentSymbolFactory extends BasicSegmentSymbolFactory {
    public static final int SEG_TOP_LEFT = 0x1;
    public static final int SEG_TOP_RIGHT = 0x2;
    public static final int SEG_BOTTOM_LEFT = 0x4;
    public static final int SEG_BOTTOM_RIGHT = 0x8;
    public static final int SEG_TOP = 0x10;
    public static final int SEG_BOTTOM = 0x20;
    public static final int SEG_CENTER = 0x40;

    private static SevenSegmentSymbolFactory obj;

    static {
        symbols.put('0', SEG_TOP | SEG_TOP_RIGHT | SEG_BOTTOM_RIGHT | SEG_BOTTOM | SEG_BOTTOM_LEFT
                | SEG_TOP_LEFT);
        symbols.put('1', SEG_TOP_RIGHT | SEG_BOTTOM_RIGHT);
        symbols.put('2', SEG_TOP | SEG_TOP_RIGHT | SEG_CENTER | SEG_BOTTOM_LEFT | SEG_BOTTOM);
        symbols.put('3', SEG_TOP | SEG_TOP_RIGHT | SEG_BOTTOM_RIGHT | SEG_BOTTOM | SEG_CENTER);
        symbols.put('4', SEG_TOP_LEFT | SEG_CENTER | SEG_TOP_RIGHT | SEG_BOTTOM_RIGHT);
        symbols.put('5', SEG_TOP | SEG_TOP_LEFT | SEG_CENTER | SEG_BOTTOM_RIGHT | SEG_BOTTOM);
        symbols.put('6', SEG_TOP | SEG_TOP_LEFT | SEG_BOTTOM_LEFT | SEG_BOTTOM | SEG_BOTTOM_RIGHT
                | SEG_CENTER);
        symbols.put('7', SEG_TOP | SEG_TOP_RIGHT | SEG_BOTTOM_RIGHT);
        symbols.put('8', SEG_TOP | SEG_TOP_RIGHT | SEG_BOTTOM_RIGHT | SEG_BOTTOM | SEG_BOTTOM_LEFT
                | SEG_TOP_LEFT | SEG_CENTER);
        symbols.put('9', SEG_CENTER | SEG_TOP_LEFT | SEG_TOP | SEG_TOP_RIGHT | SEG_BOTTOM_RIGHT);
        symbols.put('-', SEG_CENTER);
    }

    public static SevenSegmentSymbolFactory getInstance() {
        if (obj != null)
            return obj;

        synchronized (SevenSegmentSymbolFactory.class) {
            if (obj != null)
                return obj;

            return obj = new SevenSegmentSymbolFactory();
        }
    }

    protected SevenSegmentSymbolFactory() {
    }

	/*
     * BasicSegmentSymbolFactory
	 */

    @Override
    protected int getAll() {
        return super.getAll() | SEG_TOP | SEG_BOTTOM | SEG_CENTER;
    }
}
