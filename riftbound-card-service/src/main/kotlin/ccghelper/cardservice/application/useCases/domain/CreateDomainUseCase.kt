package ccghelper.cardservice.application.useCases.domain

import ccghelper.cardservice.domain.interfaces.core.UseCase
import ccghelper.cardservice.domain.interfaces.repositories.DomainRepository
import ccghelper.cardservice.domain.model.domain.Domain

class CreateDomain(
    private val domainRepository: DomainRepository
) : UseCase<Domain, Domain> {

    override fun execute(params: Domain): Domain =
        domainRepository.save(params)
}
