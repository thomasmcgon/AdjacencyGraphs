import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Graphs {
    public static boolean[][] truthMatrix;
    public static int[][] edgeMatrix;
    public static int[][] numberInNodeMatrix;
    public static int nodesInGraph;


    // A utility function to find the vertex with minimum key
    // value, from the set of vertices not yet included in MST
    public static int minKey(int key[], Boolean mstSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE;
        int min_index = -1;

        for (int v = 0; v < nodesInGraph; v++)
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }

    // A utility function to print the constructed MST stored in
    // parent[]
    public static void printMST(int parent[])
    {
        System.out.println(nodesInGraph);
        for (int i = 1; i < nodesInGraph; i++)
            System.out.println("1" + " " + i + " " + edgeMatrix[i][parent[i]]);
    }

    // Function to construct and print MST for a graph represented
    // using adjacency matrix representation
    public static void MST()
    {
        String[] print = new String[nodesInGraph];
        int[] counter = new int[nodesInGraph];
        print[0] = "" + nodesInGraph;

        // Array to store constructed MST
        int parent[] = new int[nodesInGraph];

        // Key values used to pick minimum weight edge in cut
        int key[] = new int[nodesInGraph];

        // To represent set of vertices included in MST
        Boolean mstSet[] = new Boolean[nodesInGraph];

        // Initialize all keys as INFINITE
        for (int i = 0; i < nodesInGraph; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // Always include first 1st vertex in MST.
        key[0] = 0;
        // Make key 0 so that this vertex is picked as first vertex
        parent[0] = -1; // First node is always root of MST

        // The MST will have V vertices
        for (int count = 0; count < nodesInGraph - 1; count++) {
            // Pick the minimum key vertex from the set of vertices
            // not yet included in MST
            int u = minKey(key, mstSet);

            // Add the picked vertex to the MST Set
            mstSet[u] = true;

            // Update key value and parent index of the adjacent
            // vertices of the picked vertex. Consider only those
            // vertices which are not yet included in MST
            for (int v = 0; v < nodesInGraph; v++)

                // graph[u][v] is non zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST
                // Update the key only if graph[u][v] is smaller than key[v]
                if (edgeMatrix[u][v] != 0 && mstSet[v] == false && edgeMatrix[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = edgeMatrix[u][v];
                }
        }

        // print the constructed MST
        printMST(parent);
    }

    /*
    Reads in the file with arguments given by the user
    */
    public static boolean connected(){
        int i = 0;
        int counter = 0;

        while(!truthMatrix[counter][0]){
            counter++;
        }
        if(truthMatrix[counter][0] == false){
            return false;
        }
        else{
            while(counter < truthMatrix.length){
                if(truthMatrix[counter][i]){
                    counter++;
                    i++;
                }
                else{
                    return false;
                }
            }
            return true;
        }
    }

    public static void shortestPath(){

    }

    /**
     * Finds out by if the graph given is metric by going down the main diagonal
     * seeing if it is all zeros. This is done by tallying the amount of zeros in the diagonal
     * and if the counter is the same as the size of the graph then it is metric.
     *
     * @return true if the graph is Metric and false if it is not metric
     */
    public static boolean isMetric(){
        int count = 0;
        for(int i = 0; i < truthMatrix.length; ++i){
            for(int j = 0; j < truthMatrix.length; ++j){
                if (i == j){
                    if(truthMatrix[i][j] == false){
                        ++count;
                    }
                }
            }
        }
        if(count == truthMatrix.length){
            return true;
        }
        else{
            return false;
        }
    }

    public static void makeMetric(){

    }

    public static void approxTSP(){

    }


    //C:\Users\17Pee\IdeaProjects\COMP-2100-Graphs\src\testG1.txt
    public static void readFile(String fileName) throws FileNotFoundException {
        try {
            File file = new File(fileName);
            Scanner scan = new Scanner(file);
            String[] lineParsed;
            int i = 0;
            while(scan.hasNextLine()){
                //Reads in the first line so it gets how many nodes are in the Graph
                if(i == 0){
                    nodesInGraph = Integer.parseInt(scan.nextLine());
                    truthMatrix = new boolean[nodesInGraph][nodesInGraph];
                    edgeMatrix = new int[nodesInGraph][nodesInGraph];
                    numberInNodeMatrix = new int[nodesInGraph][nodesInGraph];
                }
                //Reads in the rest of the lines in the file into nodes then matrices
                else {
                    lineParsed = (scan.nextLine()).split("\\s+");

                    for(int j = 1; j < lineParsed.length; j += 2){
                        int nodeLoc = Integer.parseInt(lineParsed[j]);
                        edgeMatrix[i - 1][nodeLoc] = Integer.parseInt(lineParsed[j + 1]);
                        truthMatrix[i - 1][nodeLoc] = true;
                        numberInNodeMatrix[i - 1][nodeLoc] = Integer.parseInt(lineParsed[0]);
                    }
                }
                i++;
            }
            scan.close();
        }
        catch(FileNotFoundException e){
            System.err.println("File Not Found, Please try again ");
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        String fileName = "";
        int input = 0;

        System.out.print("Enter graph file: ");
        fileName = in.nextLine();
        readFile(fileName);
        System.out.println("Truth Matrix is as follows");
        for(int i = 0; i < truthMatrix.length; i++){
            for (int j = 0; j < truthMatrix.length; j++){
                System.out.print(truthMatrix[i][j] + ", ");
            }
            System.out.println();
        }
        System.out.println("\nEdge Matrix is as follows");
        for(int i = 0; i < edgeMatrix.length; i++){
            for (int j = 0; j < edgeMatrix.length; j++){
                System.out.print(edgeMatrix[i][j] + ", ");
            }
            System.out.println();
        }
        System.out.println("\nNode Number Matrix is as follows");
        for(int i = 0; i < numberInNodeMatrix.length; i++){
            for (int j = 0; j < numberInNodeMatrix.length; j++){
                System.out.print(numberInNodeMatrix[i][j] + ", ");
            }
            System.out.println();
        }



        do {
            System.out.println("1. Is Connected\n2. Minimum Spanning Tree\n3. Shortest Path\n4. Is Metric\n5. Make Metric\n6. Traveling Salesman Problem\n7. Approximate TSP\n8. Quit");
            input = in.nextInt();

            //Is Connected
            if(input == 1) {
                if (connected() == true) {
                    System.out.println("Graph is connected.");
                } else {
                    System.out.println("Graph is not connected");
                }
            }
            //Minimum Spanning Tree
            else if (input == 2){
                if (connected() == true) {
                    MST();
                } else {
                    System.out.println("Graph is not connected");
                }
            }
            //Shortest Path
            else if (input == 3){
                if (connected() == true) {
                    shortestPath();
                } else {
                    System.out.println("Graph is not connected");
                }
            }
            //Is Metric
            else if (input == 4){
                if (isMetric() == true) {
                    System.out.println("Graph is metric");
                } else if(isMetric() == false){
                    System.out.println("Graph is not connected");
                }
            }
            //Make Metric
            else if (input == 5){
                if (connected() == true) {
                    makeMetric();
                } else {
                    System.out.println("Graph is not connected");
                }
            }
            //Traveling Salesman Problem
            else if (input == 6){
                if (connected() == true) {

                } else {
                    System.out.println("Graph is not connected");
                }
            }
            //Approximate TSP
            else if (input == 7){
                if (connected() == true) {
                    approxTSP();
                } else {
                    System.out.println("Graph is not connected");
                }
            }
            else if (input == 8){

            }
            else{
                System.out.println("Enter a valid input (1-8)");
            }
        }while(input != 8);
        in.close();
    }
}
