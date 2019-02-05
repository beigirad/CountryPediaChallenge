package ir.beigirad.app.mapper

interface ViewMapper<in P, out V> {
    fun mapToView(presentation: P): V
}