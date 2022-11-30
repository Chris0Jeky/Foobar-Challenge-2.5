import java.util.ArrayList;

public class Solution {
    public static int solution(int src, int dest) {
        return bruteForce(coordinatesTranslationNumToArr(src), coordinatesTranslationNumToArr(dest));
    }

    public static int[][] biArray() {
        int quadraticSize = 8;
        int[][] biArr = new int[quadraticSize][quadraticSize];
        for (int s = 0; s < quadraticSize; s++) {
            for (int i = 0; i < quadraticSize; i++){
                biArr[s][i] = i;
            }
        }
        return biArr;
    }

    public static ArrayList<int[]> legitMoves(int coordinateRow, int coordinateColumn) {
        int[][] totalMoves = {{2, 1}, {2, -1},
                {-2, 1}, {-2, -1},
                {1, 2}, {1, -2},
                {-1, 2}, {-1, -2}};
        ArrayList<int[]> legitMvs = new ArrayList<>();
        for (int i = 0; i < totalMoves.length; i++){
            if (((totalMoves[i][0] + coordinateRow) <= 7) && ((totalMoves[i][0] + coordinateRow) >= 0) &&
                    ((totalMoves[i][1] + coordinateColumn) <= 7) && ((totalMoves[i][1] + coordinateColumn) >= 0)){
                legitMvs.add(totalMoves[i]);
            }
        }
        return legitMvs;
    }

    public static int[] coordinatesTranslationNumToArr(int coordinateNum){
        int[] coordinateBiArr = new int[2];
        coordinateBiArr[1] = coordinateNum % 8;
        coordinateBiArr[0] = coordinateNum / 8;
        return coordinateBiArr;
    }
    public static int coordinatesTranslateArrToNum(int[] coordinatesBi){
        return coordinatesBi[0] * 8 + coordinatesBi[1];
    }


    public static int bruteForce(int[] src, int[] dest) {
        ArrayList<int[]> visited = new ArrayList<>();
        ArrayList<int[]> queue = legitMoves(src[0], src[1]);
        int depth = 1;
        /* 1. Check if the destination is in the queue
            * 2. If not, add all the legit moves to the queue
            * 3. Check if the destination is in the queue
            * 4. If not, add all the legit moves to the queue
            * 5. Repeat until the destination is in the queue
            * 6. Return the lowest depth
         */
        while (true) {
            for (int i = 0; i < queue.size(); i++) {
                if (queue.get(i)[0] == dest[0] && queue.get(i)[1] == dest[1]) {
                    return depth;
                }
                visited.add(queue.get(i));
            }
            ArrayList<int[]> tempQueue = new ArrayList<>();
            for (int i = 0; i < queue.size(); i++) {
                for (int j = 0; j < legitMoves(queue.get(i)[0], queue.get(i)[1]).size(); j++) {
                    if (!visited.contains(legitMoves(queue.get(i)[0], queue.get(i)[1]).get(j))) {
                        tempQueue.add(legitMoves(queue.get(i)[0], queue.get(i)[1]).get(j));
                    }
                }
            }
            queue = tempQueue;
            depth++;
        while (!queue.isEmpty()) {
            int[] current = queue.remove(0);
            if (current[0] == dest[0] && current[1] == dest[1]) {
                return depth;
            }
            if (!visited.contains(current)) {
                visited.add(current);
                queue.addAll(legitMoves(current[0], current[1]));
            }
        }
        return -1;
    }}}