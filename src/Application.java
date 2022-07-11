import mappers.OscarDataMapper;
import models.OscarData;
import services.OscarService;
import utils.FileUtil;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Application {
    public static void main(String[] args) throws IOException {
        var femaleOscars = new FileUtil<OscarData>("oscar_age_female.csv");
        var maleOscars = new FileUtil<OscarData>("oscar_age_male.csv");

        var oscarData = Stream.of(
                femaleOscars.readFile(new OscarDataMapper())
                , maleOscars.readFile(new OscarDataMapper())
            )
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

        OscarService.setOscarData(oscarData);

        var mostYoung = OscarService.getMostYoung();

        mostYoung.ifPresent(
                r -> System.out.printf(
                        "O(A) ator/atriz mais jovem a ser premiado(a) foi %s, que possuía %d anos em %d. %n"
                        , r.getName()
                        , r.getAge()
                        , r.getYear()
                )
        );

        var mostAwarded = OscarService.getMostAwarded();

        mostAwarded.ifPresent(
                r -> System.out.printf(
                        "O(A) ator/atriz mais premiado(a) é %s, que recebeu %d prêmios. %n"
                        , r.getKey()
                        , r.getValue()
                )
        );

        var youthMostAwarded = OscarService.getYouthMostAwarded();

        youthMostAwarded.ifPresent(
                r -> System.out.printf(
                        "O(A) jovem ator/atriz (entre 18 e 24 anos) mais premiado(a) é %s, que recebeu %d prêmio(s). %n"
                        , r.getKey()
                        , r.getValue()
                )
        );
    }
}
