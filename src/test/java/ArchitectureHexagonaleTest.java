import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(
        packages = "gaule.village.javanoramix",
        importOptions = {
                com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests.class,
                com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeArchives.class
        })
public class ArchitectureHexagonaleTest {

    @ArchTest
    public static final ArchRule apixNeDoitUtiliserQueDomainOuSpring =
            classes()
                    .that().resideInAPackage("..apix..")
                    .should().onlyDependOnClassesThat()
                    .resideInAnyPackage("..domain..", "java..", "jakarta..", "org.springframework..");

    @ArchTest
    public static final ArchRule domainNeDoitDépendreQueDuDomain =
            noClasses()
                    .that().resideInAPackage("..domain..")
                    .should().dependOnClassesThat()
                    .resideOutsideOfPackages("..domain..", "java..", "jakarta..");

    @ArchTest
    public static final ArchRule adaptersDoitDependreDeDomainInfrastructureEtSpring =
            classes()
                    .that().resideInAPackage("..adapters..")
                    .should().onlyDependOnClassesThat()
                    .resideInAnyPackage("..domain..", "..infrastructure..", "java..", "jakarta..", "org.springframework..");

    @ArchTest
    public static final ArchRule domainNeDoitPasUtiliserSpring =
            noClasses()
                    .that().resideInAPackage("..domain..")
                    .should().dependOnClassesThat()
                    .resideInAnyPackage("..org.springframework..");
}
