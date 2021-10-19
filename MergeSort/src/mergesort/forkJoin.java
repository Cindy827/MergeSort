/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesort;

import java.util.concurrent.RecursiveAction;

/**
 *
 * @author Cindy
 */
public class forkJoin extends RecursiveAction{
    int arreglo[];
    int inicio;
    int ultimo;
    
    public forkJoin(int []arreglo, int inicio, int ultimo){
        this.arreglo = arreglo;
        this.inicio = inicio;
        this.ultimo = ultimo;
    }
    
    @Override
    protected void compute() {

        if( (ultimo-inicio) < 2){
            if(arreglo[inicio]>arreglo[ultimo]){
                int tmp = arreglo[ultimo];
                arreglo[ultimo] = arreglo[inicio];
                arreglo[inicio] = tmp;
            }
        }else{
            int med;
            med = (inicio+ultimo)/2;
            
            invokeAll(new forkJoin(arreglo, inicio, med), new forkJoin(arreglo, med+1, ultimo));
            //https://programmerclick.com/article/43431394560/
            
            mezclar(arreglo, inicio, med, ultimo);
        }
        
    }
        
    public void mezclar(int []lis, int ini, int med, int fin){
        int izq = ini, der = med+1, ia = 0;
        int []liAux = new int[lis.length];
        
        while(izq<=med && der<=fin){
            if(lis[izq]<lis[der]){
                liAux[ia] = lis[izq];
                izq++;
                ia++;
            }else{
                liAux[ia] = lis[der];
                der++;
                ia++;
            }//if-else
        }//while
        
        for(int k=der;k<=fin;k++){
            liAux[ia] = lis[k];
            ia++;
        }
        
        for(int k=izq;k<=med;k++){
            liAux[ia] = lis[k];
            ia++;
        }
        
        for(int k=0; k<=ia-1;k++){
            lis[ini+k] = liAux[k];
        }   
    }
    
}
