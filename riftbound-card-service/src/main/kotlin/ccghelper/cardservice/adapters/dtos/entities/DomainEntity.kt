package ccghelper.cardservice.adapters.dtos.entities

import org.springframework.data.cassandra.core.mapping.Column
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import org.springframework.data.cassandra.core.mapping.UserDefinedType

@Table("domain")
data class DomainEntity(
    @PrimaryKey
    val name: String,
    @Column("shorthand")
    val shorthand: Char,
    @Column("color")
    val color: String,
    @Column("symbol")
    val symbol: SymbolEntity,
    @Column("synergies")
    val synergies: Set<String> = emptySet()
)

@UserDefinedType("symbol")
data class SymbolEntity(
    @Column("image_url")
    val imageUrl: String,
    @Column("description")
    val description: String? = null
)

