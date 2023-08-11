/******************************************************************************

Liudmila Chizhikova (c)
автор Чижикова Людмила Андреевна

*******************************************************************************/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Задаем данные для обучения
        List<Iris> trainingData = new ArrayList<>();
        trainingData.add(new Iris(5.1, 3.5, 1.4, 0.2, "setosa"));
        trainingData.add(new Iris(4.9, 3.0, 1.4, 0.2, "setosa"));
        trainingData.add(new Iris(5.8, 2.6, 4.0, 1.2, "versicolor"));
        trainingData.add(new Iris(6.7, 3.0, 5.2, 2.3, "virginica"));
        // ...

        // Создаем модель машинного обучения
        MachineLearningModel model = new MachineLearningModel();

        // Обучаем модель
        model.train(trainingData);

        // Задаем новые данные для предсказания
        //сканирование ввода данных пользователя для обучения
      
        Scanner scan = new Scanner(System.in);
        double ML_user[] = new double [4];
        System.out.println(" Введите числа");

        for (int i = 0; i < ML_user.length; i++) {
            if (scan.hasNextDouble()) {
                ML_user[i] = scan.nextDouble();
            }
        }
        
        double firstnum  = ML_user[0];
        double secondnum = ML_user[1];
        double thirdnum  = ML_user[2];
        double fourthnum = ML_user[3];
        
        Iris newData = new Iris(firstnum, secondnum, thirdnum, fourthnum, "");
        
        
        
        // Предсказываем класс новых данных
        String predictedClass = model.predict(newData);

        System.out.println("Самый вероятный класс: " + predictedClass);
    }

    // Класс для представления данных ириса
    static class Iris {
        double sepalLength;
        double sepalWidth;
        double petalLength;
        double petalWidth;
        String irisClass;

        public Iris(double sepalLength, double sepalWidth, double petalLength, double petalWidth, String irisClass) {
            this.sepalLength = sepalLength;
            this.sepalWidth = sepalWidth;
            this.petalLength = petalLength;
            this.petalWidth = petalWidth;
            this.irisClass = irisClass;
        }
    }

    // Класс модели машинного обучения
    static class MachineLearningModel {
        List<Iris> trainingData;

        public void train(List<Iris> trainingData) {
            
            this.trainingData = trainingData;
        }

        public String predict(Iris newData) {
            double minDistance = Double.MAX_VALUE;
            String predictedClass = "";

            for (Iris iris : trainingData) {
                double distance = calculateDistance(newData, iris);
                if (distance < minDistance) {
                    minDistance = distance;
                    predictedClass = iris.irisClass;
                }
            }

            return predictedClass;
        }

        private double calculateDistance(Iris newData, Iris iris) {
            double sepalLengthDiff = newData.sepalLength - iris.sepalLength;
            double sepalWidthDiff = newData.sepalWidth - iris.sepalWidth;
            double petalLengthDiff = newData.petalLength - iris.petalLength;
            double petalWidthDiff = newData.petalWidth - iris.petalWidth;

            return Math.sqrt(sepalLengthDiff * sepalLengthDiff + sepalWidthDiff * sepalWidthDiff +
                    petalLengthDiff * petalLengthDiff + petalWidthDiff * petalWidthDiff);
                    
        }}
}
