package ir.beigirad.zeroapplication.model


data class DeviceInfo(

        var manufacture: String? = null,

        var model: String? = null,

        var sdk: Int = 0,

        var appVersionName: String? = null,

        var appVersionCode: Int,

        var firebaseToken: String? = null
)