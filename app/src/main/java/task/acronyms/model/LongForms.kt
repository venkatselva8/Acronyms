package task.acronyms.model

import com.google.gson.annotations.SerializedName

data class LongForms(

    @SerializedName("lf") val lf: String,
    @SerializedName("freq") val freq: Int,
    @SerializedName("since") val since: Int,
    @SerializedName("vars") val vars: List<Vars>
) {
    data class Vars(
        @SerializedName("lf") val lf: String,
        @SerializedName("freq") val freq: Int,
        @SerializedName("since") val since: Int
    )
}
