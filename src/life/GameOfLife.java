package life;

import javax.swing.*;
import java.awt.event.ItemEvent;

public class GameOfLife extends JFrame {

    private Universe universe;
    private final JLabel generationLabel;
    private final JLabel aliveLabel;
    private final Cells cellGrid;
    private final JToggleButton playToggleButton;
    private Timer timer;

    public GameOfLife() {
        super("Game Of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 750);
        JPanel box = new JPanel();
        JButton resetButton = new JButton("reset");
        resetButton.setName("ResetButton");
        resetButton.addActionListener(actionEvent -> {
            timer.stop();
            startEvolution();
        });
        playToggleButton = new JToggleButton("pause");
        playToggleButton.setName("PlayToggleButton");
        playToggleButton.addItemListener(itemEvent -> {
            int state = itemEvent.getStateChange();
            if (state == ItemEvent.SELECTED) {
                timer.stop();
                playToggleButton.setText("resume");
            } else {
                playToggleButton.setText("pause");
                timer.restart();
            }
        });
        JPanel header = new JPanel();
        header.setName("Header");
        header.setLayout(new BoxLayout(header, BoxLayout.PAGE_AXIS));
        generationLabel = new JLabel("Generation #0");
        generationLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        generationLabel.setName("GenerationLabel");
        header.add(generationLabel);

        aliveLabel = new JLabel("Alive: 0");
        aliveLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        aliveLabel.setName("AliveLabel");
        header.add(aliveLabel);
        JPanel buttonBox = new JPanel();
        buttonBox.add(resetButton);
        buttonBox.add(playToggleButton);
        header.add(buttonBox);
        box.add(header);
        boolean[][] cellStatus = new boolean[1][1];
        cellGrid = new Cells(cellStatus);
        box.add(cellGrid);
        add(box);
        setVisible(true);
        startEvolution();
    }

    public void startEvolution() {
        int size = 100;
        universe = new Universe(size);
        this.setGenerationLabel(universe.getGenerations());
        this.setAliveLabel(universe.getCurrentGeneration().getAliveCells());
        this.draw(universe.getCurrentGeneration().getGameBoard());
        this.revalidate();
        this.repaint();
        final int interval = 500;
        timer = new Timer(interval, evt -> {
            universe.evolution();
            this.setGenerationLabel(universe.getGenerations());
            this.setAliveLabel(universe.getCurrentGeneration().getAliveCells());
            this.draw(universe.getCurrentGeneration().getGameBoard());
            this.revalidate();
            this.repaint();
        });
        timer.start();
    }

    public void setGenerationLabel(int generations) {
        generationLabel.setText("Generation #" + generations);
    }

    public void setAliveLabel(int aliveCells) {
        aliveLabel.setText("Alive: " + aliveCells);
    }

    public void draw(boolean[][] cellStatus) {
        cellGrid.setState(cellStatus);
        cellGrid.repaint();
    }
}