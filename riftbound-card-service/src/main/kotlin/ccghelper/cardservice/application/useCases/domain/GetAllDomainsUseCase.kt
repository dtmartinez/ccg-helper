package ccghelper.cardservice.application.useCases.domain

import ccghelper.cardservice.domain.interfaces.core.NoParams
import ccghelper.cardservice.domain.interfaces.core.UseCase
import ccghelper.cardservice.domain.interfaces.repositories.DomainRepository
import ccghelper.cardservice.domain.model.domain.Domain

class GetAllDomains(
    private val domainRepository: DomainRepository
) : UseCase<NoParams, List<Domain>> {

    override fun execute(params: NoParams): List<Domain> =
        domainRepository.findAll()
}
