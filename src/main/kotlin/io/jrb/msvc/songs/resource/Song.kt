package io.jrb.msvc.songs.resource

data class Song(
        val guid: String?,
        val title: String,
        val authors: List<String>,
        val additionalTitles: List<String>,
        val themes: List<String>,
        val lyrics: Map<String, List<String>>,
        val lyricOrder: List<String>,
        val sourceId: String,
        val sourceSystem: String
) {
}
