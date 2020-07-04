package graphautomorphism;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Juan Esteban Rozo Urbina
 */
//El siguiente programa calcula el grupo de automorfismos de una grafo de n 
//vértices,las aristas se ingresan por teclado una sola vez (la arista (i,j)=(j,i)).
//Las permutaciones se imprimen por consola y son de la forma (a1,a2,...,an) que 
//correspondería a la representación "matricial" de la permutación y no a su forma
//cíclica,es decir, la permutación de un grafo de 4 vértices (2 1 4 3) corresponde 
//en forma cíclica a (1,2)(4,3).
public class GraphAutomorphism {

    static ArrayList<String> ListP = new ArrayList<String>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner leer = new Scanner(System.in);
        System.out.print("Ingrese el número de nodos: ");
        int num = leer.nextInt();
        System.out.println("Grupo de automorfismos de un grafo de "+num+" nodos");
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 1; i <= num; i++) {
            list.add(String.valueOf(i));
        }
        
        Permutar("",list);
        //Matriz de adyacencia del grafo:
        ArrayList<String> edges = new ArrayList<>();
        boolean menu = true;
        int des;
        System.out.println();
        boolean[][] matrix = new boolean[num][num];
        int arista=1;
        while(menu){
            System.out.println("--------- Arista "+arista+" -------------");
            System.out.print("Ingrese el primer nodo incidente: ");
            int e1 = leer.nextInt();
            System.out.println();
            System.out.print("Ingrese el segundo nodo incidente: ");
            int e2 = leer.nextInt();
            System.out.println();
            edges.add(String.valueOf(e1)+String.valueOf(e2));
            matrix[e1-1][e2-1] = true;
            matrix[e2-1][e1-1] = true;
            System.out.println("Que desea hacer?");
            System.out.println("1. Registrar más aristas");
            System.out.println("2. Salir");
            des = leer.nextInt();
            if (des==2) {
                menu=false;
            }
            arista++;
        }
        boolean desicion;
        for (int i = 0; i < ListP.size(); i++) {
            desicion = aplicarPermutacion(ListP.get(i),matrix,edges);
            if (desicion) {
                System.out.println(ListP.get(i));
            }
        }
    }
    
    static boolean aplicarPermutacion(String permutacion,boolean[][] matrix,
            ArrayList edges){
        ArrayList<String> fin = aplicarPermutacion(edges,permutacion);
        for (int i = 0; i < fin.size(); i++) {
            String aux = fin.get(i);
            int a = Integer.valueOf(aux.substring(0,1));
            int b = Integer.valueOf(aux.substring(1));
            if (!((matrix[a-1][b-1])&&((matrix[b-1][a-1])))) {
                return false;
            }
        }
        return true;
    }
    
    static ArrayList aplicarPermutacion(ArrayList edges,String permutacion){
        char[] array = permutacion.toCharArray();
        ArrayList<String> fin = new ArrayList<>();
        for (int i = 0; i < edges.size(); i++) {
            fin.add("00");
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < edges.size(); j++) {
                int[] aux = new int[2];
                String a = edges.get(j).toString();
                char[] node = a.toCharArray();
                aux[0] = Character.getNumericValue(node[0]);
                aux[1] = Character.getNumericValue(node[1]);
                if (aux[0]==(i+1)) {
                    aux[0] = Character.getNumericValue(array[i]);
                    String b = fin.get(j);
                    fin.set(j, array[i]+b.substring(1));
                }
                if (aux[1]==(i+1)) {
                    aux[1] = Character.getNumericValue(array[i]);
                    String b = fin.get(j);
                    fin.set(j, b.substring(0,1)+array[i]);
                }
            }
        }
        return fin;
    }
    static void Permutar(String c,ArrayList list){
        if (list.size()==0) {
            ListP.add(c);
        }
        for (int i = 0; i < list.size(); i++) {
            String a = (String)list.remove(i);
            Permutar(c.concat(a),list);
            list.add(i, a);
        }
    }
}
