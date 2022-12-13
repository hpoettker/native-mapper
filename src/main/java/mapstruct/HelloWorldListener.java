package mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import static org.mapstruct.ReportingPolicy.ERROR;

@Component
public class HelloWorldListener implements ApplicationListener<ApplicationReadyEvent> {

  private static final Logger LOG = LoggerFactory.getLogger(HelloWorldListener.class);

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    var mapper = Mappers.getMapper(RecordMapper.class);
    var mapped = mapper.map(new SourceRecord("Hello World!"));
    LOG.info(mapped.string());
  }

}

record SourceRecord(String string) {
}

record TargetRecord(String string) {
}

@Mapper(unmappedTargetPolicy = ERROR)
interface RecordMapper {

  TargetRecord map(SourceRecord record);

}
