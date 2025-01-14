package org.pavlov.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Entities manipulation Api",
                description = "API система с добавлением сущностей сотрудников, отедлов и задач",
                version = "1.0.0",
                contact = @Contact(
                        name = "Pavlov Kirill",
                        email = "kiryl.paulau@softclub.by"
                )
        )
)
public class OpenApiConfig {
}