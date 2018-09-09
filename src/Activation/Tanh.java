/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activation;

import Matrix.MatrixFuntion;

/**
 *
 * @author TheJenos
 */
public class Tanh implements MatrixFuntion{

    @Override
    public double funtion(double d, int row, int col) {
        return Math.tanh(d);
    }
    
}
