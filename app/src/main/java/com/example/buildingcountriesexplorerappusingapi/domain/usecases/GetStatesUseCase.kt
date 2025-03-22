import com.example.buildingcountriesexplorerappusingapi.data.model.State
import com.example.buildingcountriesexplorerappusingapi.data.repositoryimplementation.StateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class GetStatesUseCase(private val stateRepository: StateRepository) { // interact with repository

    operator fun invoke(countryName: String): Flow<List<State>> = flow {
        try {
            val states = stateRepository.getStates(countryName)
            emit(states)
        } catch (e: Exception) { // handle error
            emit(emptyList())
        }
    }.flowOn(Dispatchers.IO)
}



