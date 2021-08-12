/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import static gameoflife.Main.DIMENSION;
import static gameoflife.Main.frame;
import static gameoflife.Main.isRunning;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcelino Andrade
 */
public class CellProcessor extends Thread {
    
    private static int[] localCellsBuffer;
    private int offset, count;
    private int total;
    public static final int ALIVE = 1;
    public static final int DEAD = 0;
    
    public CellProcessor(int total, int offset, int count)
    {
        localCellsBuffer = new int[total];
        this.total = total;
        this.offset = offset;
        this.count = count;
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
            for (int k = this.offset; k < this.offset + this.count; k++)
            {
                int i = (int)(k / DIMENSION);//
                int j = k % DIMENSION;//
                
                int ngbAlive = 0;
                
                System.out.print("this: " + i + ", " + j);
                for (int x = -1; x < 2; x++)
                {
                    int xj = j + x;
                    if (xj < 0 || xj >= DIMENSION) continue;
                    
                    for (int y = -1; y < 2; y++)
                    {
                        int yi = i + y;
                        
                        if (x == 0 && y == 0) continue;
                        if (yi < 0 || yi >= DIMENSION) continue;
                        
                        ngbAlive += Board.get.getCellState(yi, xj);
                        System.out.print(" | n: " + yi + ", " + xj);
                    }
                }
                System.out.println("");
                
                int _this = Board.get.getCellState(i, j);
                if (_this == ALIVE)
                {
                    if (ngbAlive == 2 || ngbAlive == 3)
                    {
                        localCellsBuffer[k] = ALIVE;
                    }
                    else
                        localCellsBuffer[k] = DEAD;
                }
                else if (ngbAlive == 3)
                {
                    localCellsBuffer[k] = ALIVE;
                }
            }
            
            Board.get.Update(localCellsBuffer);
            
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
        
        try {
            this.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(CellProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
