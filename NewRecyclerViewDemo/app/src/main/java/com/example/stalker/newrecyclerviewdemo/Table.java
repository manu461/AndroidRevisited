package com.example.stalker.newrecyclerviewdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by staLker on 21-06-2016.
 */
public class Table {
    
    private static int to;
    private static int uo;
    private int tableof;
    private int index;
    private String result;
    public static void tableDetails(int t , int u){
        to = t;
        uo = u;
    }
    
    public Table(int p,int q){
        this.tableof = p;
        this.index = q;
    }
    
    public static List<Table> wholeTable(){
        List<Table> table = new ArrayList<>();
        for(int i=1; i<= uo ; i++){
            table.add(new Table(to,i));
        }
        return table;
    }
    
    public String getTable(){
        

        String p = Integer.toString(tableof);
        String q = Integer.toString(index);
        String r = Integer.toString(tableof*index);
        String s = p+"x"+q+"="+r;


        return s;
    }

}
