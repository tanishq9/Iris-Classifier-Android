package mlexpert.tanishqsaluja.mnist_digit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

public class MainActivity extends AppCompatActivity {

    private static final String MODEL_FILE = "file:///android_asset/frozen_model_iris.pb";

    private static final String INPUT_NODE = "Input";
    private static final String OUTPUT_NODE = "Output";

    private static final int[] INPUT_SIZE = {1, 4};


    private TensorFlowInferenceInterface inferenceInterface;

    static {
        System.loadLibrary("tensorflow_inference");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        inferenceInterface = new TensorFlowInferenceInterface();
        // We need to initialize our Tensorflow interface with the MODEL_FILE
        inferenceInterface.initializeTensorFlow(getAssets(), MODEL_FILE);
        System.out.println("Model Loaded Successfully");
        ImageView image = findViewById(R.id.img);
        image.setImageResource(R.drawable.collage);


        final EditText Num1 = findViewById(R.id.f1);
        final EditText Num2 = findViewById(R.id.f2);
        final EditText Num3 = findViewById(R.id.f3);
        final EditText Num4 = findViewById(R.id.f4);


        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                float num1 = Float.parseFloat(Num1.getText().toString());
                float num2 = Float.parseFloat(Num2.getText().toString());
                float num3 = Float.parseFloat(Num3.getText().toString());
                float num4 = Float.parseFloat(Num4.getText().toString());

                float[] inputFloats = {num1, num2, num3, num4};

                System.out.println(inputFloats.length);

                // Input node values in the interface
                inferenceInterface.fillNodeFloat(INPUT_NODE, INPUT_SIZE, inputFloats);

                // Run the interface to get the output for input node value
                inferenceInterface.runInference(new String[]{OUTPUT_NODE});

                // We read the float and store it in variable result
                float[] result = {0, 0, 0};
                inferenceInterface.readNodeFloat(OUTPUT_NODE, result);

                int class_id = argmax(result);

                ImageView image = (ImageView) findViewById(R.id.img);

                String s = "class";
                if (class_id == 0) {
                    s = "The species is likely Iris-setosa";
                    image.setImageResource(R.drawable.setosa);
                } else if (class_id == 1) {
                    s = "The species is likely Iris-versicolor";
                    image.setImageResource(R.drawable.versicolor);
                } else if (class_id == 2) {
                    s = "The species is likely Iris-virginica";
                    image.setImageResource(R.drawable.virginica);
                }

                final TextView textViewR = findViewById(R.id.result);
                textViewR.setText(s);
            }
        });
    }

    public static int argmax(float[] elems) {
        int bestIdx = -1;
        float max = -1000;
        for (int i = 0; i < elems.length; i++) {
            float elem = elems[i];
            if (elem > max) {
                max = elem;
                bestIdx = i;
            }
        }
        return bestIdx;
    }
}