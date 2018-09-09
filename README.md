# NeuralNetwork-Java-Multi-Layered
its a java based NeuralNetwork

## Getting Started

it's simple to implement. just add the jar file to your project. That's all.

## Running
### How it's work
```
//layers and perceptron count  
int[] layers = {13,12};

//brain object 
Brain b = new Brain(2, layers, 1);

//known datas
double[][][] mydata = {
    {{0, 1}, {1}},
    {{1, 0}, {1}},
    {{0, 0}, {0}},
    {{1, 1}, {0}},
};

//train the NeuralNetwork to against weights
for (int j = 0; j < 10000; j++) {
    for (int i = 0; i < mydata.length; i++) {
      b.train(mydata[i][0], mydata[i][1]);
    }
}

//results
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

```
## Built With

* Java8 - language

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
