package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class LoadMap {

    public int[][] getMap(String src) {
        int[][] map = null;

        int row = 0;
        int col = 0;

        BufferedReader br;
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(src)));
            do {
                line = br.readLine();
                if (row == 0) {
                    col = line.split(" ").length;
                }
                row++;
            } while (line != null);

            row = row - 1;

            map = new int[row][col];

            br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(src)));
            int j;
            for (int i = 0; i < row; i++) {
                line = br.readLine();
                j = 0;
                for (String s : line.split(" ")) {
                    map[i][j++] = Integer.parseInt(s);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    /**
     * * MIN PATH **
     */
    public static void next(int map[][], int[] direct, ArrayList<Position> path,
            ArrayList<Position> backPoint,
            Position p1, Position p2, ArrayList<Position> result) {

        if (equals(p1, p2)) {
            System.out.println(getList(path));
            for(Position p : path){
                result.add(p);
            }
            result.add(new Position(-1, -1));
            return;
        }

        int x = p1.x;
        int y = p1.y;
        int counter = 0;
        ArrayList<Position> ds = new ArrayList<Position>();
        for (int d : direct) {
            x = p1.x;
            y = p1.y;
            switch (d) {
                case 1:
                    x -= 1;
                    break;
                case 2:
                    x += 1;
                    break;
                case 3:
                    y -= 1;
                    break;
                case 4:
                    y += 1;
                    break;
            }

            // cac diem co the di chuyen
            if (x >= 0 && x <= map[0].length - 1 && y >= 0 && y <= map.length - 1) {
                if (map[y][x] == 0 && !contains(path, new Position(x, y))) {
                    counter++;
                    ds.add(new Position(x, y));
//                    if (equals(new Position(x, y), p2)) {
//                        System.out.println(getList(path));
//                    }
                }
            }
        }

        if (ds.size() == 0) {
            Position p = path.get(path.size() - 1);
//            System.out.println("PATH " + ++c);
//            System.out.println(getList(path) + "___" + path.size());

            if (backPoint.size() > 0) {
                Position bp = backPoint.get(backPoint.size() - 1);
                int index = path.indexOf(bp);
                int k = index + 1;
                while (k <= path.size() - 1) {
                    path.remove(path.size() - 1);
                }
                backPoint.remove(bp);
            }

        }

        if (counter >= 2) {
//            System.out.println("Counter: " + counter);
            for (int i = 0; i < counter - 1; i++) {
                backPoint.add(p1);
            }
//            System.out.println("BACKPOINT");
//            System.out.println(getList(backPoint));
        }

        for (Position d : ds) {
            ArrayList<Position> path2 = path;
            path2.add(d);
            next(map, direct, path2, backPoint, d, p2, result);
        }

//        System.out.println(getList(path));
    }

    public static boolean equals(Position p1, Position p2) {
        return p1.x == p2.x && p1.y == p2.y;
    }

    public static boolean contains(ArrayList<Position> ps, Position p) {
        for (Position i : ps) {
            if (i.x == p.x && i.y == p.y) {
                return true;
            }
        }
        return false;
    }

    public static String getList(ArrayList<Position> ps) {
        String rs = "";
        for (Position p : ps) {
            rs += ">>" + "[" + p.x + "," + p.y + "]";
        }
        return rs;
    }

    public static void main(String[] args) {
        int[][] map = (new LoadMap()).getMap("/map/testPathMin");
        int[] direct = {1, 2, 3, 4};
        Position p1 = new Position(0, 0);
        Position p2 = new Position(map[0].length - 1, map.length - 1);
        for (int[] k : map) {
            for (int i : k) {
                System.out.print(i + " ");
            }
            System.out.println("");
        }

        System.out.println("\n\n");

        ArrayList<Position> path = new ArrayList<Position>();
        path.add(p1);

        ArrayList<Position> backPoint = new ArrayList<Position>();

        ArrayList<Position> result = new ArrayList<Position>();

        next(map, direct, path, backPoint, p1, p2, result);

        System.out.println("RESULT");

        System.out.println(getList(result));
        
        ArrayList<Position> kq = new ArrayList<>();
        ArrayList<Position> kq2 = new ArrayList<>();
        for(Position p : result){
            if(equals(p, new Position(-1, -1))){
                break;
            }
            kq2.add(p);
        }
        
        System.out.println(getList(kq));
     
        
    }

}
