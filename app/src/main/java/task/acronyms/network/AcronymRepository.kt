package task.acronyms.network

class AcronymRepository constructor(private val retrofitService: RetrofitService) {

   suspend fun getListOfAcronym(shortForm:String) = retrofitService.getListOfAcronym(shortForm)
}