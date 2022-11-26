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
    void removeConnection(Node n){
        connections.remove(n);
    }
    public String toString(){
        return String.valueOf(id);
    }
}

public class Graph {
    ArrayList<Node> nodes = new ArrayList<>();
    public int startVertex = 0;

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

    private int DFS(Node startNode){
        ArrayList<Node> visited = new ArrayList<>();
        LinkedList<Node> stack = new LinkedList<>();

        int count = 0;
        stack.push(startNode);
        visited.add(startNode);
        while (stack.size() != 0){
            Node curSearch = stack.pop();
            count++;
            for (Node av: curSearch.getConnections()){
                if(!visited.contains(av)){
                    stack.push(av);
                    visited.add(av);
                }
            }

        }

        return count;
    }
    private boolean isBridge(Node n1, Node n2){
        int count1 = DFS(n2);
        n1.removeConnection(n2);
        n2.removeConnection(n1);
        int count2 = DFS(n2);
        n1.addConnections(n2);
        n2.addConnections(n1);
        return (count1>count2) ? true:false;
    }

    private boolean isValidNextEdge(Node n1, Node n2){
        if (n1.getDegree() == 1){
            return true;
        }
        if (!isBridge(n1,n2)){
            return true;
        }

        return false;
    }

    private void printEuler(Node n){
        ArrayList<Node> adjNodes = n.getConnections(); 
        for (int i=0; i<adjNodes.size(); i++){
            Node adj = adjNodes.get(i);
            if (isValidNextEdge(n, adj)){
                System.out.println(n + " -> " + adj);
                n.removeConnection(adj);
                adj.removeConnection(n);
                printEuler(adj);
                break;
            }
        }
    }

    // see if you can save and restore the graph
    public void getEulerPath(){
        if (!checkIfEvenDegrees()){
            System.out.println("Graph cannot have Euler path as it has one or more vertices of odd degree");
            return;
        }
        if (nodes.size() == 0){
            return;
        }
        printEuler(nodes.get(startVertex));
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