import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // TODO: Add any necessary instance variables.
    private final int n;
    private final boolean[][] open;
    private final WeightedQuickUnionUF ufPercolates;
    private final WeightedQuickUnionUF ufFull;
    private final int virtualTop;
    private final int virtualBottom;
    private int openCount=0;

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        if(N<=0){
            throw new IllegalArgumentException();
        }
        this.n = N;
        this.open = new boolean[N][N];
        this.ufPercolates = new WeightedQuickUnionUF(N*N+2);
        this.ufFull = new WeightedQuickUnionUF(N*N+1);
        this.virtualTop = N*N;
        this.virtualBottom = N*N+1;
        this.openCount=0;



    }
    private int index(int row ,int col){
        return row*n+col;
    }

    private void validate(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new IllegalArgumentException();
        }
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        validate(row, col);

        if (open[row][col]) {
            return;
        }

        open[row][col] = true;
        openCount++;

        int id = index(row, col);

        if (row == 0) {
            ufPercolates.union(id, virtualTop);
            ufFull.union(id, virtualTop);
        }

        if (row == n - 1) {
            ufPercolates.union(id, virtualBottom);
        }

        if (row > 0 && isOpen(row - 1, col)) {
            int up = index(row - 1, col);
            ufPercolates.union(id, up);
            ufFull.union(id, up);
        }

        if (row < n - 1 && isOpen(row + 1, col)) {
            int down = index(row + 1, col);
            ufPercolates.union(id, down);
            ufFull.union(id, down);
        }

        if (col > 0 && isOpen(row, col - 1)) {
            int left = index(row, col - 1);
            ufPercolates.union(id, left);
            ufFull.union(id, left);
        }

        if (col < n - 1 && isOpen(row, col + 1)) {
            int right = index(row, col + 1);
            ufPercolates.union(id, right);
            ufFull.union(id, right);
        }


    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        validate(row,col);
        return open[row][col];
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        validate(row,col);
        if(!open[row][col]){
            return false;
        }
        return ufFull.connected(index(row, col), virtualTop);
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return openCount;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        return ufPercolates.connected(virtualTop, virtualBottom);
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
