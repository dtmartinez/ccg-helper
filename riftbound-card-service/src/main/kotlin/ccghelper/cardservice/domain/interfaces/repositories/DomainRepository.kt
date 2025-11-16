package ccghelper.cardservice.domain.interfaces.repositories

import ccghelper.cardservice.domain.model.domain.Domain
import ccghelper.cardservice.domain.model.domain.DomainId

interface DomainRepository {
    fun findById( id: DomainId): Domain?
    fun findAll(): List<Domain>
    fun save(domain: Domain): Domain
    fun delete(id: DomainId)
}