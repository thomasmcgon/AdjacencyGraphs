public class Node {
    int data;
    int connected;

    Node(){

    }
    Node (int info){
        data = info;
    }
    Node (int info, String connections) {
        data = info;
        String temp[] = connections.split("//s+");

        for(String thing : temp){
            Integer.parseInt(thing);
        }
    }
}