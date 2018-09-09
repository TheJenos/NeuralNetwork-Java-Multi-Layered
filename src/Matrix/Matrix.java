/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matrix;

/**
 *
 * @author TheJenos
 */
public class Matrix {

    private int rows, cols;
    private double[][] data;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows][cols];
    }

    public double[] toArray() {
        double[] out = new double[getRows()];
        for (int i = 0; i < getRows(); i++) {
            out[i] = getData()[i][0];
        }
        return out;
    }

    public static Matrix ArrayToMatrix(double[] m1) {
        Matrix out = new Matrix(m1.length, 1);
        for (int i = 0; i < m1.length; i++) {
            double d = m1[i];
            out.getData()[i][0] = d;
        }
        return out;
    }

    public static Matrix MatrixTranspose(Matrix m1) {
        Matrix out = new Matrix(m1.getCols(), m1.getRows());
        for (int i = 0; i < m1.getRows(); i++) {
            for (int j = 0; j < m1.getCols(); j++) {
                out.getData()[j][i] = m1.getData()[i][j];
            }
        }
        return out;
    }

    public static Matrix MatrixMapping(Matrix mat, MatrixFuntion func) {
        Matrix out = new Matrix(mat.getRows(), mat.getCols());
        for (int i = 0; i < mat.getData().length; i++) {
            for (int j = 0; j < mat.getData()[i].length; j++) {
                out.getData()[i][j] = func.funtion(mat.getData()[i][j], i, j);
            }
        }
        return out;
    }

    public static Matrix MatrixDotMultiply(Matrix m1, Matrix m2) {
        if (m1.getCols() != m2.getRows()) {
            throw new MatrixErrorException("m1_rows must equal to m2_columns");
        }
        Matrix out = new Matrix(m1.getRows(), m2.getCols());
        for (int i = 0; i < out.getRows(); i++) {
            for (int j = 0; j < out.getCols(); j++) {
                double sum = 0;
                for (int k = 0; k < m1.getCols(); k++) {
                    sum += m1.getData()[i][k] * m2.getData()[k][j];

                }
                out.getData()[i][j] = sum;
            }
        }
        return out;
    }

    public static Matrix MatrixElementMultiply(Matrix m1, Matrix m2) {
        if (m1.getCols() != m2.getCols() || m1.getRows() != m2.getRows()) {
            throw new MatrixErrorException("Rows and Columns are not matching");
        }
        Matrix out = new Matrix(m1.getRows(), m1.getCols());
        for (int i = 0; i < m1.getData().length; i++) {
            for (int j = 0; j < m1.getData()[i].length; j++) {
                out.getData()[i][j] = m1.getData()[i][j] * m2.getData()[i][j];
            }
        }
        return out;
    }

    public static Matrix MatrixSubtract(Matrix m1, Matrix m2) {
        if (m1.getCols() != m2.getCols() || m1.getRows() != m2.getRows()) {
            throw new MatrixErrorException("Rows and Columns are not matching");
        }
        Matrix out = new Matrix(m1.getRows(), m1.getCols());
        for (int i = 0; i < m1.getData().length; i++) {
            for (int j = 0; j < m1.getData()[i].length; j++) {
                out.getData()[i][j] = m1.getData()[i][j] - m2.getData()[i][j];
            }
        }
        return out;
    }

    public static Matrix MatrixAddition(Matrix m1, Matrix m2) {
        if (m1.getCols() != m2.getCols() || m1.getRows() != m2.getRows()) {
            throw new MatrixErrorException("Rows and Columns are not matching");
        }
        Matrix out = new Matrix(m1.getRows(), m1.getCols());
        for (int i = 0; i < m1.getData().length; i++) {
            for (int j = 0; j < m1.getData()[i].length; j++) {
                out.getData()[i][j] = m1.getData()[i][j] + m2.getData()[i][j];
            }
        }
        return out;
    }

    public double[][] getData() {
        return data;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public Matrix randomize() {
        for (int i = 0; i < getData().length; i++) {
            for (int j = 0; j < getData()[i].length; j++) {
                this.getData()[i][j] = (Math.random() * 2) - 1;
            }
        }
        return this;
    }

    public Matrix randomize(int num) {
        for (int i = 0; i < getData().length; i++) {
            for (int j = 0; j < getData()[i].length; j++) {
                this.getData()[i][j] = Math.floor(Math.random() * num);
            }
        }
        return this;
    }

    public Matrix add(Matrix mat) throws MatrixErrorException {
        if (mat.getCols() != getCols() || mat.getRows() != getRows()) {
            throw new MatrixErrorException("Rows and Columns are not matching");
        }
        for (int i = 0; i < getData().length; i++) {
            for (int j = 0; j < getData()[i].length; j++) {
                this.getData()[i][j] += mat.getData()[i][j];
            }
        }
        return this;
    }

    public Matrix elementMultipuly(Matrix mat) {
        if (mat.getCols() != getCols() || mat.getRows() != getRows()) {
            throw new MatrixErrorException("Rows and Columns are not matching");
        }
        for (int i = 0; i < getData().length; i++) {
            for (int j = 0; j < getData()[i].length; j++) {
                this.getData()[i][j] *= mat.getData()[i][j];
            }
        }
        return this;
    }

    public Matrix map(MatrixFuntion func) {
        for (int i = 0; i < getData().length; i++) {
            for (int j = 0; j < getData()[i].length; j++) {
                this.getData()[i][j] = func.funtion(this.getData()[i][j], i, j);
            }
        }
        return this;
    }

    public Matrix scaler(double d) {
        for (int i = 0; i < getData().length; i++) {
            for (int j = 0; j < getData()[i].length; j++) {
                this.getData()[i][j] *= d;
            }
        }
        return this;
    }

    public Matrix add(double d) {
        for (int i = 0; i < getData().length; i++) {
            for (int j = 0; j < getData()[i].length; j++) {
                this.getData()[i][j] += d;
            }
        }
        return this;
    }

    public void print() {
        String out = "";
        for (double[] ds : getData()) {
            out += "|";
            for (double d : ds) {
                out += d + ",";
            }
            out += "|\n";
        }
        System.out.println(out);
    }

}
