package com.ebsolutions.eventsadminservice.dal.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.time.LocalDateTime;

@Data
@DynamoDbBean
@Serdeable
@Slf4j
@SuperBuilder
@NoArgsConstructor
public abstract class DatabaseDto {
    @NonNull
    @Getter(onMethod_ = @DynamoDbPartitionKey)
    private String partitionKey;

    @NonNull
    @Getter(onMethod_ = @DynamoDbSortKey)
    private String sortKey;

    @NonNull
    private String name;

    private LocalDateTime createdOn;

    private LocalDateTime lastUpdatedOn;
    /**
     * Date that record is no longer to remain in database
     * Must be in epoch seconds
     * i.e. should not be considered for items past expiryTime
     */
    private long expiryTime;
}
