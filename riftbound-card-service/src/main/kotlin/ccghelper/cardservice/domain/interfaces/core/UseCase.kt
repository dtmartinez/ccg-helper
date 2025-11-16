package ccghelper.cardservice.domain.interfaces.core

interface UseCase<Params, Result> {
    fun execute(params: Params): Result
}

typealias NoParams = Unit
