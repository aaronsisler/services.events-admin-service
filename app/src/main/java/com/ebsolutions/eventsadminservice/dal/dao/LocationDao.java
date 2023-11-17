package com.ebsolutions.eventsadminservice.dal.dao;

import com.ebsolutions.eventsadminservice.config.DatabaseConstants;
import com.ebsolutions.eventsadminservice.dal.SortKeyType;
import com.ebsolutions.eventsadminservice.dal.dto.LocationDto;
import com.ebsolutions.eventsadminservice.dal.util.KeyBuilder;
import com.ebsolutions.eventsadminservice.exception.DataProcessingException;
import com.ebsolutions.eventsadminservice.model.Location;
import com.ebsolutions.eventsadminservice.util.MetricsStopWatch;
import com.ebsolutions.eventsadminservice.util.UniqueIdGenerator;
import io.micronaut.context.annotation.Prototype;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.text.MessageFormat;
import java.time.LocalDateTime;

@Slf4j
@Prototype
public class LocationDao {
    private final DynamoDbTable<LocationDto> ddbTable;

    public LocationDao(DynamoDbEnhancedClient enhancedClient) {
        this.ddbTable = enhancedClient.table(DatabaseConstants.DATABASE_TABLE_NAME, TableSchema.fromBean(LocationDto.class));
    }

    public Location read(String locationId) throws DataProcessingException {
        MetricsStopWatch metricsStopWatch = new MetricsStopWatch();
        try {
            Key key = KeyBuilder.build(locationId, SortKeyType.LOCATION);

            LocationDto locationDto = ddbTable.getItem(key);

            return locationDto == null
                    ? null
                    : Location.builder()
                    .clientId(locationDto.getPartitionKey())
                    .locationId(locationDto.getSortKey())
                    .name(locationDto.getName())
                    .createdOn(locationDto.getCreatedOn())
                    .lastUpdatedOn(locationDto.getLastUpdatedOn())
                    .build();
        } catch (Exception e) {
            log.error("ERROR::{}", this.getClass().getName(), e);
            throw new DataProcessingException(MessageFormat.format("Error in {0}", this.getClass().getName()), e);
        } finally {
            metricsStopWatch.logElapsedTime(MessageFormat.format("{0}::{1}", this.getClass().getName(), "read"));
        }
    }

    public Location create(Location location) {
        MetricsStopWatch metricsStopWatch = new MetricsStopWatch();
        try {
            LocalDateTime now = LocalDateTime.now();

            LocationDto locationDto = LocationDto.builder()
                    .partitionKey(location.getClientId())
                    .sortKey(UniqueIdGenerator.generate())
                    .name(location.getName())
                    .createdOn(now)
                    .lastUpdatedOn(now)
                    .build();

            ddbTable.updateItem(locationDto);

            return Location.builder()
                    .clientId(locationDto.getPartitionKey())
                    .locationId(locationDto.getSortKey())
                    .name(locationDto.getName())
                    .createdOn(locationDto.getCreatedOn())
                    .lastUpdatedOn(locationDto.getLastUpdatedOn())
                    .build();
        } catch (Exception e) {
            log.error("ERROR::{}", this.getClass().getName(), e);
            throw new DataProcessingException(MessageFormat.format("Error in {0}", this.getClass().getName()), e);
        } finally {
            metricsStopWatch.logElapsedTime(MessageFormat.format("{0}::{1}", this.getClass().getName(), "create"));
        }
    }

    /**
     * This will replace the entire database object with the input location
     *
     * @param location the object to replace the current database object
     */
    public Location update(Location location) {
        MetricsStopWatch metricsStopWatch = new MetricsStopWatch();
        try {
            LocationDto locationDto = LocationDto.builder()
                    .partitionKey(location.getClientId())
                    .sortKey(location.getLocationId())
                    .name(location.getName())
                    .createdOn(location.getCreatedOn())
                    .lastUpdatedOn(LocalDateTime.now())
                    .build();

            ddbTable.putItem(locationDto);

            return Location.builder()
                    .clientId(locationDto.getPartitionKey())
                    .locationId(locationDto.getSortKey())
                    .name(locationDto.getName())
                    .createdOn(locationDto.getCreatedOn())
                    .lastUpdatedOn(locationDto.getLastUpdatedOn())
                    .build();
        } catch (Exception e) {
            log.error("ERROR::{}", this.getClass().getName(), e);
            throw new DataProcessingException(MessageFormat.format("Error in {0}", this.getClass().getName()), e);
        } finally {
            metricsStopWatch.logElapsedTime(MessageFormat.format("{0}::{1}", this.getClass().getName(), "update"));
        }
    }

    public void delete(String locationId) {
        MetricsStopWatch metricsStopWatch = new MetricsStopWatch();
        try {
            Key key = KeyBuilder.build(locationId, SortKeyType.LOCATION);

            ddbTable.deleteItem(key);
        } catch (Exception e) {
            log.error("ERROR::{}", this.getClass().getName(), e);
            throw new DataProcessingException(MessageFormat.format("Error in {0}", this.getClass().getName()), e);
        } finally {
            metricsStopWatch.logElapsedTime(MessageFormat.format("{0}::{1}", this.getClass().getName(), "delete"));
        }
    }
}