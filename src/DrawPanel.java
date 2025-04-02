import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

public class DrawPanel extends JPanel implements MouseListener {

    private int[][] grid;
    private BrickLayout brickLayout;
    private long time;
    private ArrayList<Brick> bricks = new ArrayList<Brick>();
    private int num = 0;

    public DrawPanel() {
        this.addMouseListener(this);
        brickLayout = new BrickLayout("src/Bricks", 40, 30, true);
        bricks = brickLayout.getBricks();
        grid = new int[30][40];
        createGrid(0);
        time = System.currentTimeMillis();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 10;
        int y = 10;
        if(System.currentTimeMillis() - time > 100){
            num++;
            createGrid(num);
            time = System.currentTimeMillis();
        }
        Graphics2D g2 = (Graphics2D)g;

        for (int c = 0; c < 40; c++) {
            for (int r = 0; r < 30; r++) {
                g.drawRect(x, y, 20, 20);
                if (grid[r][c] == 1) {
                    g2.setColor(Color.RED);
                    g2.fillRect(x, y, 20, 20);
                    g2.setColor(Color.BLACK);
                }
                y += 25;
            }
            x += 25;
            y = 10;
        }

    }
    public void createGrid(int amount){
        for(int i = 0; i < grid.length; i++){
            for(int n = 0; n < grid[0].length; n++){
                grid[i][n] = 0;
            }
        }
        if (amount > bricks.size()){
            amount = bricks.size();
        }
        for(int i = 0; i < amount; i ++){
            Brick brick = bricks.get(i);
            if(30 - brick.getCurrentHeight() != brick.getHeight()) {
                brick.setCurrentHeight(brick.getCurrentHeight() + 1);
            }
            for(int n = 0; n < brick.getEnd() - brick.getStart() + 1; n ++){
                grid[brick.getCurrentHeight()][brick.getStart() + n] = 1;
            }
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}