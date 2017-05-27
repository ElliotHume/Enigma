/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma;

import static enigma.Enigma.font;
import static enigma.Enigma.smallFont;
import java.io.*;
import java.util.Scanner;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 *
 * @author elliot
 */
public class Puzzle {
    public int Sequence;
    public String name;
    public String[] text;
    public Image pic;
    public String[] keys;
    public String google;
    
    
    public Puzzle(int Sequence){
        this.Sequence = Sequence;
        this.build();
    }
    
    public void display(int x, int y, Graphics g){
        g.setFont(font);
        g.drawString(name, x, y);
        
        g.setFont(smallFont);
        if (pic != null){
            g.drawImage(pic, 300-(pic.getWidth()/2), (y+30));
            
            int s = 0;
            for (String line : text){
                g.drawString(line, x, (y+pic.getHeight()+50)+s);
                s += 20;
            }
        } else {
            int s = 0;
            for (String line : text){
                g.drawString(line, x, (y+50)+s);
                s += 20;
            }
        }
    }
    
    public void build(){     
        File in = null;
        Scanner sc = null;
        String contents = null;
        String[] contentsList = null;
        
        try {
            String file = "puzzles/keys.txt";
            in = new File(file);
            sc = new Scanner(in);
            
            for (int i = 0; i < this.Sequence; i++){
                contents = sc.nextLine();
            }
                      
            contentsList = contents.split("~");
            this.name = contentsList[0];
            this.text = contentsList[1].split("_");
            this.keys = contentsList[4].split("_");
            this.google = contentsList[3];
            this.pic = new Image(contentsList[2]);
            
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            sc.close();
        }
    }
}
