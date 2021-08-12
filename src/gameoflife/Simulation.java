/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import static gameoflife.Main.frame;
import static gameoflife.Main.isRunning;
import static gameoflife.Main.board;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marce
 */
public class Simulation extends Thread {
    
    private int[] cellsBuffer;
    
    public Simulation()
    {
        /*cellsBuffer = new int[board.cells.length];
        for (int i = 0; i < cellsBuffer.length; i++)
            cellsBuffer[i] = board.cells[i];*/
    }
    
    @Override public void start()
    {
        isRunning = true;
        
        super.start();
    }
    
    @Override public void run()
    {
        while (isRunning)
        {
            //
            
            frame.repaint();
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void stopSimulation()
    {
        isRunning = false;
    }
}
