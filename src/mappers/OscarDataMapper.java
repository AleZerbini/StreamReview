package mappers;

import models.OscarData;

import java.util.Arrays;
import java.util.function.Function;

public class OscarDataMapper implements Function<String, OscarData> {

    @Override
    public OscarData apply(String line) {
        var lineData = Arrays.stream(line.split(";"))
                .map(String::trim)
                .toArray(String[]::new);

        // lineData[0] = Index (not used)
        var year = Integer.parseInt(lineData[1]);
        var age = Integer.parseInt(lineData[2]);
        var name = lineData[3];
        var movie = lineData[4];

        return new OscarData(year, age, name, movie);
    }
}
