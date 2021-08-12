/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Marcelino Andrade
 */
public class Main extends Thread {

    public static Board board;
    public static JFrame frame;
    private static final int CELL_SIZE = 20;
    public static final int DIMENSION = 30;
    public static boolean isRunning = false;
    private static CellProcessor simulation;
    private static JButton startBtn, stopBtn;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        frame = new JFrame("Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(new Dimension((DIMENSION + 1) * CELL_SIZE, (DIMENSION + 1) * CELL_SIZE + CELL_SIZE * 5));
        
        board = new Board(DIMENSION, DIMENSION, CELL_SIZE);
        
        startBtn = new JButton("Start");
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startBtn.setEnabled(false);
                simulation = new CellProcessor(DIMENSION * DIMENSION, 0, DIMENSION * DIMENSION);
                simulation.start();
                stopBtn.setEnabled(true);
            } 
        });
        stopBtn = new JButton("Stop");
        stopBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                stopBtn.setEnabled(false);
                simulation.stopSimulation();
                startBtn.setEnabled(true);
            }
        });
        
        frame.add(board);
        board.setLocation(0, 0);
        
        JPanel gui = new JPanel(new GridLayout(1, 2));
        gui.setSize(new Dimension((DIMENSION + 1) * CELL_SIZE, CELL_SIZE * 4));
        gui.add(startBtn);
        gui.add(stopBtn);
        frame.add(gui);
        gui.setLocation(0, DIMENSION * CELL_SIZE);
        
        frame.setVisible(true);
    }
}
