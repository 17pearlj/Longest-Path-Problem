class Solution {
    int max;
    int[][]arr;
    int[][]matrix;
    int[][] temp;
    public int longestIncreasingPath(int[][] matrix) {
        max = 1;
        this.matrix = matrix;
        temp = new int[4][2];
        arr = new int[matrix.length][matrix[0].length];
        resetArr();
        longestIncreasingPathRec(0, 0);
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                longestIncreasingPathRec(r, c);
            }
        }
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                max = Math.max(max, arr[r][c]);
            }
        }
        
                        
        return max;
    }
    
    public int longestIncreasingPathRec(int r, int c) {
        if (!isValidRC(r, c)) { return 0; }
        if (arr[r][c] == -1) {
            int val = 0;
            fillTemp(r, c);
            for (int i = 0; i < temp.length; i++) {
                if (isValidRC(temp[i][0], temp[i][1]) && matrix[temp[i][0]][temp[i][1]] > matrix[r][c]) {
                    val = Math.max(val, longestIncreasingPathRec(temp[i][0], temp[i][1]));
                }
                
            }
            arr[r][c] = 1 + val;
            
        }
        return arr[r][c];
    }
    
    public void fillTemp(int r, int c) {
        temp[0][0] = r + 1;
        temp[0][1] = c;
        temp[1][0] = r - 1;
        temp[1][1] = c;
        temp[2][0] = r;
        temp[2][1] = c + 1;
        temp[3][0] = r;
        temp[3][1] = c - 1;
    }
    
    public boolean isValidRC(int r, int c) {
        return (r >= 0 && c >= 0 && r < matrix.length && c < matrix[0].length);
    }
    
    public void resetArr() {
        for (int i = 0; i < matrix.length; i++) {
            Arrays.fill(arr[i], -1);
        }
    }
}
