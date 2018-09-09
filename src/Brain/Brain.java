/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brain;

import Activation.Dsigmoid;
import Activation.Mutate;
import Activation.Sigmoid;
import Activation.Tanh;
import Matrix.Matrix;
import Matrix.MatrixFuntion;
import java.io.Serializable;

/**
 *
 * @author TheJenos
 */
public class Brain implements Serializable {

    private int inputs, outputs;
    private int[] hidden_layers;
    private Matrix[] weights;
    private Matrix[] biases;
    private double learning_rate = 0.1;
    private MatrixFuntion activation;
    private MatrixFuntion deactivation;

    /**
     * This creates an object of Brain with custom learning_rate and activation
     *
     * @param inputs input value count
     * @param hidden_layers layer as array of int
     * @param outputs output value count
     * @param learning_rate learning_rate double
     * @param activation activation type (sigmoid,tanh)
     */
    public Brain(Brain b) {
        this.inputs = b.inputs;
        this.outputs = b.outputs;
        this.hidden_layers = b.hidden_layers;
        this.weights = new Matrix[b.weights.length];
        for (int i = 0; i < b.weights.length; i++) {
            Matrix weight = b.weights[i];
            this.weights[i] = weight.copy();
        }
        this.activation = b.activation;
        this.deactivation = b.deactivation;
        this.learning_rate = b.learning_rate;
    }

    /**
     * This creates an object of Brain with custom learning_rate and activation
     *
     * @param inputs input value count
     * @param hidden_layers layer as array of int
     * @param outputs output value count
     * @param learning_rate learning_rate double
     * @param activation activation type (sigmoid,tanh)
     */
    public Brain(int inputs, int[] hidden_layers, int outputs, double learning_rate, String activation) {
        this(inputs, hidden_layers, outputs, activation);
        this.learning_rate = learning_rate;
    }

    /**
     * This creates an object of Brain with custom learning_rate
     *
     * @param inputs input value count
     * @param hidden_layers layer as array of int
     * @param outputs output value count
     * @param learning_rate learning_rate double
     */
    public Brain(int inputs, int[] hidden_layers, int outputs, double learning_rate) {
        this(inputs, hidden_layers, outputs);
        this.learning_rate = learning_rate;
    }

    /**
     * This creates an object of Brain with custom activation
     *
     * @param inputs input value count
     * @param hidden_layers layer as array of int
     * @param outputs output value count
     * @param activation activation type (sigmoid,tanh)
     */
    public Brain(int inputs, int[] hidden_layers, int outputs, String activation) {
        this(inputs, hidden_layers, outputs);
        if (activation.equalsIgnoreCase("tanh")) {
            this.activation = new Tanh();
            this.deactivation = new Tanh();
        } else {
            this.activation = new Sigmoid();
            this.deactivation = new Dsigmoid();
        }
    }

    /**
     * This creates an object of Brain
     *
     * @param inputs input value count
     * @param hidden_layers layer as array of int
     * @param outputs output value count
     */
    public Brain(int inputs, int[] hidden_layers, int outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
        this.hidden_layers = hidden_layers;
        this.weights = new Matrix[hidden_layers.length + 1];
        this.biases = new Matrix[hidden_layers.length + 1];
        for (int i = 0; i < this.weights.length; i++) {
            if (i == 0) {
                this.weights[i] = new Matrix(this.hidden_layers[i], this.inputs);
            } else if (i == (this.weights.length - 1)) {
                this.weights[i] = new Matrix(this.outputs, this.hidden_layers[i - 1]);
            } else {
                this.weights[i] = new Matrix(this.hidden_layers[i], this.hidden_layers[i - 1]);
            }
            this.weights[i].randomize();
        }
        for (int i = 0; i < this.biases.length; i++) {
            if (i == (this.biases.length - 1)) {
                this.biases[i] = new Matrix(this.outputs, 1);
            } else {
                this.biases[i] = new Matrix(this.hidden_layers[i], 1);
            }
            this.biases[i].randomize();
        }
        this.activation = new Sigmoid();
        this.deactivation = new Dsigmoid();
    }

    /**
     * This method gives the guess of given input values
     *
     * @param input_array double array
     * @return output guess values
     */
    public double[] feedforward(double[] input_array) {
        Matrix input = Matrix.ArrayToMatrix(input_array);
        Matrix[] hidden_vals = new Matrix[this.hidden_layers.length];
        for (int i = 0; i < hidden_vals.length; i++) {
            if (i == 0) {
                hidden_vals[i] = Matrix.MatrixDotMultiply(this.weights[i], input);
            } else {
                hidden_vals[i] = Matrix.MatrixDotMultiply(this.weights[i], hidden_vals[i - 1]);
            }
            hidden_vals[i].add(this.biases[i]);
            hidden_vals[i].map(this.activation);
        }
        Matrix output = Matrix.MatrixDotMultiply(this.weights[this.weights.length - 1], hidden_vals[hidden_vals.length - 1]);
        output.add(this.biases[this.biases.length - 1]);
        output.map(this.activation);
        return output.toArray();
    }

    /**
     * This method use to train the brain object
     *
     * @param input_array random inputs
     * @param target_array real output values of that inputs
     */
    public void train(double[] input_array, double[] target_array) {

        // Turn input and target arrays into matrices
        Matrix input = Matrix.ArrayToMatrix(input_array);
        Matrix target = Matrix.ArrayToMatrix(target_array);

        Matrix[] hidden_vals = new Matrix[this.hidden_layers.length];
        for (int i = 0; i < hidden_vals.length; i++) {
            if (i == 0) {
                hidden_vals[i] = Matrix.MatrixDotMultiply(this.weights[i], input);
            } else {
                hidden_vals[i] = Matrix.MatrixDotMultiply(this.weights[i], hidden_vals[i - 1]);
            }
            hidden_vals[i].add(this.biases[i]);
            hidden_vals[i].map(this.activation);
        }
        Matrix outputs = Matrix.MatrixDotMultiply(this.weights[this.weights.length - 1], hidden_vals[hidden_vals.length - 1]);
        outputs.add(this.biases[this.biases.length - 1]);
        outputs.map(this.activation);

        Matrix[] weights_trans = new Matrix[this.weights.length];
        Matrix[] weights_delta = new Matrix[this.weights.length];

        Matrix output_error = Matrix.MatrixSubtract(target, outputs);
        Matrix last_hidden_trans = Matrix.MatrixTranspose(hidden_vals[hidden_vals.length - 1]);
        Matrix output_gradient = Matrix.MatrixMapping(outputs, this.deactivation)
                .elementMultipuly(output_error)
                .scaler(learning_rate);
        weights_delta[weights_delta.length - 1] = Matrix.MatrixDotMultiply(output_gradient, last_hidden_trans);

        this.weights[weights.length - 1].add(weights_delta[weights_delta.length - 1]);
        this.biases[biases.length - 1].add(output_gradient);

        Matrix[] hidden_errors = new Matrix[this.hidden_layers.length];
        Matrix[] hidden_gradient = new Matrix[this.hidden_layers.length];
        for (int i = (hidden_errors.length - 1); i >= 0; i--) {
            weights_trans[i + 1] = Matrix.MatrixTranspose(this.weights[i + 1]);
            if (i == (hidden_errors.length - 1)) {
                hidden_errors[i] = Matrix.MatrixDotMultiply(weights_trans[i + 1], output_error);
            } else {
                hidden_errors[i] = Matrix.MatrixDotMultiply(weights_trans[i + 1], hidden_errors[i + 1]);
            }
            hidden_gradient[i] = Matrix.MatrixMapping(hidden_vals[i], this.deactivation)
                    .elementMultipuly(hidden_errors[i])
                    .scaler(learning_rate);
            Matrix last_vals_transpose;
            if (i == 0) {
                last_vals_transpose = Matrix.MatrixTranspose(input);
            } else {
                last_vals_transpose = Matrix.MatrixTranspose(hidden_vals[i - 1]);
            }
            weights_delta[i] = Matrix.MatrixDotMultiply(hidden_gradient[i], last_vals_transpose);
            this.weights[i].add(weights_delta[i]);
            this.biases[i].add(hidden_gradient[i]);
        }

    }

    // This is how we adjust weights ever so slightly
    public void mutate() {
        for (int i = 0; i < this.weights.length; i++) {
            this.weights[i].map(new Mutate());
        }
    }

    public Brain copy() {
        return new Brain(this);
    }

    public void print() {
        for (Matrix weight : weights) {
            weight.print();
        }
        System.out.println("---------------");
        for (Matrix biase : biases) {
            biase.print();
        }
    }

}
