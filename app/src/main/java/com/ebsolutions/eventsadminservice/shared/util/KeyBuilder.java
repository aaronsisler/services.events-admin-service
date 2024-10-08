package com.ebsolutions.eventsadminservice.shared.util;

import com.ebsolutions.eventsadminservice.shared.SortKeyType;
import software.amazon.awssdk.enhanced.dynamodb.Key;

public class KeyBuilder {
  public static Key.Builder builder(String partitionKey, SortKeyType sortKeyType, String sortKey) {
    return Key.builder()
        .partitionValue(partitionKey)
        .sortValue(sortKeyType.name() + sortKey);
  }

  public static Key build(String partitionKey, SortKeyType sortKeyType, String sortKey) {
    return builder(partitionKey, sortKeyType, sortKey).build();
  }
}
