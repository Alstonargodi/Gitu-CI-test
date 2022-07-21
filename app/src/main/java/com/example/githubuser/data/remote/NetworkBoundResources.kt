package com.example.githubuser.data.remote

import androidx.lifecycle.LiveData
import com.example.githubuser.data.remote.utils.FetchResults
import com.example.githubuser.data.remote.utils.Resource
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResources<ResultType,RequestType> {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val source = loadFromDB().first()
        if (shouldFetch(source)){
            emit(Resource.Loading())
            when(val apiResponse = createCall().first()){
                is FetchResults.Empty ->{
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
                is FetchResults.Success ->{
                    saveCallResult(apiResponse.data)
                }
                is FetchResults.Error ->{
                    onFetchFailed()
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
        }else{
            emitAll(loadFromDB().map { Resource.Success(it) })
        }
    }

    protected open fun onFetchFailed(){}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<FetchResults<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result

}