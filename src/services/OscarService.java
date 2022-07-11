package services;

import models.OscarData;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class OscarService {
    private static List<OscarData> oscarData;

    public static void setOscarData(List<OscarData> oscarData) {
        OscarService.oscarData = oscarData;
    }

    // Ator ou atriz mais jovem a ser premiado
    public static Optional<OscarData> getMostYoung() {
        return oscarData.stream()
                .min(Comparator.comparingInt(OscarData::getAge));
    }

    // Ator ou atriz mais premiado
    public static Optional<Map.Entry<String, Long>> getMostAwarded() {
        return oscarData.stream()
                .collect(Collectors.groupingBy(OscarData::getName, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue());
    }

    // Jovem ator/atriz (entre 18 e 24 anos) mais premiado
    public static Optional<Map.Entry<String, Long>> getYouthMostAwarded() {
        return oscarData.stream()
                .filter(p -> p.getAge() >= 18 && p.getAge() <= 24)
                .collect(Collectors.groupingBy(OscarData::getName, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue());
    }


    public static Optional<OscarData> searchActor(String name) {
        for (OscarData award : oscarData) {
            if (award.getName().equals(name)) {
                return Optional.of(award);
            }
        }
        return Optional.empty();
    }

}
