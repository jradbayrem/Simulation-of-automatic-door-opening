import com.googlecode.javacv.cpp.opencv_core;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_contrib.*;
import static com.googlecode.javacv.cpp.opencv_contrib.createFisherFaceRecognizer;
import static com.googlecode.javacv.cpp.opencv_contrib.createLBPHFaceRecognizer;


import java.io.File;
import java.io.FilenameFilter;

public class OpenCVFaceRecognizer {
    public static void main(String[] args) {
        String trainingDir = "C:/Users/BAYREM/Work/J2EE Work/OpenCVJavaCVFisherFaces/TrainingDirectory";
        IplImage testImage = cvLoadImage("123.jpg");

        File root = new File(trainingDir);

        FilenameFilter pngFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".png");
            }
        };

        File[] imageFiles = root.listFiles(pngFilter);

        MatVector images = new MatVector(imageFiles.length);

        int[] labels = new int[imageFiles.length];
        String [] persons= new String[imageFiles.length];
        int counter = 0;
        int label;

        IplImage img;
        IplImage grayImg;

        for (File image : imageFiles) {
            img = cvLoadImage(image.getAbsolutePath());
persons[counter]=image.getName();
            label = Integer.parseInt(image.getName().split("\\-")[0]);

            grayImg = IplImage.create(img.width(), img.height(), IPL_DEPTH_8U, 1);

            cvCvtColor(img, grayImg, CV_BGR2GRAY);

            images.put(counter, grayImg);

            labels[counter] = label;

            counter++;
        }

        IplImage greyTestImage = IplImage.create(testImage.width(), testImage.height(), IPL_DEPTH_8U, 1);

        FaceRecognizer faceRecognizer = createFisherFaceRecognizer();
        // FaceRecognizer faceRecognizer = createEigenFaceRecognizer();
        // FaceRecognizer faceRecognizer = createLBPHFaceRecognizer()

        faceRecognizer.train(images, labels);

        cvCvtColor(testImage, greyTestImage, CV_BGR2GRAY);

        int predictedLabel = faceRecognizer.predict(greyTestImage);

        int indexOfChar_=persons[ predictedLabel-1].indexOf("_");
        int indexOfCharDot=persons[ predictedLabel-1].indexOf(".");
        System.out.println("Nom: "+persons[ predictedLabel-1].substring(2,indexOfChar_));
        System.out.println("Prénom: "+persons[ predictedLabel-1].substring(indexOfChar_+1,indexOfCharDot));
        System.out.println("La personne est autorisé à entrer....");

    }
}