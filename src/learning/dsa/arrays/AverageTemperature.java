package learning.dsa.arrays;

class AverageTemperature {

    float[] temperatures;
    float sumOfTemperature;

    public AverageTemperature(int noOfDays) {
        temperatures = new float[noOfDays];
    }

    public void addTemperature(int index, float temperature) {
        temperatures[index] = temperature;
        sumOfTemperature += temperatures[index];
    }

    public Float getAverageTemperature() {
        return sumOfTemperature/temperatures.length;
    }
}
