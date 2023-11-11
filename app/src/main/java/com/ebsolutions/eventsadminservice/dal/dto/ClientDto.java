package com.ebsolutions.eventsadminservice.dal.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@Data
@DynamoDbBean
@Serdeable
@Slf4j
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto extends DatabaseDto {
    @NonNull
    private String name;
}
