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
public class Mutate implements Matrix.MatrixFuntion {

    @Override
    public double funtion(double d, int row, int col) {
        if ((Math.random() * 1) < 0.1) {
            double offset = (Math.round(Math.random() * 2) - 1) / 10;
            double newx = d + offset;
            return newx;
        } else {
            return d;
        }
    }

}
