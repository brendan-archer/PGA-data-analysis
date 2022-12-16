public class Golfers {
    private ST<String, double[]> golfers; // frequency of each char

    public Golfers() {
        ST<String, double[]> golfers = new ST<String, double[]>();
        this.golfers = golfers;
    }


    public void addGolfer(String name, double[] sgData) {
        // golfers = new ST<String, double[]>();
        if (!golfers.contains(name)) {
            golfers.put(name, sgData);
        }

        else {
            double[] current = golfers.get(name);
            double[] updated = new double[current.length];
            double averageCount = current[0];
            updated[0] = averageCount + 1;
            for (int i = 1; i < current.length; i++) {
                updated[i] = ((current[i] * averageCount) + sgData[i]) / (averageCount + 1);
            }
            golfers.put(name, updated);
        }
    }

    public void datainput() {
        String name = "Tiger Woods";
        double[] sgData = { 1, 0.24, 0.4, 2.19, -0.82, 1.76, 2.0 };
        double[] sgData2 = { 1, 0.5, 0, 3, -0.5, 2, 3 };
        addGolfer(name, sgData);
        addGolfer(name, sgData2);
        addGolfer("Tommy", sgData2);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (String key : golfers) {
            result.append('"' + key + '"' + ",");
            double[] sg = golfers.get(key);
            for (int i = 0; i < sg.length; i++) {
                result.append(sg[i] + ",");
            }
            result.append("\n");
        }
        return result.toString();
    }

    public static Golfers combine(Golfers num1, Golfers num2) {
        Golfers combined = new Golfers();
        for (String key : num1.golfers) {
            if (num2.golfers.contains(key)) {
                double[] current = num1.golfers.get(key);
                double[] second = num2.golfers.get(key);
                double[] updated = new double[current.length];
                for (int i = 1; i < current.length; i++) {
                    updated[i] = ((current[i] + second[i]) / 2);
                }
                combined.addGolfer(key, updated);
            }
        }
        return combined;
    }

    // returns new golfer that is the first minus the second
    public static Golfers difference(Golfers num1, Golfers num2) {
        Golfers difference = new Golfers();
        for (String key : num1.golfers) {
            if (num2.golfers.contains(key)) {
                double[] current = num1.golfers.get(key);
                double[] second = num2.golfers.get(key);
                double[] updated = new double[current.length];
                for (int i = 1; i < current.length; i++) {
                    updated[i] = ((current[i] - second[i]));
                }
                difference.addGolfer(key, updated);
            }
        }
        return difference;
    }

    public static void main(String[] args) {

    }
}
