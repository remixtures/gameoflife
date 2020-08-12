package life;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Cells extends JPanel {

    int gridSize;
    boolean[][] cellStatus;

    public Cells(boolean[][] cellStatus) {
        super();
        setPreferredSize(new Dimension(600, 600));
        this.setBorder(BorderFactory.createLineBorder(Color.gray));
        this.cellStatus = Arrays.stream(cellStatus).map(boolean[]::clone).toArray(boolean[][]::new);
        this.gridSize = cellStatus[0].length;
    }

    public void setState(boolean[][] cellStatus) {
        int cellStatusSize = cellStatus[0].length;
        if (this.cellStatus.length != cellStatusSize) {
            this.cellStatus = new boolean[cellStatusSize][cellStatusSize];
            this.gridSize = cellStatusSize;
        }
        this.cellStatus = Arrays.stream(cellStatus).map(boolean[]::clone).toArray(boolean[][]::new);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension panelSize = this.getSize();
        Graphics2D g2 = (Graphics2D) g;
        for (int rows = 0; rows < gridSize; rows++) {
            for (int columns = 0; columns < gridSize; columns++) {
                if (cellStatus[rows][columns]) {
                    g2.setColor(Color.black);
                    g2.fillRect(columns * (panelSize.width/gridSize),
                            rows * (panelSize.height/gridSize),
                            panelSize.width/gridSize - 1,
                            panelSize.height/gridSize - 1);
                } else {
                    g2.setColor(Color.lightGray);
                    g2.drawRect(columns * (panelSize.width/gridSize),
                            rows * (panelSize.height/gridSize),
                            panelSize.width/gridSize - 1,
                            panelSize.height/gridSize - 1);
                }
            }
        }
    }
}
