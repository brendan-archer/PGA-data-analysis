public class DataInput {
    public static void main(String[] args) {
        // String name = "Tiger Woods";
        // double[] sgData = { 1, 0.24, 0.4, 2.19, -0.82, 1.76, 2.0 };
        // double[] sgData2 = { 1, 0.5, 0, 3, -0.5, 2, 3 };
        // Golfers current = new Golfers();
        // current.addGolfer(name, sgData);
        // current.addGolfer(name, sgData2);
        // current.addGolfer("Tommy", sgData2);
        // StdOut.println(current.toString());

        // String info = data.readAll();
        // StdOut.println("all: " + info);

        Golfers usOpen = new Golfers();
        Golfers pgaChamp = new Golfers();
        Golfers baseline = new Golfers();
        Golfers highPurse = new Golfers();

        In data = new In("All SG + Tournament Data + Purse Data.csv");
        while (!data.isEmpty()) {
            String info = data.readLine();
            // finds name and sg data
            int begOfName = 0;
            int endOfName = 0;
            // sg putt
            int endPutt = 0;
            // around green
            int endArG = 0;
            // app
            int endApp = 0;
            int endOTT = 0;
            int endT2G = 0;
            int endTot = 0;
            int endTorn = 0;
            int cCount = 0;
            for (int i = 0; i < info.length(); i++) {
                if (info.charAt(i) == ',') {
                    if (cCount == 1) endOfName = i;
                    if (cCount == 2) endPutt = i;
                    if (cCount == 3) endArG = i;
                    if (cCount == 4) endApp = i;
                    if (cCount == 5) endOTT = i;
                    if (cCount == 6) endT2G = i;
                    if (cCount == 7) endTot = i;
                    if (cCount == 8) endTorn = i;
                    cCount += 1;
                }
            }
            String name = info.substring(begOfName + 1, endOfName);
            String sgPutt = info.substring(endOfName + 1, endPutt);
            String sgArG = info.substring(endPutt + 1, endArG);
            String sgApp = info.substring(endArG + 1, endApp);
            String sgOTT = info.substring(endApp + 1, endOTT);
            String sgT2G = info.substring(endOTT + 1, endT2G);
            String sgTot = info.substring(endT2G + 1, endTot);
            String tournament = info.substring(endTot + 1, endTorn);
            String purse = info.substring(endTorn + 1);
            double purseD = Double.parseDouble(purse);

            if (!sgPutt.contentEquals("NA")) {
                double sgPutti = Double.parseDouble(sgPutt);
                double sgArgi = Double.parseDouble(sgArG);
                double sgAppi = Double.parseDouble(sgApp);
                double sgOTTi = Double.parseDouble(sgOTT);
                double sgT2Gi = Double.parseDouble(sgT2G);
                double sgToti = Double.parseDouble(sgTot);
                double[] sgData = { 1.0, sgPutti, sgArgi, sgAppi, sgOTTi, sgT2Gi, sgToti };
                baseline.addGolfer(name, sgData);
                if (purseD >= 10)
                    highPurse.addGolfer(name, sgData);
                if (tournament.contentEquals("U.S. Open"))
                    usOpen.addGolfer(name, sgData);
                if (tournament.contentEquals("PGA Championship"))
                    pgaChamp.addGolfer(name, sgData);
            }
        }
        // golfers SG data in majors
        Golfers majors = Golfers.combine(usOpen, pgaChamp);
        // gives the amount above baseline players play in majors
        Golfers differenceMaj = Golfers.difference(majors, baseline);
        Golfers differenceinUS = Golfers.difference(usOpen, baseline);
        Golfers differenceinPGA = Golfers.difference(pgaChamp, baseline);

        // gives the amount above baseline players play in high purse events
        Golfers differenceHighPurse = Golfers.difference(highPurse, baseline);

        StdOut.println(differenceHighPurse);
        // StdOut.println(name);
        // StdOut.println(sgPutt);
        // StdOut.println(sgArG);
        // StdOut.println(sgApp);
        // StdOut.println(sgOTT);
        // StdOut.println(sgT2G);
        // StdOut.println(sgTot);

    }
}
