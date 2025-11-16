package ccghelper.cardservice.domain.errors

import ccghelper.cardservice.domain.model.domain.DomainId

sealed class DomainException(message: String) : RuntimeException(message) {
    class NotFound(val id: DomainId) :
        DomainException("Domain ${id.name} not found")
}
