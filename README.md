# Iris Classifier Android

#### Step 1 : Training and Exporting model

Dataset was taken from : https://archive.ics.uci.edu/ml/machine-learning-databases/iris/iris.data

I have trained the neural network using Tensorflow , and have used Adam Optimizer to minimize the cost.

Softmax as used as activation function for the output layer as it works quite well for multi-class classification(3 classes!) 

After the model has been trained , we save the model and freeze all the parameters.Now ,the model is ready for inference.

#### Step 2 : Modify the gradle 

Mention the directory in which our Java libraries for tensorflow(Used Build version #44) are present. 

#### Step 3 : Create an assets folder and paste the model files.

These files are the .pb files that we formed using our model.

#### Step 4 : Importing TF and other libraries in Android Studio 

It includes TensorFlowInferenceInterface , which is used for feeding input into the TF graph.

#### Step 5 : Load the model and Fetch the input in TF format

Fill the input node and make inference.

#### Step 6 : Build a basic UI 

It will be used for accessing data and displaying results.

