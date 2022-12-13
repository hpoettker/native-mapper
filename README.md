# Native Mapstruct Spring Boot Demo Application

The application instantiates a mapper with `Mappers::getMapper`, and produces
the native hint to make it work in a native image with classpath scanning
during AOT processing.
