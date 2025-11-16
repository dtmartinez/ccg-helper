package ccghelper.cardservice.application.useCases.domain

import ccghelper.cardservice.domain.errors.DomainException
import ccghelper.cardservice.domain.interfaces.core.UseCase
import ccghelper.cardservice.domain.interfaces.repositories.DomainRepository
import ccghelper.cardservice.domain.model.domain.DomainId

class DeleteDomain(
    private val domainRepository: DomainRepository
) : UseCase<DomainId, Unit> {

    override fun execute(params: DomainId) {
        val existing = domainRepository.findById(params)
            ?: throw DomainException.NotFound(params)

        domainRepository.delete(existing.id)
    }
}
