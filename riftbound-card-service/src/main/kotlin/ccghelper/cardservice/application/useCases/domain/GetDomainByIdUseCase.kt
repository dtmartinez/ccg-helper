package ccghelper.cardservice.application.useCases.domain

import ccghelper.cardservice.domain.interfaces.core.UseCase
import ccghelper.cardservice.domain.interfaces.repositories.DomainRepository
import ccghelper.cardservice.domain.model.domain.Domain
import ccghelper.cardservice.domain.model.domain.DomainId

class GetDomainById(
    private val domainRepository: DomainRepository
) : UseCase<DomainId, Domain?> {

    override fun execute(params: DomainId): Domain? =
        domainRepository.findById(DomainId(name = params.name.uppercase()))
}
