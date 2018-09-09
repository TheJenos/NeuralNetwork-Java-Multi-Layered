/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activation;

/**
 *
 * @author TheJenos
 */
public class Sigmoid implements Matrix.MatrixFuntion{
    
    @Override
    public double funtion(double d, int row, int col) {
        return 1 / (1+Math.exp(-d));
    }
    
}
