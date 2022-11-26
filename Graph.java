import java.util.*;

class Node{
    private int id;
    private ArrayList<Node> connections = new ArrayList<>(); 
    Node(int id){
        this.id = id;
    }
    int getId(){
        return id;
    }
    ArrayList<Node> getConnections(){
        return connections;
    }
    void addConnections(Node n){
        connections.add(n);
    }
    int getDegree(){
        return connections.size();
    }
    public String toString(){
        return String.valueOf(id);
    }
}

public class Graph {
    ArrayList<Node> nodes = new ArrayList<>();

    public Graph(int[][] adjMat){
        int size = adjMat.length;
        for (int i=0; i<size; i++){
            nodes.add(new Node(i));
        }

        for (int i=0; i<size; i++){
            int rowCount = adjMat[i].length;

            // Filter non square matrices
            if (rowCount != size){
                nodes = new ArrayList<>(Arrays.asList(new Node[]{}));
                return;
            }
            for (int j=0; j<rowCount; j++){
                if (adjMat[i][j] == 1){
                    // undirected edge
                    nodes.get(i).addConnections(nodes.get(j));
                    nodes.get(j).addConnections(nodes.get(i));
                }
            }
        }
    }

    public boolean checkIfEvenDegrees(){
        for (Node n:nodes){
            if (n.getDegree()%2!=0){
                return false;
            }
        }
        return true;
    }

    public String toString(){
        String returnString = "";
        for (int i=0; i<nodes.size(); i++){
            Node curNode = nodes.get(i);
            var conns = curNode.getConnections();
            if (conns.size() == 0){
                returnString += curNode + "";
            }
            for (int j=0;j<conns.size();j++){
                returnString += curNode + " <-> " + conns.get(j);
                if (j != conns.size()-1){
                    returnString += ", ";
                } 
            }

            if (i != nodes.size()-1){
                returnString += "\n";
            }  
        }
        return returnString;
    }  
}