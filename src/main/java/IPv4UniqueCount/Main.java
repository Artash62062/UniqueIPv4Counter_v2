package IPv4UniqueCount;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        String filePath="/home/artash/Desktop/TestFiles/TestFile";// Write Yor File Path
        Map <Long,Integer> ipMap = putIpsIntoMapWithCounts(filePath);
        System.out.println(countUniqueIps(ipMap));
        long endTime = System.nanoTime();
        System.out.println("Took "+((endTime - startTime)/1000000000) + " s");
    }

    /* 1.5m Distance */

    public static long ipToLong(String ipAddress) {
        String[] ipAddressInArray = ipAddress.split("\\.");
        long result = 0;
        for (int i = 0; i < ipAddressInArray.length; i++) {
            int power = 3 - i;
            int ip = Integer.parseInt(ipAddressInArray[i]);
            result += ip * Math.pow(256, power);
        }
        return result;
    }

    public static int countUniqueIps(Map<Long,Integer> ipMap) {
        int counter = 0;
        for (Map.Entry<Long,Integer> entry : ipMap.entrySet()) {
            int repeatCountOfIps = entry.getValue();
            if (repeatCountOfIps == 1) {
                counter++;
            }
        }
        return counter;
    }

    public static  Map<Long,Integer> putIpsIntoMapWithCounts (String filePath) {
        Map <Long,Integer> ipMap = new HashMap <Long, Integer> ();
        try {
            BufferedReader reader;
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            Long ipLongFormat;
            while (line!= null) {
                ipLongFormat = ipToLong(line);
                if(!ipMap.containsKey(ipLongFormat)) {
                    ipMap.put(ipLongFormat, 1);
                } else {
                    ipMap.put(ipLongFormat,ipMap.get(ipLongFormat)+1);
                }
                line = reader.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return ipMap;
    }
}
