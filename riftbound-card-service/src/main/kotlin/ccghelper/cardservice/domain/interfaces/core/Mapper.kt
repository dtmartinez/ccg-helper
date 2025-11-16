package ccghelper.cardservice.domain.interfaces.core

interface Mapper<Internal, External> {
    fun mapFrom(dto: External): Internal
    fun mapTo(model: Internal): External
}
