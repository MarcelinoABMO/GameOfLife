/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import static gameoflife.Main.frame;
import static gameoflife.Main.isRunning;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marce
 */
public class Simulation extends Thread {
    
    private static int[] cellsBuffer;
    private int total;
    private CellProcessor[] processors;
    private static final int THREAD_COUNT = 30;
    
    public Simulation(int dimension)
    {
        this.total = dimension * dimension;
        this.processors = new CellProcessor[THREAD_COUNT];
    }
    
    @Override public void start()
    {
        isRunning = true;
        cellsBuffer = new int[this.total];
        
        super.start();
    }
    
    @Override public void run()
    {
        while (isRunning)
        {
            for (int i = 0; i < THREAD_COUNT; i++)
                this.processors[i] = new CellProcessor(cellsBuffer, THREAD_COUNT * i, this.total / THREAD_COUNT);
            
            for (CellProcessor p : this.processors)
                p.start();
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            for (CellProcessor p : this.processors){
                try {
                    p.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            Board.get.Update(this.cellsBuffer);
            
            frame.repaint();
        }
    }
    
    public void stopSimulation()
    {
        isRunning = false;
        
        try {
            this.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(CellProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
