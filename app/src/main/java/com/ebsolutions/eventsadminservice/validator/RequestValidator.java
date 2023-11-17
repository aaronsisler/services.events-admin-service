package com.ebsolutions.eventsadminservice.validator;

import com.ebsolutions.eventsadminservice.controller.RequestMethod;
import com.ebsolutions.eventsadminservice.model.Client;
import com.ebsolutions.eventsadminservice.model.Location;

public class RequestValidator {
    public static boolean isClientValid(RequestMethod requestMethod, Client client) {
        return switch (requestMethod) {
            case PUT -> RequestValidator.isClientUpdateValid(client);
            case GET, POST, DELETE -> true;
        };
    }

    public static boolean isLocationValid(RequestMethod requestMethod, Location location) {
        return switch (requestMethod) {
            case PUT -> RequestValidator.isLocationUpdateValid(location);
            case GET, POST, DELETE -> true;
        };
    }

    private static boolean isClientUpdateValid(Client client) {
        if (StringValidator.isBlank(client.getClientId())) {
            return false;
        }

        if (client.getCreatedOn() == null) {
            return false;
        }

        return DateValidator.isBeforeNow(client.getCreatedOn());
    }

    private static boolean isLocationUpdateValid(Location location) {
        if (StringValidator.isBlank(location.getClientId())) {
            return false;
        }

        if (location.getCreatedOn() == null) {
            return false;
        }

        return DateValidator.isBeforeNow(location.getCreatedOn());
    }
//    public static boolean isEventValid(RequestMethod requestMethod, Event event) {
//        return switch (requestMethod) {
//            case POST -> RequestValidator.isEventValid(event);
//            case GET, PUT, DELETE -> true;
//        };
//    }
//
//    public static boolean isWorkshopValid(RequestMethod requestMethod, Workshop workshop) {
//        return switch (requestMethod) {
//            case POST -> RequestValidator.isPostBaseEventValid(workshop);
//            case GET, PUT, DELETE -> true;
//        };
//    }
//
//    private static boolean isPostBaseEventValid(BaseEvent baseEvent) {
//        if (baseEvent.getDuration() <= 0) {
//            return false;
//        }
//
//        return baseEvent.getStartTime() != null;
//    }
//
//    public static boolean isCsvRequestValid(CsvRequest csvRequest) {
//        boolean isYearValid = csvRequest.getYear() >= LocalDate.now().getYear();
//        boolean isMonthValid = (1 <= csvRequest.getMonth() && csvRequest.getMonth() <= 12);
//
//        return isYearValid && isMonthValid;
//    }
//
//    private static boolean isEventValid(Event event) {
//        if (!RequestValidator.isPostBaseEventValid(event)) {
//            return false;
//        }
//
//        return event.getDayOfWeek() != null;
//    }
}
