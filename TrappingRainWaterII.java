class Solution {
    public int trapRainWater(int[][] heightMap) {
        int directions[][] = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((v1,v2)->v1[2]-v2[2]);
        int rowsLength = heightMap.length;
        int colsLength = heightMap[0].length;
        boolean visited[][] = new boolean[rowsLength][colsLength];
        for(int i=0;i<rowsLength;i++) {
            priorityQueue.offer(new int[]{i,0,heightMap[i][0]});
            priorityQueue.offer(new int[]{i,colsLength-1, heightMap[i][colsLength-1]});
        }
        
        for(int i=0;i<colsLength;i++) {
            priorityQueue.offer(new int[]{0,i,heightMap[0][i]});
            priorityQueue.offer(new int[]{rowsLength-1,i,heightMap[rowsLength-1][i]});
        }
        
        int result = 0;
        while(!priorityQueue.isEmpty()) {
            int cell[] = priorityQueue.poll();
            for(int[] dir:directions) {
                int newR = cell[0]+dir[0];
                int newC = cell[1]+dir[1];
                if((newR>0) && (newR <rowsLength-1) && (newC>0) && (newC < colsLength-1) && (!visited[newR][newC])) {
                    visited[newR][newC] = true;
                    result+=Math.max(0,cell[2] - heightMap[newR][newC]);
                    priorityQueue.offer(new int[]{newR, newC, Math.max(cell[2],heightMap[newR][newC])});
                }
            }
        }
        return result;
    }
}
