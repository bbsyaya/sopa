package de.sopa.model.game;

import static de.sopa.model.game.TileType.NONE;


/**
 * @author  David Schilling - davejs92@gmail.com
 */
public class Tile {

    private boolean top;
    private boolean bottom;
    private boolean left;
    private boolean right;
    private TileType tileType;
    private char shortcut;

    public Tile(boolean top, boolean bottom, boolean left, boolean right, TileType tileType, char shortcut) {

        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
        this.tileType = tileType;
        this.shortcut = shortcut;
    }


    public Tile(Tile tile) {

        top = tile.isTop();
        bottom = tile.isBottom();
        left = tile.isLeft();
        right = tile.isRight();
        tileType = tile.getTileType();
        shortcut = tile.getShortcut();
    }


    public Tile(TileType tileType, char shortcut) {

        this.top = false;
        this.bottom = false;
        this.left = false;
        this.right = false;
        this.tileType = tileType;
        this.shortcut = shortcut;
    }


    public Tile() {

        this(NONE, 'n');
    }

    public boolean isTop() {

        return top;
    }


    public boolean isBottom() {

        return bottom;
    }


    public boolean isLeft() {

        return left;
    }


    public boolean isRight() {

        return right;
    }


    public TileType getTileType() {

        return tileType;
    }


    public void setTop(boolean top) {

        this.top = top;
    }


    public void setBottom(boolean bottom) {

        this.bottom = bottom;
    }


    public void setLeft(boolean left) {

        this.left = left;
    }


    public void setRight(boolean right) {

        this.right = right;
    }


    public void setTileType(TileType tileType) {

        this.tileType = tileType;
    }


    public char getShortcut() {

        return shortcut;
    }


    public void setShortcut(char shortcut) {

        this.shortcut = shortcut;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Tile tile = (Tile) o;

        if (bottom != tile.bottom)
            return false;

        if (left != tile.left)
            return false;

        if (right != tile.right)
            return false;

        if (shortcut != tile.shortcut)
            return false;

        if (top != tile.top)
            return false;

        if (tileType != tile.tileType)
            return false;

        return true;
    }


    @Override
    public int hashCode() {

        int result = (top ? 1 : 0);
        result = 31 * result + (bottom ? 1 : 0);
        result = 31 * result + (left ? 1 : 0);
        result = 31 * result + (right ? 1 : 0);
        result = 31 * result + (tileType != null ? tileType.hashCode() : 0);
        result = 31 * result + (int) shortcut;

        return result;
    }


    @Override
    public String toString() {

        return "Tile{"
            + "tileType=" + tileType
            + ", shortcut=" + shortcut + '}';
    }
}
