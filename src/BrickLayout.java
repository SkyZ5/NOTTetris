import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BrickLayout {

    private ArrayList<Brick> bricks;
    private int[][] brickLayout;
    private int cols;
    private int rows;
    private ArrayList<Brick> completedBricks;

    public BrickLayout(String fileName, int cols, int rows, boolean dropAllBricks) {
        this.cols = cols;
        ArrayList<String> fileData = getFileData(fileName);
        bricks = new ArrayList<Brick>();
        completedBricks = new ArrayList<Brick>();
        for (String line : fileData) {
            String[] points = line.split(",");
            int start = Integer.parseInt(points[0]);
            int end = Integer.parseInt(points[1]);
            Brick b = new Brick(start, end);
            bricks.add(b);
        }
        brickLayout = new int[rows][cols];
        if (dropAllBricks) {
            while (bricks.size() != 0) {
                doOneBrick();
            }
        }
    }

    public ArrayList<Brick> getBricks() {
        return completedBricks;
    }

    public void doOneBrick() {
        if (bricks.size() != 0) {
            Brick b = bricks.remove(0);
            int currentHeight = 0;
            for (int i = 0; i < completedBricks.size(); i++) {
                if (completedBricks.get(i).getHeight() >= currentHeight) {
                    if ((completedBricks.get(i).getStart() <= b.getStart() && completedBricks.get(i).getEnd() >= b.getStart()) || (completedBricks.get(i).getStart() <= b.getEnd() && completedBricks.get(i).getEnd() >= b.getEnd()) || (completedBricks.get(i).getStart() >= b.getStart() && completedBricks.get(i).getEnd() <= b.getEnd())) {
                        b.setHeight(completedBricks.get(i).getHeight() + 1);
                        currentHeight = b.getHeight();

                    }
                }
            }
            for(int i = b.getStart(); i <= b.getEnd(); i++){
                brickLayout[brickLayout.length - b.getHeight()][i] = 1;
            }
            System.out.println(b);
            completedBricks.add(b);
        }
    }
    public ArrayList<String> getFileData(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }
        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        return fileData;
    }

    public int[][] getBrickLayout() {
        return brickLayout;
    }

    public void printBrickLayout() {
        for (int r = 0; r < brickLayout.length; r++) {
            for (int c = 0; c < brickLayout[0].length; c++) {
                System.out.print(brickLayout[r][c] + " ");
            }
            System.out.println();
        }
    }

    public boolean checkBrickSpot(int r, int c) {
        if (brickLayout[r][c] == 1) {
            return true;
        }
        else {
            return false;
        }
    }
}