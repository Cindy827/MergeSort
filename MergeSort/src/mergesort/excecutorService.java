/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesort;

/**
 *
 * @author Cindy
 */
public class excecutorService implements Runnable{
    int [] arreglo;
    int primero;
    int ultimo;
    
    public excecutorService(int [] arreglo, int primero, int ultimo){
        this.arreglo = arreglo;
        this.primero = primero;
        this.ultimo = ultimo;
    }
    
    @Override
    public void run() {
        mergeSort(arreglo, primero, ultimo);
    }
    
    public void mergeSort(int []lista, int ini, int fin){
        int med;
        if(ini<fin){
            med = (ini+fin)/2;
            mergeSort(lista, ini, med);
            mergeSort(lista, med+1, fin);
            mezclar(lista, ini, med, fin);
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
