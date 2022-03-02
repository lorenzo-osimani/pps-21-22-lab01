package lab01.tdd;

public interface SelectStrategyFactory {

    public SelectStrategy createEvenStrategy();

    public SelectStrategy createMultipleOfStrategy(final int baseElement);

    public SelectStrategy createEqualsStrategy(final int baseElement);
}
