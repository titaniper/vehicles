package com.github.titaniper.vehicles.model

data class VehicleInfo (
    var vehicleIdx: Int = 0,
    var description: String = "",
    var adminIdx: Int = 0,
    var favorite: Boolean = false,
    var lastUseDate: String  = "",
    var driverIdx: String  = "",
    var onMyDrive: Boolean = true,
    var depotSiteIdx: Int = 0,
    var landfillSiteIdx: Int = 0,
    var licenseNumber: String = "",
    var capacity: Int = 0,
    var status: String = "",
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var writable: Boolean = false,
    var companyIdx: Int = 0,
    var companyName: String = ""
)