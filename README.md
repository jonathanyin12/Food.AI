# Food.AI
#### Easy calorie tracking using real-time object detection on Android.

<!-- TODO(b/124116863): Add app screenshot. -->

## Overview
This demo app allows users to quickly obtain calorie information using their camera. The object detection model utilizes a **[MobileNetV2 SSD](https://github.com/tensorflow/models/tree/master/research/object_detection)** architecture that was trained using transfer learning on 15 unique food classes from the **[Open Images v4](https://storage.googleapis.com/openimages/web/factsfigures_v4.html)** dataset.

Note: this app was built and modified from the **[TensorFlow Lite Object Detection Android Demo](https://github.com/tensorflow/examples/tree/d0046f3f18c66634395819a50ea6bda65f8bd0ac/lite/examples/object_detection/android)**.

## Overview



## Building in Android Studio
To build the pretrained demo in Android Studio, select "Open an existing Android Studio project" and navigate to the Food.AI/android directory. Then, connect a device and press 'run'.

## Model training
* Create a directory in Google Drive called `food_detection`.

* Add the **[training dataset](https://drive.google.com/file/d/11WC6XPp4kHGN1vEzl_ZRnFla99pxIs33/view?usp=sharing)** and **[label_map.pbtxt](https://drive.google.com/file/d/1R5KhvedYJ_7SzHGugl-Qbff0Gh9JAEjU/view?usp=sharing)** to `food_detection`.

* Open `Food_detect_train.ipynb` and follow the notebook instructions.

* To use the newly trained model, download `food_detect.tflite` from `model_checkpoints/tflite_model/` and move it to the _assets_ folder in Android Studio. It should replace the existing pretrained model.

## Custom food classes
#### Preparing the data
* Create a directory in Google Drive called `food_detection`.

* Use **[OIDv4 ToolKit](https://github.com/EscVM/OIDv4_ToolKit)** to download images and bounding box annotations for the desired classes.

* Change the classes in `OIDv4_ToolKit/classes.txt` accordingly. Then, zip the `OIDv4_ToolKit` folder and upload it to `food_detection`.

* Modify **[label_map.pbtxt](https://github.com/tensorflow/models/blob/master/research/object_detection/data/kitti_label_map.pbtxt)** to match the custom classes and upload it to`food_detection`.

#### Training the model
* When generating the TFRecords, set the flags to point to the location of the dataset. 

* Edit the number of classes in the model configuration file (`s = re.sub('90', 'NUM_CUSTOM_CLASSES', s)`).

#### Adding the model to Android Studio
* Download `food_detect.tflite` from `model_checkpoints/tflite_model/` and move it to the _assets_ folder in Android Studio. It should replace the existing pretrained model.

* Modify `food_labelmap.txt` accordingly. Make sure to keep `???` as the first line.

* Modify `calorie_info.txt` to reflect the custom classes








