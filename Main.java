public class Main {
    public static void main(String[] args){
        //SAMPLE COMMAND : java Main.java "0 1 1 1 1" "1 0 1 0 0" "1 1 0 0 0" "1 0 0 0 1" "1 0 0 1 0"

        int[][] adjMat;
        // defaults to 0
        int startVertex = 0;
        try{
            if (args[args.length-2].trim().equals("start")){
                startVertex = Integer.parseInt(args[args.length-1]);
            }
            adjMat = new int[args.length][args.length];
            for (int i=0;i<args.length;i++){
                String[] temp = args[i].split(" ");
                for (int j=0; j<args.length; j++){
                    adjMat[i][j] = Integer.valueOf(temp[j]);
                }
            }
        }
        catch(Exception e){
            System.out.println("Incorrect usage of Command Line Arguments - Specify Adjacency Matrix [format '01' '10']");
            return;
        }

        if (args.length == 0){
            System.out.println("No arguments specified, defaulting to sample graph -> '0 1 1 1 1' '1 0 1 0 0' '1 1 0 0 0' '1 0 0 0 1' '1 0 0 1 0'");
            adjMat = new int[][]{{0,1,1,1,1},{1,0,1,0,0},{1,1,0,0,0},{1,0,0,0,1},{1,0,0,1,0}};
        }

        Graph graph = new Graph(adjMat);

        // View the graph in an arrow format
        //System.out.println(graph);

        graph.startVertex = startVertex;
        graph.getEulerPath();
    }
}
