package mgug.app.domain

import groovy.transform.Immutable

@Immutable
class Repository {
    String name
    String description
    Boolean checked
    Date updated
}