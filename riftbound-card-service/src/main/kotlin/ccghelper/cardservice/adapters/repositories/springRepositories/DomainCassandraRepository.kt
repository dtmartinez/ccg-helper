package ccghelper.cardservice.adapters.repositories.springRepositories

import ccghelper.cardservice.adapters.dtos.entities.DomainEntity
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository

@Repository
interface DomainCassandraRepository : CassandraRepository<DomainEntity, String>
