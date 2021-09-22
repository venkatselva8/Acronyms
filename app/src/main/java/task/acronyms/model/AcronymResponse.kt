package task.acronyms.model

import com.google.gson.annotations.SerializedName

data class AcronymResponse(
    @SerializedName("sf") val sf: String,
    @SerializedName("lfs") val lfs: List<LongForms>
)



