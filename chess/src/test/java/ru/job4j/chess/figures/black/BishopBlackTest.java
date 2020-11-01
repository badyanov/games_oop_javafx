package ru.job4j.chess.figures.black;

import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * 2. Каркас шахматной доски [#285792]
 */
public class BishopBlackTest {

    @Test
    public void testPosition() {
        Figure testedFigure = new BishopBlack(Cell.C1);
        assertThat(testedFigure.position(), is(Cell.C1));
    }

    @Test
    public void testCopy() {
        Figure initialFigure = new BishopBlack(Cell.C1);
        Figure copiedFigure = initialFigure.copy(Cell.A3);
        assertThat(copiedFigure.position(), is(Cell.A3));
    }

    @Test
    public void testWay1() throws ImpossibleMoveException {
        Figure initialFigure = new BishopBlack(Cell.C1);
        Cell[] way = initialFigure.way(Cell.G5);
        Cell[] expected = new Cell[] {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        assertThat(way, is(expected));
    }

    @Test
    public void testWay2() throws ImpossibleMoveException {
        Figure initialFigure = new BishopBlack(Cell.B7);
        Cell[] way = initialFigure.way(Cell.D5);
        Cell[] expected = new Cell[] {Cell.C6, Cell.D5};
        assertThat(way, is(expected));
    }

    @Test
    public void testWay3() throws ImpossibleMoveException {
        Figure initialFigure = new BishopBlack(Cell.H8);
        Cell[] way = initialFigure.way(Cell.G7);
        Cell[] expected = new Cell[] {Cell.G7};
        assertThat(way, is(expected));
    }

    @Test
    public void testWay4() throws ImpossibleMoveException {
        Figure initialFigure = new BishopBlack(Cell.H1);
        Cell[] way = initialFigure.way(Cell.A8);
        Cell[] expected = new Cell[] {Cell.G2, Cell.F3, Cell.E4, Cell.D5, Cell.C6, Cell.B7, Cell.A8};
        assertThat(way, is(expected));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void testWayNonDiagonal1() throws ImpossibleMoveException {
        Figure initialFigure = new BishopBlack(Cell.C1);
        Cell[] way = initialFigure.way(Cell.G6);
    }

    @Test(expected = ImpossibleMoveException.class)
    public void testWayNonDiagonal2() throws ImpossibleMoveException {
        Figure initialFigure = new BishopBlack(Cell.B7);
        Cell[] way = initialFigure.way(Cell.F5);
    }

    @Test(expected = ImpossibleMoveException.class)
    public void testWayNonDiagonal3() throws ImpossibleMoveException {
        Figure initialFigure = new BishopBlack(Cell.H8);
        Cell[] way = initialFigure.way(Cell.F7);
    }

    @Test(expected = ImpossibleMoveException.class)
    public void testWayNonDiagonal4() throws ImpossibleMoveException {
        Figure initialFigure = new BishopBlack(Cell.H1);
        Cell[] way = initialFigure.way(Cell.B8);
    }
}