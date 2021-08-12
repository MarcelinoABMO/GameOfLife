/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author marce
 */
public class Main {

    private static Board board;
    public static JFrame frame;
    private static final int CELL_SIZE = 20;
    private static final int DIMENSION = 30;
    public static boolean isRunning = false;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        frame = new JFrame("Game of Life");
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        
        board = new Board(DIMENSION, DIMENSION, CELL_SIZE);
        
        frame.add(board);
        frame.pack();
        
        Simulation();
    }
    
    private static void Simulation()
    {
        //frame.repaint();
    }
}
