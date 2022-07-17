public class Box {
    private int label, value, nextIndex;

    public Box(int label) {
        this.label = label;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setNextIndex(int nextIndex) {
        this.nextIndex = nextIndex;
    }

    public int getLabel() {
        return label;
    }

    public int getValue() {
        return value;
    }

    public int getNextIndex() {
        return nextIndex;
    }
}
