/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import static gameoflife.Main.DIMENSION;

/**
 *
 * @author Marcelino Andrade
 */
public class CellProcessor extends Thread {
    
    private static int[] localCellsBuffer;
    private int offset, count;
    public static final int ALIVE = 1;
    public static final int DEAD = 0;
    
    public CellProcessor(int[] cells, int offset, int count)
    {
        localCellsBuffer = cells;
        this.offset = offset;
        this.count = count;
    }
    
    @Override public void run()
    {
        for (int k = this.offset; k < this.offset + this.count; k++)
        {
            int i = (int)(k / DIMENSION);//
            int j = k % DIMENSION;//

            int ngbAlive = 0;
            
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
                }
            }

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
    }
}
