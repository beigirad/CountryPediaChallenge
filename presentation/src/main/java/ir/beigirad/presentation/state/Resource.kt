package ir.beigirad.presentation.state

class Resource<out T>(
    val status: ResourceState,
    val data: T?,
    val message: String?
)