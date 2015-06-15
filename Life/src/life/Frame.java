/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package life;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.*;

/**
 *
 * @author Hutnik-Admin
 */
public class Frame extends JFrame {

    File file = new File("test.txt");
    LifeGame game = new LifeGame(file);
    DrawPanel drawPanel;
    GenerationButton genButton;
    ClearButton clrButton;


    public Frame() {
        super("Life");
        genButton = new GenerationButton();
        clrButton = new ClearButton();
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawPanel = new DrawPanel(game, genButton);
        add(new ButtonPanel(game, genButton, drawPanel, clrButton));

        add(drawPanel);

        setBounds(0, 0, game.map_y * 25 + 50, game.map_x * 25 + 50);
        pack();
        setVisible(true);
    }

    private class ButtonPanel extends JPanel implements ActionListener {
        JButton clrButton;
        JButton genButton;
        JButton openButton;
        JFileChooser chooser;
        
        
        LifeGame game;
        DrawPanel drawPanel;

        
        public ButtonPanel(LifeGame game, GenerationButton generationButton, DrawPanel drawPanel, ClearButton clearButton) {
            super();
            this.game = game;
            genButton = generationButton;
            clrButton = clearButton;
            openButton = new OpenButton();
            chooser = new JFileChooser();
            add(clrButton);
            add(genButton);
            add(openButton);
            
            this.drawPanel = drawPanel;

            genButton.addActionListener(this);
            clrButton.addActionListener(this);
            openButton.addActionListener(this);
            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent ev) {
            Object source = ev.getSource();

            if (source == genButton) {
                game.generate();
                game.show();
                drawPanel.reload();

            }else if(source == clrButton){
                game.clear();
                game.show();
                drawPanel.reload();
                
            }else if(source == openButton){
                //game.clear();
                //game.show();
                //drawPanel.reload();
                int returnedVal = chooser.showOpenDialog(this);
                
                if(returnedVal == chooser.APPROVE_OPTION){
                    game = new LifeGame(chooser.getSelectedFile());
                    game.show();
                    drawPanel.reload();
                }else{
                    
                    System.out.println("Plik nie zostal wczytany");
                    
                }
                
            }

        }

    }

    private class GenerationButton extends JButton {

        public GenerationButton() {
            super("Generation++");
            setSize(50, 100);
            setVisible(true);
        }

    }
    
    private class OpenButton extends JButton {

        public OpenButton() {
            super("Open File");
            setSize(50, 100);
            setVisible(true);
        }

    }
    
        private class ClearButton extends JButton {

        public ClearButton() {
            super("Clear");
            setSize(50, 100);
            setVisible(true);
        }

    }

        /*
private class TimeButton extends JButton {

        public TimeButton() {
            super("Generate");
            setSize(50, 100);
            setVisible(true);
        }

    }
*/
    private class DrawPanel extends JPanel implements MouseListener {
        int x, y;
        final int BLOCK_SIZE = 50;
        LifeGame game;
        JButton genButton;

        public DrawPanel(LifeGame game, GenerationButton generationButton) {
            super();
            this.game = game;
            this.addMouseListener(this);
            genButton = generationButton;
            setPreferredSize(new Dimension(game.map_x * BLOCK_SIZE, game.map_y * BLOCK_SIZE));
            setBackground(Color.white);
            setVisible(true);
        }

        public void reload() {
            repaint();
            System.out.println("generation++");

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            for (int i = 0; i < game.map_y; i++) {
                for (int j = 0; j < game.map_x; j++) {
                    g2d.drawRect(j * 50, i * 50, 48, 48);
                    if (game.map[j][i] == 1) {
                        g2d.fillRect(j * 50, i * 50, 48, 48);
                    }

                }
            }

        }
        
        @Override
        public void mouseClicked(MouseEvent ev){
            //System.out.println("mouseClicked");
            x = ev.getX();
            y = ev.getY();
            System.out.println("x = " + x + " y = " + y);
            System.out.println("int_div x = " + int_div(x, BLOCK_SIZE) + "int_div y = " + int_div(y, BLOCK_SIZE));
            x = int_div(x, BLOCK_SIZE);
            y = int_div(y, BLOCK_SIZE);
            
            game.changeState(x, y);
            repaint();
        }
        
        @Override
        public void mouseEntered(MouseEvent ev){
            //System.out.println("mouseEntered");
        }
        
        @Override
        public void mousePressed(MouseEvent ev){
            //System.out.println("mousePressed");
        }
        
        @Override
        public void mouseExited(MouseEvent ev){
            //System.out.println("mouseExited");
        }
        @Override
        public void mouseReleased(MouseEvent ev){
            //System.out.println("mouseReleased");
        }
        
        int int_div(int a,int b){
            int n;
            n = (int)(a/b);
            System.out.println("n = " + n);
            return n;
        }
        
    }
        
}