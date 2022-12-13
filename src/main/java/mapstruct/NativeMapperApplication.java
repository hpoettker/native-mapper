package mapstruct;

import mapstruct.NativeMapperApplication.MapperHintsRegistrar;
import org.mapstruct.Mapper;
import org.reflections.Reflections;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;

import static java.util.function.Predicate.not;
import static org.springframework.aot.hint.MemberCategory.INVOKE_DECLARED_CONSTRUCTORS;

@SpringBootApplication
@ImportRuntimeHints(MapperHintsRegistrar.class)
public class NativeMapperApplication {

  public static void main(String[] args) {
    SpringApplication.run(NativeMapperApplication.class, args);
  }

  static class MapperHintsRegistrar implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
      var reflections = new Reflections(getClass().getPackageName());
      reflections.getTypesAnnotatedWith(Mapper.class).stream()
          .filter(not(Class::isInterface))
          .forEach(clazz -> hints.reflection().registerType(clazz, INVOKE_DECLARED_CONSTRUCTORS));
    }

  }

}
