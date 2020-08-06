package br.com.testes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author julia
 */
public class Solution {

    public static int computeClosestToZero(int[] ts) {
        if (ts.length == 0) {
            return 0;
        }
        
        int[] maior = new int[ts.length];
        int[] menor = new int[ts.length];
        
        for (int i = 0; i < ts.length; i++) {
            int j = ts[i];
            if (j >= 0) {
                maior[i] = j;
            }
        }
        
        for (int i = 0; i < ts.length; i++) {
            int j = ts[i];
            if (j <= 0) {
                menor[i] = j;
            }
        }

        Arrays.sort(maior);
        Arrays.sort(menor);
        
        
        
        System.out.println(Arrays.toString(maior));
        System.out.println(Arrays.toString(menor));

//        for (int t : ts) {
//            System.out.println(t);
//            System.out.println(t*-1);
//            
//            if (t >= 0) {
//                maior[t] = t;
//            } else {
//                menor[menor.length+1] = t;
//            } 
//        }
//        Arrays.sort(maior);
//        Arrays.sort(menor);
//        System.out.println(maior);
//        System.out.println(menor);
//        Arrays.sort(ts);

//        System.out.println(Arrays.toString(ts));
//        To debug: System.err.println("Debug messages...");
        return 0;
    }
}
