package lab01.tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CircularListImpl implements CircularList {

    private final List<Integer> internalList;
    private static final int DEFAULT_VALUE = -1;
    private int currentPosition;


    public CircularListImpl() {
        this.internalList = new ArrayList<>();
        this.currentPosition = DEFAULT_VALUE;
    }

    @Override
    public void add(int element) {
        internalList.add(element);
    }

    @Override
    public int size() {
        return internalList.size();
    }

    @Override
    public boolean isEmpty() {
        return internalList.isEmpty();
    }

    @Override
    public Optional<Integer> next() {
        if(this.isEmpty()) {
            return Optional.empty();
        } else {
            incrementPosition();
            return Optional.of(internalList.get(this.currentPosition));
        }
    }

    @Override
    public Optional<Integer> previous() {
        if(this.isEmpty()) {
            return Optional.empty();
        } else {
            decrementPosition();
            return Optional.of(internalList.get(this.currentPosition));
        }
    }

    @Override
    public void reset() {
        this.currentPosition = DEFAULT_VALUE;
    }

    @Override
    public Optional<Integer> next(SelectStrategy strategy) {
        for(int index : IntStream.range(0, this.size())
                .boxed()
                .collect(Collectors.toList())) {
            Optional<Integer> element = this.next();
            if(element.isPresent() && strategy.apply(element.get()))
                return element;
        }
        return Optional.empty();
    }

    private void incrementPosition() {
        this.currentPosition++;
        checkPositionBoundaries();
    }

    private void decrementPosition() {
        this.currentPosition--;
        checkPositionBoundaries();
    }

    private void checkPositionBoundaries() {
        if(this.currentPosition < 0) {
            this.currentPosition = size() - 1;
        } else {
            this.currentPosition %= size();
        }
    }
}
