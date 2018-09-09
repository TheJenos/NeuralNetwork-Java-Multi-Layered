/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Brain.Brain;
import Matrix.Matrix;

/**
 *
 * @author TheJenos
 */
public class Run {

    public static void main(String[] args) {
        int[] layers = {13, 12};
        Brain b = new Brain(2, layers, 1, "sigmoid");

        double[][][] mydata = {
            {{0, 1}, {1}},
            {{1, 0}, {1}},
            {{0, 0}, {0}},
            {{1, 1}, {0}},};

        for (int j = 0; j < 10000; j++) {
            for (int i = 0; i < mydata.length; i++) {
                b.train(mydata[i][0], mydata[i][1]);
            }
        }

        double[] ins = {0, 1};
        double[] out = b.feedforward(ins);
        System.out.println(out[0]);

        double[] ins1 = {1, 0};
        double[] out1 = b.feedforward(ins1);
        System.out.println(out1[0]);

        double[] ins2 = {0, 0};
        double[] out2 = b.feedforward(ins2);
        System.out.println(out2[0]);

        double[] ins3 = {1, 1};
        double[] out3 = b.feedforward(ins3);
        System.out.println(out3[0]);

    }
}
