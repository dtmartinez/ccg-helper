package ccghelper.cardservice.domain.model.ability

@JvmInline value class AbilityId(val name: String);

data class Ability(
    val id: AbilityId
)

