public enum CargoDimension {
    LARGE(200),
    SMALL(100);

    private int costIncrease;

    CargoDimension(int costIncrease) {
        this.costIncrease = costIncrease;
    }

    public int getCostIncrease() {
        return costIncrease;
    }
}
