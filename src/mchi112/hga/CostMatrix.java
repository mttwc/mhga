package mchi112.hga;

public class CostMatrix {
    private int[][] matrix;
    private int size;

    public CostMatrix(int[][] matrix) throws Exception {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix.length != matrix[i].length) {
                throw new Exception("Cost matrix must be a square matrix");
            }
        }
        this.matrix = matrix;
        this.size = matrix.length;
    }

    public float getDistance(int source, int destination) throws IndexOutOfBoundsException {
        if (source < 1 || source > this.size) {
            throw new IndexOutOfBoundsException("Source " + source + " is out of bounds for cost matrix of size " + this.size);
        }
        if (destination < 1 || destination > this.size) {
            throw new IndexOutOfBoundsException("Destination " + destination + " is out of bounds for cost matrix of size " + this.size);
        }
        return matrix[source-1][destination-1];
    }

    public int getSize() {
        return this.size;
    }
}
