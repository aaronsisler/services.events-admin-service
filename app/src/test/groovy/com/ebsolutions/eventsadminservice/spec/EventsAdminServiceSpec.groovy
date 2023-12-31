package com.ebsolutions.eventsadminservice.spec

import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest
class EventsAdminServiceSpec extends Specification {

    @Inject
    EmbeddedApplication<?> application

    def "Application starts correctly"() {
        expect:
            application.running
    }
}
