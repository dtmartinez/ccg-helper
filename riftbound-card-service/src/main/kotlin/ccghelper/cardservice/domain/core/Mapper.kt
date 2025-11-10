package ccghelper.cardservice.domain.core

interface Mapper<Internal, External> {
    fun mapFrom(dto: External): Internal
    fun mapTo(model: Internal): External
}
