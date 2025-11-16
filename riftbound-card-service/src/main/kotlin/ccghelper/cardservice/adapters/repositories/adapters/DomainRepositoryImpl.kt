package ccghelper.cardservice.adapters.repositories.adapters

import ccghelper.cardservice.adapters.dtos.entities.DomainEntity
import ccghelper.cardservice.adapters.mappers.DomainMapper
import ccghelper.cardservice.adapters.repositories.springRepositories.DomainCassandraRepository
import ccghelper.cardservice.domain.interfaces.repositories.DomainRepository
import ccghelper.cardservice.domain.model.domain.Domain
import ccghelper.cardservice.domain.model.domain.DomainId
import org.springframework.stereotype.Repository

@Repository
class DomainRepositoryImpl(
    private val springRepository: DomainCassandraRepository,
    private val domainMapper: DomainMapper
) : DomainRepository {

    override fun findById(id: DomainId): Domain? {
        val entity: DomainEntity? = springRepository.findById(id.name).orElse(null)
        return entity?.let { domainMapper.mapFrom(it) }
    }

    override fun findAll(): List<Domain> =
        springRepository.findAll()
            .map { domainMapper.mapFrom(it) }

    override fun save(domain: Domain): Domain {
        val entityToSave: DomainEntity = domainMapper.mapTo(domain)
        val savedEntity: DomainEntity = springRepository.save(entityToSave)
        return domainMapper.mapFrom(savedEntity)
    }

    override fun delete(id: DomainId) {
        springRepository.deleteById(id.name)
    }
}
