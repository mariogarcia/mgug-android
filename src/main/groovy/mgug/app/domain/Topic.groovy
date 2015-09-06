package mgug.app.domain

import groovy.transform.CompileStatic
import groovy.transform.Immutable

@Immutable
@CompileStatic
class Topic {

    String author
    Boolean checked
    String description
    Integer votes

    String toString() {
        return description
    }

}