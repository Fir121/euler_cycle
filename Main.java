public class Main {
    public static void main(String[] args){
        int [][] adjMat = new int[][]{{0,1,0,0},{0,0,1,0},{0,0,0,1},{1,0,0,0}};
        Graph graph = new Graph(adjMat);

        // View the graph in mapping format
        System.out.println(graph);

        if (!graph.checkIfEvenDegrees()){
            System.out.println("Degrees are not even");
        }
    }
}
