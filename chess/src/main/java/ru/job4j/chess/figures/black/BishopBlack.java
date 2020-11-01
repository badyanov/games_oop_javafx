package ru.job4j.chess.figures.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 * 2. Каркас шахматной доски [#285792]
 */
public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell ps) {
        position = ps;
    }

    @Override
    public Cell position() {
        return position;
    }

    @Override
    public Cell[] way(Cell dest) throws ImpossibleMoveException {
        if (!isDiagonal(position, dest)) {
            throw new ImpossibleMoveException (
                    String.format("Could not way by diagonal from %s to %s", position, dest)
            );
        }
        int stepsCounter = Math.abs(dest.getX() - position.getX());
        Cell[] steps = new Cell[stepsCounter];
        int deltaX = (dest.getX() > position.getX()) ? 1 : -1;
        int deltaY = (dest.getY() > position.getY()) ? 1 : -1;
        for (int i = 0; i < stepsCounter; i++) {
            int x = position.getX() + deltaX * (i + 1);
            int y = position.getY() + deltaY * (i + 1);
            steps[i] = Cell.findBy(x, y);
        }
        return steps;
    }

    /**
     * Слон должен ходить только по диагоналям
     * Проверка на то, что координаты Х и У меняются на одно и то же значение
     */
    public boolean isDiagonal(Cell source, Cell dest) {
        return Math.abs(dest.getX() - source.getX()) == Math.abs(dest.getY() - source.getY());
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
