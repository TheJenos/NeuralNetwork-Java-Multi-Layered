/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matrix;

import java.io.Serializable;

/**
 *
 * @author TheJenos
 */
public class Matrix implements Serializable{

    private int rows, cols;
    private double[][] data;

    /**
     * This constructor use to create matrix
     * @param rows int of rows
     * @param cols int of column
     */
    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows][cols];
    }

    /**
     * this method returns new 1D array that holds data of matrix
     * @return double array
     */
    public Matrix copy() {
        Matrix out = new Matrix(getRows(), getCols());
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                out.getData()[i][j] = getData()[i][j];
            }
        }
        return out;
    }
    
    /**
     * this method returns new 1D array that holds data of matrix
     * @return double array
     */
    public double[] toArray() {
        double[] out = new double[getRows()];
        for (int i = 0; i < getRows(); i++) {
            out[i] = getData()[i][0];
        }
        return out;
    }

    /**
     * Convert 1D Array into a matrix
     * @param m1 input array
     * @return new matrix object
     */
    public static Matrix ArrayToMatrix(double[] m1) {
        Matrix out = new Matrix(m1.length, 1);
        for (int i = 0; i < m1.length; i++) {
            double d = m1[i];
            out.getData()[i][0] = d;
        }
        return out;
    }

    /**
     * This method returns new Matrix object that Transpose given matrix
     * @param m1 input matrix
     * @return new matrix object
     */
    public static Matrix MatrixTranspose(Matrix m1) {
        Matrix out = new Matrix(m1.getCols(), m1.getRows());
        for (int i = 0; i < m1.getRows(); i++) {
            for (int j = 0; j < m1.getCols(); j++) {
                out.getData()[j][i] = m1.getData()[i][j];
            }
        }
        return out;
    }

    /**
     * This method returns new Matrix object that mapped with given function
     * @param mat input matrix
     * @param func function
     * @return new matrix object
     */
    public static Matrix MatrixMapping(Matrix mat, MatrixFuntion func) {
        Matrix out = new Matrix(mat.getRows(), mat.getCols());
        for (int i = 0; i < mat.getData().length; i++) {
            for (int j = 0; j < mat.getData()[i].length; j++) {
                out.getData()[i][j] = func.funtion(mat.getData()[i][j], i, j);
            }
        }
        return out;
    }

    /**
     * This method returns Dot product of 2 matrices
     * @param m1 1st matrix 
     * @param m2 2st matrix
     * @return new matrix object
     */
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

    /**
     * This method returns Hadamard product of 2 matrices
     * @param m1 1st matrix 
     * @param m2 2st matrix
     * @return new matrix object
     */
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

    /**
     * This method use to subtract matrices
     * @param m1 1st matrix 
     * @param m2 2st matrix
     * @return new matrix object
     */
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

    /**
     * This method use to matrices addition 
     * @param m1 1st matrix 
     * @param m2 2st matrix
     * @return new matrix object
     */
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

    /**
     * This method returns matrix data.
     * @return double array
     */
    public double[][] getData() {
        return data;
    }

    /**
     * This method returns column count of matrix.
     * @return int value
     */
    public int getCols() {
        return cols;
    }

    /**
     * This method returns row count of matrix.
     * @return int value
     */
    public int getRows() {
        return rows;
    }

    /**
     * This randomize method use to add random values to between 1 and (-1)
     * @return Matrix object
     */
    public Matrix randomize() {
        for (int i = 0; i < getData().length; i++) {
            for (int j = 0; j < getData()[i].length; j++) {
                this.getData()[i][j] = (Math.random() * 2) - 1;
            }
        }
        return this;
    }

    /**
     * This randomize method use to add random values to between 0 and given int number
     * @param num range of random
     * @return
     */
    public Matrix randomize(int num) {
        for (int i = 0; i < getData().length; i++) {
            for (int j = 0; j < getData()[i].length; j++) {
                this.getData()[i][j] = Math.floor(Math.random() * num);
            }
        }
        return this;
    }

    /**
     * This method use to add values between 2 matrices 
     * @param mat second matrix
     * @return current matrix with new values
     * @throws MatrixErrorException
     */
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

    /**
     * This method returns Hadamard product of 2 matrices
     * @param mat second matrix
     * @return current matrix with new values
     */
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

    /**
     * This method returns current matrix with new values of MatrixFuntion
     * @param func MatrixFuntion
     * @return current matrix with new values
     */
    public Matrix map(MatrixFuntion func) {
        for (int i = 0; i < getData().length; i++) {
            for (int j = 0; j < getData()[i].length; j++) {
                this.getData()[i][j] = func.funtion(this.getData()[i][j], i, j);
            }
        }
        return this;
    }

    /**
     * This method returns current matrix, multiply with given number
     * @param d scaler value
     * @return current matrix with new values
     */
    public Matrix scaler(double d) {
        for (int i = 0; i < getData().length; i++) {
            for (int j = 0; j < getData()[i].length; j++) {
                this.getData()[i][j] *= d;
            }
        }
        return this;
    }

    /**
     * This method gives current matrix, addition with given number
     * @param d addition value
     * @return current matrix with new values
     */
    public Matrix add(double d) {
        for (int i = 0; i < getData().length; i++) {
            for (int j = 0; j < getData()[i].length; j++) {
                this.getData()[i][j] += d;
            }
        }
        return this;
    }

    /**
     * This method print structure and data of current matrix 
     */
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
