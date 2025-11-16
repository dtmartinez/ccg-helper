package ccghelper.cardservice.domain.model.domain

import ccghelper.cardservice.domain.model.ability.Ability

data class Domain(
    val id: DomainId,
    val shorthand: Char,
    val color: Color,
    val symbol: Symbol,
    val synergies: Set<Ability> = emptySet()
)

@JvmInline value class DomainId(val name: String);
enum class Color { RED, GREEN, BLUE, ORANGE, PURPLE, YELLOW, INCORRECT_COLOR_NAME}
data class Symbol(val imageUrl: String, val description: String? = null)
