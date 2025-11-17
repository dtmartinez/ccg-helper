package ccghelper.cardservice.adapters.controllers

import ccghelper.cardservice.application.useCases.domain.CreateDomain
import ccghelper.cardservice.application.useCases.domain.DeleteDomain
import ccghelper.cardservice.application.useCases.domain.GetAllDomains
import ccghelper.cardservice.application.useCases.domain.GetDomainById
import ccghelper.cardservice.application.useCases.domain.UpdateDomain
import ccghelper.cardservice.domain.errors.DomainException
import ccghelper.cardservice.domain.model.domain.Domain
import ccghelper.cardservice.domain.model.domain.DomainId
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/domains")
class DomainController(
    private val getDomainByIdUseCase: GetDomainById,
    private val getAllDomainsUseCase: GetAllDomains,
    private val createDomainUseCase: CreateDomain,
    private val updateDomainUseCase: UpdateDomain,
    private val deleteDomainUseCase: DeleteDomain
) {
   @GetMapping
    fun getAllDomains(): List<Domain> =
        getAllDomainsUseCase.execute(Unit)

    @GetMapping("/{id}")
    fun getDomainById(@PathVariable id: String): ResponseEntity<Domain> {
        val domainId = DomainId(id)
        val domain = getDomainByIdUseCase.execute(domainId)
        return domain
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()
    }

    @PostMapping
    fun createDomain(@RequestBody request: Domain): ResponseEntity<Domain> {
        val created = createDomainUseCase.execute(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(created)
    }

    @PutMapping("/{id}")
    fun updateDomain(
        @PathVariable id: String,
        @RequestBody request: Domain
    ): ResponseEntity<Domain> {
        val domainWithPathId = request.copy(id = DomainId(id.uppercase()))
        val updated = updateDomainUseCase.execute(domainWithPathId)
        return ResponseEntity.ok(updated)
    }

    @DeleteMapping("/{id}")
    fun deleteDomain(@PathVariable id: String): ResponseEntity<Void> {
        val domainId = DomainId(id.uppercase())
        deleteDomainUseCase.execute(domainId)
        return ResponseEntity.noContent().build()
    }

    @ExceptionHandler(DomainException::class)
    fun handleDomainException(ex: DomainException): ResponseEntity<ProblemDetail> {
        val (status, detail) = when (ex) {
            is DomainException.NotFound -> HttpStatus.NOT_FOUND to (ex.message ?: "Domain not found")
        }

        val problem = ProblemDetail.forStatusAndDetail(status, detail)

        return ResponseEntity.status(status).body(problem)
    }
}