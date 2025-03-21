import com.example.buildingcountriesexplorerappusingapi.data.model.State
import com.example.buildingcountriesexplorerappusingapi.data.repositoryimplementation.StateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

class GetStatesUseCase(private val stateRepository: StateRepository) {

    operator fun invoke(countryName: String): Flow<List<State>> = flow {
        try {
            val states = stateRepository.getStates(countryName)
            emit(states)
        } catch (e: IOException) {
            emit(emptyList())
        } catch (e: HttpException) {
            emit(emptyList())
        } catch (e: Exception) {
            emit(emptyList())
        }
    }.flowOn(Dispatchers.IO)
}
