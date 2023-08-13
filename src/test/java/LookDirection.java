public enum LookDirection {
    UP(2), DOWN(0), LEFT(1), RIGHT(3);

    public final int value;

    LookDirection(int value) {
        this.value = value;
    }
}
