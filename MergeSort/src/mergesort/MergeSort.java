/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesort;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

/**
 *
 * @author Cindy
 */
public class MergeSort extends JFrame  implements ActionListener{
    int AnchoVentana = 990;
    int AltoVentana = 400;
    
    JButton secuencial, forkJoin, excecutorService; //Declaracion de botones
    //JButton adios;
    JLabel t2, t3,t4,t5;
    
    int arreglo[];
    int arregloAux[];
    TextField lngArr;
    JTable tabla;
    JScrollPane scrollArrDes, scrollArrSec, scrollArrFJ, scrollArrES;


    public static void main(String[] args) {
        MergeSort ventana = new MergeSort();
        
        //Boton Generar arreglo dinamico
        JButton btnGenerarArreglo = new JButton("Generar Arreglo");
        btnGenerarArreglo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int tam = Integer.parseInt(ventana.lngArr.getText().toString());
                System.out.println("\nGenerando arreglo dinámico de tamaño ");
                ventana.arreglo = new int[tam];
                ventana.arregloAux = new int[tam];
                ventana.generarArregloDinamico(ventana.arreglo, tam);
                //ventana.imprimirArreglo(ventana.arreglo);
                /*
                Interfaz grafica
                */
                JTextArea lista = new JTextArea();
                lista.setText("");
                for(int i=0; i<ventana.arreglo.length;i++)
                    lista.append(ventana.arreglo[i] + ", ");
                ventana.scrollArrDes.setViewportView(lista); 
                ventana.setVisible(true);                
            }
        });
        btnGenerarArreglo.setBounds(120, 30, 200, 30);
        ventana.add(btnGenerarArreglo);
        
        //Boton secuencial
        JButton btnSecuencial = new JButton("Secuencial");
        btnSecuencial.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                System.out.println("\nEjecutando método Secuencial de Merge Sort!");                
                //System.out.println("Imprimiendo arreglo original");
                for(int i=0; i<ventana.arreglo.length; i++){
                    ventana.arregloAux[i] = ventana.arreglo[i];
                }
                //ventana.imprimirArreglo(ventana.arregloAux);
                
                long inicioTiempo = System.currentTimeMillis();
                new secuencial(ventana.arregloAux);
                long finTiempo = System.currentTimeMillis();

                System.out.println("\nTiempo de ejecución en milisegundos = " + (finTiempo-inicioTiempo));
                
                //System.out.println("\narreglo ordenado");
                //ventana.imprimirArreglo(ventana.arregloAux);
                
                /*
                Interfaz grafica
                */
                JTextArea lista = new JTextArea();
                lista.setText("");
                for(int i=0; i<ventana.arregloAux.length;i++)
                    lista.append(ventana.arregloAux[i] + ", ");
                ventana.scrollArrSec.setViewportView(lista); 
                ventana.setVisible(true);
                ventana.t2.setText("Tiempo de ejecución Secuencial (milisegundos): " + (finTiempo-inicioTiempo));
            }
        });
        btnSecuencial.setBounds(115, 170, 100, 30);
        ventana.add(btnSecuencial);
        
        //Boton Fork/Join
        JButton btnFJ = new JButton("Fork/Join");
        btnFJ.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("\nEjecutando ForK/Join");                
                //System.out.println("Imprimiendo arreglo original");
                for(int i=0; i<ventana.arreglo.length; i++){
                    ventana.arregloAux[i] = ventana.arreglo[i];
                }
                //ventana.imprimirArreglo(ventana.arregloAux);
                
                long inicioTiempo = System.currentTimeMillis();
                ForkJoinPool pool = ForkJoinPool.commonPool();
                pool.invoke(new forkJoin(ventana.arregloAux, 0, ventana.arregloAux.length-1));                
                long finTiempo = System.currentTimeMillis();
                
                System.out.println("\nTiempo de ejecución uwu en milisegundos = " + (finTiempo-inicioTiempo));

                //System.out.println("\narreglo ordenado");
                //ventana.imprimirArreglo(ventana.arregloAux);
        
                /*
                Interfaz grafica
                */
                JTextArea lista = new JTextArea();
                lista.setText("");
                for(int i=0; i<ventana.arregloAux.length;i++)
                    lista.append(ventana.arregloAux[i] + ", ");
                ventana.scrollArrFJ.setViewportView(lista); 
                ventana.setVisible(true);
                ventana.t3.setText("Tiempo de ejecución ForkJoin (milisegundos): " + (finTiempo-inicioTiempo));
            }
        });
        btnFJ.setBounds(430, 170, 100, 30);
        ventana.add(btnFJ);
        
         //Boton ExecutorService
        JButton btnES = new JButton("ExecutorService");
        btnES.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                System.out.println("\nEjecutando ExecutorService");                
                //System.out.println("Imprimiendo arreglo original");
                for(int i=0; i<ventana.arreglo.length; i++){
                    ventana.arregloAux[i] = ventana.arreglo[i];
                }
                //ventana.imprimirArreglo(ventana.arregloAux);
                
                long inicioTiempo = System.currentTimeMillis();
                ExecutorService executor = Executors.newCachedThreadPool();
                executor.execute(new excecutorService(ventana.arregloAux, 0, ventana.arregloAux.length-1));
                executor.shutdown();
                while(!executor.isTerminated()){}
                long finTiempo = System.currentTimeMillis();

                System.out.println("\nTiempo de ejecución en milisegundos = " + (finTiempo-inicioTiempo));
                
                //ventana.imprimirArreglo(ventana.arregloAux);
                
                
                /*
                Interfaz grafica
                */
                JTextArea lista = new JTextArea();
                lista.setText("");
                for(int i=0; i<ventana.arregloAux.length;i++)
                    lista.append(ventana.arregloAux[i] + ", ");
                ventana.scrollArrES.setViewportView(lista); 
                ventana.setVisible(true);
                ventana.t4.setText("Tiempo de ejecución ExcecutorService (milisegundos): " + (finTiempo-inicioTiempo));
            }
        });
        btnES.setBounds(705, 170, 150, 30);
        ventana.add(btnES);  
    } 
    
    public MergeSort(){
        setSize(AnchoVentana,AltoVentana);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("MergeSort by Cindy");
        setResizable(false);
        this.setLayout(null);
        
        //Secuencial
        t5 = new JLabel("Ingrese longitud del array (int)");
        t5.setBounds(10, 5, 300, 30);
        this.add(t5);
      
       //Ingreso de longitud de array
        lngArr = new TextField();
        lngArr.setBounds(10, 30, 100, 30);
        this.add(lngArr);     
        
        //ArregoDinamico
        JLabel t1 = new JLabel("Arreglo Dinámico");
        t1.setBounds(10, 70, 150, 20);
        this.add(t1);
        scrollArrDes = new JScrollPane();
        scrollArrDes.setBounds(10, 100, 920, 40);
        this.add(scrollArrDes);
        
        //Secuencial
        t2 = new JLabel("Tiempo de ejecución secuencial (milisegundos): ");
        t2.setBounds(10, 250, 300, 30);
        this.add(t2);
        scrollArrSec = new JScrollPane();
        scrollArrSec.setBounds(10, 210, 300, 40);//x,y,tamx,tamy
        this.add(scrollArrSec);
        
        //Fork/Join
        t3 = new JLabel("Tiempo de ejecución Fork Join (milisegundos): ");
        t3.setBounds(320, 250, 300, 30);
        this.add(t3);
        scrollArrFJ = new JScrollPane();
        scrollArrFJ.setBounds(320, 210, 300, 40);//x,y,tamx,tamy
        this.add(scrollArrFJ);        
        
        //ExecutorService
        t4 = new JLabel("Tiempo de ejecución ExcecutorService (milisegundos): ");
        t4.setBounds(630, 250, 350, 30);
        this.add(t4);
        scrollArrES = new JScrollPane();
        scrollArrES.setBounds(630, 210, 300, 40);
        this.add(scrollArrES);       
        
        this.setVisible(true);
    }
    
   public void generarArregloDinamico(int []arreglo, int limite){
        for(int i=0; i<arreglo.length; i++){
            arreglo[i] = (int) (Math.random()*limite+1);//Aleatorios entre 1 y limite
        }
    }
    
    public void imprimirArreglo(int [] arreglo){
        for(int i=0; i<arreglo.length; i++){
            System.out.print(arreglo[i] + ", ");
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
