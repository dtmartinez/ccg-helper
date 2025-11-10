package ccghelper.cardservice.domain.core

interface UseCase<Params, Result> {
    fun execute(params: Params): Result
}
