import kotlinx.serialization.Serializable

@Serializable
data class StreamResponse(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String? = null
)

@Serializable
data class ListStreamResponse(
    val results: List<StreamResponse>
)
