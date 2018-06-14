/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strongly_connected_com;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Mat Nguyen
 */
public class STRONGLY_CONNECTED_COM {
    int n;
    int m;
    Set<Integer> V;
    Map<Integer, Set<Integer>> A;
    Map<Integer, Set<Integer>> B;
    char[] color;
    int count = 0;
    int[] icc;
    int idx = 0;
    int[] x;
   
    
    public STRONGLY_CONNECTED_COM(){
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        STRONGLY_CONNECTED_COM str = new STRONGLY_CONNECTED_COM();
        str.input();
        str.DFS();
        str.bu_graph();
        str.duyet_V();
        str.DFS();
        str.printResult();
        
    }
    
    public void input(){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        V = new HashSet<>();
        A = new HashMap<Integer, Set<Integer>>();
        B = new HashMap<Integer, Set<Integer>>();
        icc = new int[n + 1];
        color = new char[n + 1];
        x = new int[2 * n + 1];
        for(int i = 0; i < n; i++){
            int v = in.nextInt();
            V.add(v);
            A.put(v, new HashSet<Integer>());
            B.put(v, new HashSet<Integer>());
        }
        for(int i = 0; i < m; i++){
            int u = in.nextInt();
            int v = in.nextInt();
            A.get(u).add(v);
        }
    }

    
    public void bu_graph(){
        for(int v: V){
            for(int u: A.get(v)){
                B.get(u).add(v);
            }
        }
        A.clear();
    }
    
    
    public void duyet_V(){
        V.clear();
        for(int i = n; i >= 1; i--){
            V.add(x[i]);
        }
    }
    
    public void DFS(){
        
        for(int v: V){
            color[v] = 'W';
        }
        for(int v: V){
            if(color[v] == 'W'){
                
                if(A.isEmpty()) {
                    count++;
                    DFS_visit(v, B);
                } else {
                    DFS_visit(v, A);
                }
                
            }
        }
    }
    
    public void DFS_visit(int u, Map<Integer, Set<Integer>> M){
        color[u] = 'G';
        for(int v: M.get(u)){
            if(color[v] == 'W')
                DFS_visit(v, M);
        } 
        color[u] = 'B';
        icc[u] = count;
        idx++;
        x[idx] = u;
        
    }
    
    public void printResult(){
        System.out.println("Số thành phần liên thông mạnh: " + count);
        for(int i = 0; i < count; i++){
            System.out.print("Thành phần liên thông mạnh thứ " + (i + 1) +" là: ");
            for(int v: V){
                if(icc[v] == i + 1){
                    System.out.print(v + " ");
                }
            }
            System.out.println("");
        }
    }
    
}
