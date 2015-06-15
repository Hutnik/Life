/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package life;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Hutnik-Admin
 */
public class LifeGame {

    int map[][];
    int tmp_map[][];
    int map_y, map_x;
    int x_tmp, y_tmp;
    String tmp_String;
    
    public LifeGame(File file) {
        try {
            Scanner fscan = new Scanner(file);
            x_tmp = 1;
            y_tmp = 1;
            if (fscan.hasNext()) {
                tmp_String = fscan.next();
                x_tmp = tmp_String.length();
            }
            while (fscan.hasNext()) {
                String j = fscan.next();

                System.out.println("zwiekszamy y, wczytane : " + j);
                y_tmp++;

            }
            map_x = x_tmp;
            map_y = y_tmp;
            map = new int[map_x][map_y];
            tmp_map = new int[map_x][map_y];
            fscan = new Scanner(file);
            for (int i = 0; i < map_y; i++) {
                tmp_String = fscan.next();
                for (int j = 0; j < map_x; j++) {
                    if (j < tmp_String.length()) {

                        map[j][i] = Integer.parseInt(String.valueOf(tmp_String.charAt(j)));
                    } else {
                        map[j][i] = 0;
                    }
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku!");
        }

    }

    void show() {
        System.out.println("Map:");
        for (int i = 0; i < map_y; i++) {
            
            for (int j = 0; j < map_x; j++) {
                System.out.print(" " + map[j][i] + " ");
            }
                System.out.print("\n");
        }

    }
    
        void show_tmp() {
        System.out.println("tmp_map:");
        for (int i = 0; i < map_y; i++) {
            
            for (int j = 0; j < map_x; j++) {
                System.out.print(" " + tmp_map[j][i] + " ");
            }
                System.out.print("\n");
        }

    }

    
    void copy_matrix2_to_matrix1(int matrix1[][], int matrix2[][], int x, int y ){
        for(int i = 0; i < y; i++){
            for(int j = 0; j < x; j++){
                
                matrix1[j][i] = matrix2[j][i];
                
            }
            
        }
        
    }
    
    void generate(){
        copy_matrix2_to_matrix1(tmp_map, map, map_x, map_y);
        for (int i = 0; i < map_y; i++) {
            
            for (int j = 0; j < map_x; j++) {
                if(neighbourhood(j, i) == 3 && map[j][i] == 0) tmp_map[j][i] = 1;
                else if((neighbourhood(j, i) == 2 || neighbourhood(j, i) == 3) && map[j][i] == 1 ) tmp_map[j][i] = 1;
                else tmp_map[j][i] = 0;
            }
        }
        copy_matrix2_to_matrix1(map, tmp_map, map_x, map_y);
    }
    
    
    void clear() {
        for (int i = 0; i < map_y; i++) {
            
            for (int j = 0; j < map_x; j++) {
                map[j][i] = 0;
            }
        }

    }
    
    
    void changeState(int x, int y){
        if(map[x][y] == 1) map[x][y] = 0;
        else if(map[x][y] == 0) map[x][y] = 1;
    }
    
    int neighbourhood(int x, int y){                // sprawdzamy na rzeczywistej mapie
        int n = 0;
        try{
            if(map[x-1][y+1] == 1) n++;
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
            if(map[x][y+1] == 1) n++;
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
            if(map[x+1][y+1] == 1) n++;
        }catch(ArrayIndexOutOfBoundsException e){}        
        try{
            if(map[x+1][y] == 1) n++;
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
            if(map[x+1][y-1] == 1) n++;
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
            if(map[x][y-1] == 1) n++;
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
            if(map[x-1][y-1] == 1) n++;
        }catch(ArrayIndexOutOfBoundsException e){}                
        try{
            if(map[x-1][y] == 1) n++;
        }catch(ArrayIndexOutOfBoundsException e){}        

        return n;
    }
}