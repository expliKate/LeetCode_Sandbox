import java.util.ArrayList;

// LeetCode
//------------------------------------------------------------------------
//class Solution{
//------------------------------------------------------------------------

// IDE
//------------------------------------------------------------------------
class SpiralMatrix{
//------------------------------------------------------------------------
    private enum Direction {up, down, left, right}

    // LeetCode
    //------------------------------------------------------------------------
    //public List<Integer> spiralOrder(int[][] matrix) {
    //------------------------------------------------------------------------

    // IDE
    //------------------------------------------------------------------------
    public static void main(String[] args){
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        //int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        //int[][] matrix = {};                      // Passes
        //int[][] matrix = {{1,2,3},{1,2}};         // TODO: Unhandled - not sure how to do this without adding more loops.
        //int[][] matrix = {{},{}};                 // Passes
    //------------------------------------------------------------------------

        // Check that there is incoming data.
        if(matrix.length < 1 || matrix[0].length < 1) {
            System.out.println("Too few items in input array.\nProgram aborting...");
            return;     // return null; for LeetCode
        }

        // Default contents are FALSE so no need to initialize.
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];

        // LeetCode
        //------------------------------------------------------------------------
        //return traverse(visited, matrix, 0, 0, Direction.left);
        //------------------------------------------------------------------------

        // IDE
        //------------------------------------------------------------------------
        ArrayList<Integer> results = traverse(visited, matrix, 0, 0, Direction.left);
        System.out.println("The final ArrayList is: " + results);
        //------------------------------------------------------------------------
    }

    private static ArrayList<Integer> traverse(boolean[][] visited, int[][] matrix, int i, int j, Direction cameFrom){
        int new_i = i;
        int new_j = j;
        Direction new_cameFrom = null;
        ArrayList<Integer> results = new ArrayList<Integer>();
        boolean hasUnvisitedNeighbors = false;

        // BASE CASE: No unvisited nodes around me.

        // First, ascertain who my neighbors are.
        // TODO: This strategy seems too time- and memory-intensive
        ArrayList<Integer[]> neighbors = new ArrayList<Integer[]>();
        if(j+1 < visited[i].length) neighbors.add(new Integer[]{i,j+1});           // To the right of current.
        if(i+1 < visited.length) neighbors.add(new Integer[]{i+1,j});              // Below current.
        if(j-1 >= 0) neighbors.add(new Integer[]{i,j-1});                           // To the left of current.
        if(i-1 >= 0) neighbors.add(new Integer[]{i-1,j});                           // Above current.

        // Next, check whether any one has not already been visited.
        for (Integer[] neighb: neighbors) {
            if(!visited[neighb[0]][neighb[1]]){
                hasUnvisitedNeighbors = true;
                break;
            }
        }

        // Then, if there aren't any unvisited neighbors, just return myself (fully traversed).
        if(!hasUnvisitedNeighbors){
            results.add(0,matrix[i][j]);
        }
        // OTHERWISE
        // If it came from the left, go right until you can't, then go down.
        // (If neither of the above are possible, this will already
        // have been caught in the base case check above.)
        // If it came from above, go down until you can't, then go left.
        // If it came from the right, go left until you can't, then go up.
        // If it came from below, go up until you can't, then go right.
        else{
            switch(cameFrom){
                case left:
                    // If you can go right,
                    if(j+1 < matrix[i].length && !visited[i][j+1]){
                        // go right.
                        new_j = j + 1;
                        new_cameFrom = Direction.left;
                    }
                    else {
                        // go down.
                        new_i = i + 1;
                        new_cameFrom = Direction.up;
                    }
                    break;
                case up:
                    // If you can go down,
                    if(i+1 < matrix.length && !visited[i+1][j]) {
                        // go down.
                        new_i = i + 1;
                        new_cameFrom = Direction.up;
                    }
                    else{
                        // go left.
                        new_j = j-1;
                        new_cameFrom = Direction.right;
                    }
                    break;
                case right:
                    // If you can go left,
                    if(j-1 >=0 && !visited[i][j-1]) {
                        // go left.
                        new_j = j - 1;
                        new_cameFrom = Direction.right;
                    }
                    else {
                        // go up.
                        new_i = i - 1;
                        new_cameFrom = Direction.down;
                    }
                    break;
                case down:
                    // If you can go up,
                    if(i-1 >= 0 && !visited[i-1][j]) {
                        // go up.
                        new_i = i - 1;
                        new_cameFrom = Direction.down;
                    }
                    else{
                        // go right.
                        new_j = j + 1;
                        new_cameFrom = Direction.left;
                    }
                    break;
                default:
                    // TODO: throw an error or something
                    // This shouldn't be possible - likely means a problem with marking nodes visited.
                    break;
            }
            // Mark the node visited.
            visited[i][j] = true;
            // Visit the selected node by calling self recursively, flagging where I've come from so next call has
            // the necessary context.
            results = traverse(visited, matrix, new_i, new_j, new_cameFrom);
            // Add current node to all of the properly traversed nodes after current.
            results.add(0,matrix[i][j]);
        }

        return results;
    }
}