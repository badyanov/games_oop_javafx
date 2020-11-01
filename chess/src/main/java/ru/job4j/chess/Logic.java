package ru.job4j.chess;

import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;
import java.util.Arrays;

public final class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        figures[index++] = figure;
    }

    public void move(Cell source, Cell dest)
            throws FigureNotFoundException, ImpossibleMoveException, OccupiedCellException {
        int index = findBy(source);
        Cell[] steps = figures[index].way(dest);
        free(steps);
        figures[index] = figures[index].copy(dest);
    }

    private void free(Cell[] steps) throws OccupiedCellException {
        for (Cell cell : steps) {
            if (indexOfFigure(cell) != -1) {
                throw new OccupiedCellException("This move is impossible, other figure is on your way");
            }
        }
    }

    public void clean() {
        Arrays.fill(figures, null);
        index = 0;
    }

    /**
     * 2. Каркас шахматной доски [#285792]
     * Алгоритм поиска индекса вырезан из метода findBy()
     * но данный метод не бросает исключение, а возвращает -1,
     * если в данной клетке нет фигуры
     *
     * @param cell ссылка на проверяемую ячейку
     * @return индекс фигуры в массиве figures, -1 если клетка пустая
     */
    private int indexOfFigure(Cell cell) {
        int result = -1;
        for (int index = 0; index != figures.length; index++) {
            Figure figure = figures[index];
            if (figure != null && figure.position().equals(cell)) {
                result = index;
                break;
            }
        }
        return result;
    }

    private int findBy(Cell cell) throws FigureNotFoundException {
        int result = indexOfFigure(cell);
        if (result == -1) {
            throw new FigureNotFoundException();
        }
        return result;
    }
}
