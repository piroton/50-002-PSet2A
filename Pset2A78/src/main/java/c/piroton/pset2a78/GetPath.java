package c.piroton.pset2a78;

import java.util.ArrayList;

public class GetPath {
    public static boolean getPath (int r, int c, ArrayList<Point> path,
                                   final int [][] grid) {
        if( r<0 || c<0 || grid[r][c] == 1) return false;
        Point current = new Point(r,c);
        path.add(0,current);
        if (getPath(r-1,c,path,grid) || getPath(r, c-1, path, grid) || (r==c && r==0))
            return true;
        else return false;
    }
}

class Point {
    int x;
    int y;

    Point (int x, int y) {
        this.x=x;
        this.y=y;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
