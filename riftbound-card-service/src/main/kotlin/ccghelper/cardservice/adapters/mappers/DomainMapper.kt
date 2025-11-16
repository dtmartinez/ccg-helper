package ccghelper.cardservice.adapters.mappers

import ccghelper.cardservice.adapters.dtos.entities.DomainEntity
import ccghelper.cardservice.adapters.dtos.entities.SymbolEntity
import ccghelper.cardservice.domain.interfaces.core.Mapper
import ccghelper.cardservice.domain.model.ability.Ability
import ccghelper.cardservice.domain.model.ability.AbilityId
import ccghelper.cardservice.domain.model.domain.Color
import ccghelper.cardservice.domain.model.domain.Domain
import ccghelper.cardservice.domain.model.domain.DomainId
import ccghelper.cardservice.domain.model.domain.Symbol

class DomainMapper : Mapper<Domain, DomainEntity> {
    override fun mapFrom(dto: DomainEntity): Domain =
        Domain(
            id = DomainId(dto.name),
            shorthand = dto.shorthand,
            color = dto.color.toColor(),
            symbol = dto.symbol.toDomain(),
            synergies = dto.synergies.map{ synergy -> createAbilityNamed(synergy) }.toSet()
        )

    override fun mapTo(model: Domain): DomainEntity =
        DomainEntity(
            name = model.id.name,
            shorthand = model.shorthand,
            color = model.color.name,
            symbol = model.symbol.toEntity(),
            synergies = model.synergies.map{ it.id.name }.toSet()
        )

    private fun String.toColor(): Color = runCatching { Color.valueOf(this.uppercase()) }.getOrDefault(Color.INCORRECT_COLOR_NAME)

    private fun SymbolEntity.toDomain(): Symbol = Symbol(
        imageUrl = this.imageUrl, description = this.description
    )

    private fun Symbol.toEntity(): SymbolEntity =
        SymbolEntity(
            imageUrl = this.imageUrl,
            description = this.description
        )

    private fun createAbilityNamed(name: String): Ability =
        Ability(id = AbilityId(name))

}