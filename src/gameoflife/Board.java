/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author Marcelino Andrade
 */
public class Board extends JPanel implements ComponentListener, MouseListener {
    
    public static Board get;
    
    private int cellSize = 20;
    private int width, height, count;
    private static int[] cells;
    
    public Board(int width, int height, int cellSize) {
        
        get = this;
        addMouseListener(this);
        
        this.width = width; this.height = height;
        this.count = width * height;
        this.cellSize = cellSize;
        
        setSize(new Dimension(width * cellSize, height * cellSize));
        
        cells = new int[this.count];
        
        for (int i : cells)
            i = 0;
    }
    
    public void Update(int[] cells)
    {
        for (int i = 0; i < cells.length; i++)
        {
            this.cells[i] = cells[i];
        }
    }
    
    @Override public void paint(Graphics g){
        super.paintComponent(g);
        
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                paintCell(g, j * cellSize, i * cellSize, cellSize, cellSize, getColor(cells[i * width + j]));
            }
        }
        java.awt.Toolkit.getDefaultToolkit().sync();
    }
    
    private void paintCell(Graphics g, int x, int y, int w, int h, Color color)
    {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, w, h);
        g.setColor(color);
        g.fillRect(x + 1, y + 1, w - 2, h - 2);
    }
    
    private Color getColor(int state)
    {
        return (state == 0) ? Color.DARK_GRAY : Color.GREEN;
    }
    
    public int getCellState(int i, int j)
    {
        return this.cells[i * width + j];
    }
    
    public void setCellState(int i, int j, int state)
    {
        this.cells[i * width + j] = state;
    }
    
    public void switchState(int i, int j)
    {
        cells[i * width + j] = (cells[i * width + j] + 1) % 2;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        int i = (int)(e.getY() / cellSize);
        int j = (int)(e.getX() / cellSize);
        
        if (!Main.isRunning)
        {
            switchState(i, j);
            Main.frame.repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentResized(ComponentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentShown(ComponentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
