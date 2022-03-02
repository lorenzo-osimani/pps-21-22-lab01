package lab01.tdd;

public class SelectStrategyFactoryImpl implements SelectStrategyFactory{

    @Override
    public SelectStrategy createEvenStrategy() {
        return element -> element % 2 == 1;
    }

    @Override
    public SelectStrategy createMultipleOfStrategy(int baseElement) {
        return element -> (element % baseElement) == 0;
    }

    @Override
    public SelectStrategy createEqualsStrategy(int baseElement) {
        return element -> element == baseElement;
    }
}
