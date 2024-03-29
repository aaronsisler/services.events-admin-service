package com.ebsolutions.eventsadminservice.dal.util;

import com.ebsolutions.eventsadminservice.dal.SortKeyType;
import software.amazon.awssdk.enhanced.dynamodb.Key;

public class KeyBuilder {
    public static Key build(String partitionKey, SortKeyType sortKeyType, String sortKey) {
        return Key.builder()
                .partitionValue(partitionKey)
                .sortValue(sortKeyType.name() + sortKey)
                .build();
    }
}
