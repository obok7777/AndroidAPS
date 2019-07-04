package info.nightscout.androidaps.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import info.nightscout.androidaps.database.TABLE_BOLUSES
import info.nightscout.androidaps.database.embedments.InsulinConfiguration
import info.nightscout.androidaps.database.embedments.InterfaceIDs
import info.nightscout.androidaps.database.interfaces.DBEntry
import info.nightscout.androidaps.database.interfaces.DBEntryWithInsulinConfig
import info.nightscout.androidaps.database.interfaces.DBEntryWithTime

@Entity(tableName = TABLE_BOLUSES,
        foreignKeys = [ForeignKey(
                entity = Bolus::class,
                parentColumns = ["id"],
                childColumns = ["referenceID"])])
data class Bolus(
        @PrimaryKey(autoGenerate = true)
        override var id: Long = 0,
        override var version: Int = 0,
        override var lastModified: Long = -1,
        override var valid: Boolean = true,
        override var referenceID: Long = 0,
        @Embedded
        override var interfaceIDs: InterfaceIDs = InterfaceIDs(),
        override var timestamp: Long,
        override var utcOffset: Long,
        var amount: Double,
        var type: Type,
        var basalInsulin: Boolean,
        @Embedded
        override var insulinConfiguration: InsulinConfiguration
) : DBEntry, DBEntryWithTime, DBEntryWithInsulinConfig {
    enum class Type {
        NORMAL,
        SMB,
        PRIMING
    }
}