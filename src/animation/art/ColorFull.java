package animation.art;

import java.awt.Color;

/**
 * class of colorfull that returns a color according to a number.
 *
 * @author Vered Sayag
 * @version 26.03.2018
 */
public class ColorFull {

    /**
     * returns a color according to a number periodically.
     * set of pink orenge red and yellow.
     *
     * @param n a number.
     * @return a color.
     */
    public java.awt.Color getColor1(int n) {
        n = n % 4;
        if (n == 0) {
            return Color.RED;
        } else if (n == 1) {
            return Color.yellow;
        } else if (n == 2) {
            return Color.orange;
        } else {
            return Color.pink;
        }
    }

    /**
     * returns a color according to a number periodically.
     * set of 2 - gray white and red .
     *
     * @param n a number.
     * @return a color.
     */
    public java.awt.Color getColor2(int n) {
        n = n % 3;
        if (n == 0) {
            return Color.gray;
        } else if (n == 1) {
            return Color.white;
        } else {
            return Color.lightGray;
        }

    }

    /**
     * returns a color according to a number periodically.
     * set of pink green cyan and magenta.
     *
     * @param n a number.
     * @return a color.
     */
    public java.awt.Color getColor3(int n) {
        n = n % 4;
        if (n == 0) {
            return Color.green;
        } else if (n == 1) {
            return Color.pink;
        } else if (n == 2) {
            return Color.cyan;
        } else {
            return Color.magenta;
        }
    }
}
